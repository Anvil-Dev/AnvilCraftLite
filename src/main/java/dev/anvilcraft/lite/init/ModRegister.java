package dev.anvilcraft.lite.init;

import dev.anvilcraft.lite.data.lang.LangHandler;
import dev.anvilcraft.lite.util.FormattingUtil;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModRegister {
    public final String modId;
    public final DeferredRegister.Blocks blocks;
    public final DeferredRegister.Items items;
    public final DeferredRegister.Entities entities;
    public final DeferredRegister.DataComponents dataComponents;
    public final DeferredRegister<CreativeModeTab> creativeModeTabs;
    public final Map<String, String> language = new HashMap<>();

    public ModRegister(String modId) {
        this.modId = modId;
        this.blocks = DeferredRegister.createBlocks(modId);
        this.items = DeferredRegister.createItems(modId);
        this.entities = DeferredRegister.createEntities(modId);
        this.dataComponents = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, modId);
        this.creativeModeTabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, modId);
    }

    public void init(IEventBus modEventBus) {
        this.blocks.register(modEventBus);
        this.dataComponents.register(modEventBus);
        this.items.register(modEventBus);
        this.creativeModeTabs.register(modEventBus);
        this.entities.register(modEventBus);
    }

    public <T extends Item> ItemRegister<T> item(String name, Function<Item.Properties, T> factory) {
        return new ItemRegister<>(name, factory, this);
    }

    public <T extends BlockItem> ItemRegister<T> blockItem(Holder<Block> block, BiFunction<Block, Item.Properties, T> factory) {
        ResourceKey<Block> key = Objects.requireNonNull(block.getKey());
        String name = key.location().getPath();
        ItemRegister<T> register = new ItemRegister<>(name, properties -> factory.apply(block.value(), properties), this);
        return register.properties(Item.Properties::useBlockDescriptionPrefix);
    }

    public ItemRegister<BlockItem> simpleBlockItem(Holder<Block> block) {
        return this.blockItem(block, BlockItem::new);
    }

    public <T extends Block> BlockRegister<T> block(String name, Function<Block.Properties, T> factory) {
        return new BlockRegister<>(name, factory, this);
    }

    public DeferredHolder<CreativeModeTab, CreativeModeTab> itemGroup(String name, Consumer<CreativeModeTab.Builder> consumer) {
        return this.creativeModeTabs.register(
            name, () -> {
                CreativeModeTab.Builder builder = CreativeModeTab.builder();
                consumer.accept(builder);
                return builder.withTabsBefore(CreativeModeTabs.SPAWN_EGGS).build();
            }
        );
    }

    public Component lang(String key, String value) {
        this.language.put(key, value);
        return Component.translatable(key);
    }

    public <T> @NotNull DataComponentType<T> componentType(String name, Consumer<DataComponentType.Builder<T>> customizer) {
        var builder = DataComponentType.<T>builder();
        customizer.accept(builder);
        var componentType = builder.build();
        this.dataComponents.register(name, () -> componentType);
        return componentType;
    }

    public <T extends Entity> EntityRegister<T> entity(String name, EntityType.EntityFactory<T> factory, MobCategory category) {
        return new EntityRegister<>(name, factory, category, this);
    }

    public void handlerLang(LangHandler provider) {
        this.language.forEach(provider::add);
    }

    public static class ItemRegister<T extends Item> {
        private final String name;
        private final ModRegister register;
        private final Function<Item.Properties, T> factory;
        private final Item.Properties properties;
        private String lang = null;
        private Consumer<Item.Properties> propertiesConsumer = properties -> {
        };

        public ItemRegister(String name, Function<Item.Properties, T> factory, ModRegister register) {
            this.name = name;
            this.register = register;
            this.factory = factory;
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(this.register.modId, this.name);
            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, location);
            this.properties = new Item.Properties().setId(key);
        }

        public ItemRegister<T> properties(Consumer<Item.Properties> consumer) {
            final Consumer<Item.Properties> propertiesConsumer = this.propertiesConsumer;
            this.propertiesConsumer = properties -> {
                consumer.accept(properties);
                propertiesConsumer.accept(properties);
            };
            return this;
        }

        public ItemRegister<T> lang(String lang) {
            this.lang = lang;
            return this;
        }

        public DeferredItem<T> register() {
            this.propertiesConsumer.accept(this.properties);
            String key = this.properties.effectiveDescriptionId();
            this.register.language.put(key, Optional.ofNullable(this.lang).orElse(FormattingUtil.toEnglishName(this.name)));
            return this.register.items.register(this.name, () -> factory.apply(this.properties));
        }
    }

    public static class BlockRegister<T extends Block> {
        private final String name;
        private final ModRegister register;
        private final Function<Block.Properties, T> factory;
        private Block.Properties properties;
        private final ResourceLocation location;
        private String lang = null;
        private Consumer<Block.Properties> propertiesConsumer = properties -> {
        };

        public BlockRegister(String name, Function<Block.Properties, T> factory, ModRegister register) {
            this.name = name;
            this.register = register;
            this.factory = factory;
            this.location = ResourceLocation.fromNamespaceAndPath(this.register.modId, this.name);
            this.properties = Block.Properties.of();
        }

        public BlockRegister<T> properties(Consumer<Block.Properties> consumer) {
            final Consumer<Block.Properties> propertiesConsumer = this.propertiesConsumer;
            this.propertiesConsumer = properties -> {
                consumer.accept(properties);
                propertiesConsumer.accept(properties);
            };
            return this;
        }

        public BlockRegister<T> properties(Supplier<Block> block) {
            this.properties = BlockBehaviour.Properties.ofFullCopy(block.get());
            return this;
        }

        public BlockRegister<T> lang(String lang) {
            this.lang = lang;
            return this;
        }

        public DeferredBlock<T> register() {
            this.propertiesConsumer.accept(this.properties);
            ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, this.location);
            this.properties.setId(key);
            this.register.language.put(
                this.properties.effectiveDescriptionId(),
                Optional.ofNullable(this.lang).orElse(FormattingUtil.toEnglishName(this.name))
            );
            return this.register.blocks.register(this.name, () -> factory.apply(this.properties));
        }
    }

    public static class EntityRegister<T extends Entity> {
        private final String name;
        private final ModRegister register;
        private final EntityType.EntityFactory<T> factory;
        private final MobCategory category;
        private final ResourceLocation location;
        private String lang = null;
        private Consumer<EntityType.Builder<T>> builderConsumer = builder -> {
        };

        public EntityRegister(String name, EntityType.EntityFactory<T> factory, MobCategory category, ModRegister register) {
            this.name = name;
            this.register = register;
            this.factory = factory;
            this.category = category;
            this.location = ResourceLocation.fromNamespaceAndPath(this.register.modId, this.name);
        }

        public EntityRegister<T> properties(Consumer<EntityType.Builder<T>> consumer) {
            this.builderConsumer = builder -> {
                consumer.accept(builder);
                this.builderConsumer.accept(builder);
            };
            return this;
        }

        public EntityRegister<T> lang(String lang) {
            this.lang = lang;
            return this;
        }

        public DeferredHolder<EntityType<?>, EntityType<T>> register() {
            this.register.language.put(
                Util.makeDescriptionId("entity", this.location),
                Optional.ofNullable(this.lang).orElse(FormattingUtil.toEnglishName(this.name))
            );
            return this.register.entities.registerEntityType(
                this.name, this.factory, this.category, builder -> {
                    builderConsumer.accept(builder);
                    return builder;
                }
            );
        }
    }
}

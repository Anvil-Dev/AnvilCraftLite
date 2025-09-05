package dev.anvilcraft.lite.init;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModRegister {
    public final String modId;
    public final DeferredRegister.Blocks blocks;
    public final DeferredRegister.Items items;
    public final DeferredRegister<DataComponentType<?>> componentTypes;
    public final DeferredRegister<CreativeModeTab> creativeModeTabs;

    public ModRegister(String modId) {
        this.modId = modId;
        this.blocks = DeferredRegister.createBlocks(modId);
        this.items = DeferredRegister.createItems(modId);
        this.componentTypes = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, modId);
        this.creativeModeTabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, modId);
    }

    public void init(IEventBus modEventBus) {
        this.blocks.register(modEventBus);
        this.componentTypes.register(modEventBus);
        this.items.register(modEventBus);
        this.creativeModeTabs.register(modEventBus);
    }

    public <T extends Item> ItemRegister<T> item(String name, Function<Item.Properties, T> factory) {
        return new ItemRegister<>(name, factory, this);
    }

    public <T extends BlockItem> DeferredItem<T> blockItem(Holder<Block> block, BiFunction<Block, Item.Properties, T> factory) {
        ResourceKey<Block> key = Objects.requireNonNull(block.getKey());
        String name = key.location().getPath();
        ItemRegister<T> register = new ItemRegister<>(name, properties -> factory.apply(block.value(), properties), this);
        return register.register();
    }

    public DeferredItem<BlockItem> simpleBlockItem(Holder<Block> block) {
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

    public <T> @NotNull DataComponentType<T> componentType(String name, Consumer<DataComponentType.Builder<T>> customizer) {
        var builder = DataComponentType.<T>builder();
        customizer.accept(builder);
        var componentType = builder.build();
        this.componentTypes.register(name, () -> componentType);
        return componentType;
    }

    public static class ItemRegister<T extends Item> {
        private final String name;
        private final ModRegister register;
        private final Function<Item.Properties, T> factory;
        private final Item.Properties properties;

        public ItemRegister(String name, Function<Item.Properties, T> factory, ModRegister register) {
            this.name = name;
            this.register = register;
            this.factory = factory;
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(this.register.modId, this.name);
            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, location);
            this.properties = new Item.Properties().setId(key);
        }

        public ItemRegister<T> properties(Consumer<Item.Properties> consumer) {
            consumer.accept(this.properties);
            return this;
        }

        public DeferredItem<T> register() {
            return this.register.items.register(this.name, () -> factory.apply(this.properties));
        }
    }

    public static class BlockRegister<T extends Block> {
        private final String name;
        private final ModRegister register;
        private final Function<Block.Properties, T> factory;
        private Block.Properties properties;
        private final ResourceLocation location;
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
            this.propertiesConsumer = consumer;
            return this;
        }

        public BlockRegister<T> properties(Supplier<Block> block) {
            this.properties = BlockBehaviour.Properties.ofFullCopy(block.get());
            return this;
        }

        public DeferredBlock<T> register() {
            this.propertiesConsumer.accept(this.properties);
            ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, location);
            this.properties.setId(key);
            return this.register.blocks.register(this.name, () -> factory.apply(this.properties));
        }
    }
}

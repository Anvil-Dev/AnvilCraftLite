package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCrushRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ItemCrushRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        ItemCrushRecipe.builder(provider.getItems()).requires(Items.CREEPER_HEAD).result(Items.GUNPOWDER, 64).save(provider.getOutput());

        ItemCrushRecipe.builder(provider.getItems()).requires(Items.SKELETON_SKULL).result(Items.BONE_MEAL, 64).save(provider.getOutput());

        ItemCrushRecipeLoader.armor(provider, Items.CHAINMAIL_HELMET, Items.IRON_CHAIN);
        ItemCrushRecipeLoader.armor(provider, Items.CHAINMAIL_CHESTPLATE, Items.IRON_CHAIN);
        ItemCrushRecipeLoader.armor(provider, Items.CHAINMAIL_LEGGINGS, Items.IRON_CHAIN);
        ItemCrushRecipeLoader.armor(provider, Items.CHAINMAIL_BOOTS, Items.IRON_CHAIN);

        ItemCrushRecipeLoader.armor(provider, Items.LEATHER_HELMET, Items.LEATHER);
        ItemCrushRecipeLoader.armor(provider, Items.LEATHER_CHESTPLATE, Items.LEATHER);
        ItemCrushRecipeLoader.armor(provider, Items.LEATHER_LEGGINGS, Items.LEATHER);
        ItemCrushRecipeLoader.armor(provider, Items.LEATHER_BOOTS, Items.LEATHER);
        ItemCrushRecipeLoader.armor(provider, Items.LEATHER_HORSE_ARMOR, Items.LEATHER);

        ItemCrushRecipeLoader.tool(provider, Items.IRON_SWORD, Items.IRON_INGOT);
        ItemCrushRecipeLoader.tool(provider, Items.IRON_PICKAXE, Items.IRON_INGOT);
        ItemCrushRecipeLoader.tool(provider, Items.IRON_AXE, Items.IRON_INGOT);
        ItemCrushRecipeLoader.tool(provider, Items.IRON_HOE, Items.IRON_INGOT);
        ItemCrushRecipeLoader.tool(provider, Items.IRON_SHOVEL, Items.IRON_INGOT);
        ItemCrushRecipeLoader.armor(provider, Items.IRON_HELMET, Items.IRON_INGOT);
        ItemCrushRecipeLoader.armor(provider, Items.IRON_CHESTPLATE, Items.IRON_INGOT);
        ItemCrushRecipeLoader.armor(provider, Items.IRON_LEGGINGS, Items.IRON_INGOT);
        ItemCrushRecipeLoader.armor(provider, Items.IRON_BOOTS, Items.IRON_INGOT);
        ItemCrushRecipeLoader.armor(provider, Items.IRON_HORSE_ARMOR, Items.IRON_INGOT);

        ItemCrushRecipeLoader.tool(provider, Items.GOLDEN_SWORD, Items.GOLD_INGOT);
        ItemCrushRecipeLoader.tool(provider, Items.GOLDEN_PICKAXE, Items.GOLD_INGOT);
        ItemCrushRecipeLoader.tool(provider, Items.GOLDEN_AXE, Items.GOLD_INGOT);
        ItemCrushRecipeLoader.tool(provider, Items.GOLDEN_HOE, Items.GOLD_INGOT);
        ItemCrushRecipeLoader.tool(provider, Items.GOLDEN_SHOVEL, Items.GOLD_INGOT);
        ItemCrushRecipeLoader.armor(provider, Items.GOLDEN_HELMET, Items.GOLD_INGOT);
        ItemCrushRecipeLoader.armor(provider, Items.GOLDEN_CHESTPLATE, Items.GOLD_INGOT);
        ItemCrushRecipeLoader.armor(provider, Items.GOLDEN_LEGGINGS, Items.GOLD_INGOT);
        ItemCrushRecipeLoader.armor(provider, Items.GOLDEN_BOOTS, Items.GOLD_INGOT);
        ItemCrushRecipeLoader.armor(provider, Items.GOLDEN_HORSE_ARMOR, Items.GOLD_INGOT);

        ItemCrushRecipeLoader.tool(provider, Items.DIAMOND_SWORD, Items.DIAMOND);
        ItemCrushRecipeLoader.tool(provider, Items.DIAMOND_PICKAXE, Items.DIAMOND);
        ItemCrushRecipeLoader.tool(provider, Items.DIAMOND_AXE, Items.DIAMOND);
        ItemCrushRecipeLoader.tool(provider, Items.DIAMOND_HOE, Items.DIAMOND);
        ItemCrushRecipeLoader.tool(provider, Items.DIAMOND_SHOVEL, Items.DIAMOND);
        ItemCrushRecipeLoader.armor(provider, Items.DIAMOND_HELMET, Items.DIAMOND);
        ItemCrushRecipeLoader.armor(provider, Items.DIAMOND_CHESTPLATE, Items.DIAMOND);
        ItemCrushRecipeLoader.armor(provider, Items.DIAMOND_LEGGINGS, Items.DIAMOND);
        ItemCrushRecipeLoader.armor(provider, Items.DIAMOND_BOOTS, Items.DIAMOND);
        ItemCrushRecipeLoader.armor(provider, Items.DIAMOND_HORSE_ARMOR, Items.DIAMOND);

        ItemCrushRecipeLoader.blockCrush(provider, Items.STONE, Items.COBBLESTONE);
        ItemCrushRecipeLoader.blockCrush(provider, Items.COBBLESTONE, Items.GRAVEL);
        ItemCrushRecipeLoader.blockCrush(provider, Items.GRAVEL, Items.SAND);
        ItemCrushRecipeLoader.blockCrush(provider, Items.POLISHED_GRANITE, Items.GRANITE);
        ItemCrushRecipeLoader.blockCrush(provider, Items.GRANITE, Items.RED_SAND);
        ItemCrushRecipeLoader.blockCrush(provider, Items.POLISHED_ANDESITE, Items.ANDESITE);
        ItemCrushRecipeLoader.blockCrush(provider, Items.POLISHED_DIORITE, Items.DIORITE);
        ItemCrushRecipeLoader.blockCrush(provider, Items.STONE_BRICKS, Items.CRACKED_STONE_BRICKS);
        ItemCrushRecipeLoader.blockCrush(provider, Items.DEEPSLATE_BRICKS, Items.CRACKED_DEEPSLATE_BRICKS);
        ItemCrushRecipeLoader.blockCrush(provider, Items.NETHER_BRICKS, Items.CRACKED_NETHER_BRICKS);
        ItemCrushRecipeLoader.blockCrush(provider, Items.DEEPSLATE_TILES, Items.CRACKED_DEEPSLATE_TILES);
        ItemCrushRecipeLoader.blockCrush(provider, Items.POLISHED_BLACKSTONE_BRICKS, Items.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        ItemCrushRecipeLoader.blockCrush(provider, Items.SOUL_SOIL, Items.SOUL_SAND);
    }

    private static void tool(RegistrumRecipeProvider provider, ItemLike tool, ItemLike result) {
        ItemCrushRecipe.builder(provider.getItems())
            .requires(tool)
            .result(result, 0.5f)
            .save(provider.getOutput(), AnvilCraftLite.of("item_crush/tool/%s_2_%s".formatted(getName(tool), getName(result))));
    }

    private static void blockCrush(RegistrumRecipeProvider provider, ItemLike input, ItemLike result) {
        ItemCrushRecipe.builder(provider.getItems())
            .requires(input)
            .result(result, 0.8f)
            .save(provider.getOutput(), AnvilCraftLite.of("item_crush/block_crush/%s_from_%s".formatted(getName(result), getName(input))));
    }

    private static void armor(RegistrumRecipeProvider provider, ItemLike armor, ItemLike result) {
        ItemCrushRecipe.builder(provider.getItems())
            .requires(armor)
            .result(result, UniformGenerator.between(0.0f, 2.0f))
            .save(provider.getOutput(), AnvilCraftLite.of("item_crush/armor/%s_2_%s".formatted(getName(armor), getName(result))));
    }

    private static String getName(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }
}

package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCrushRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ItemCrushRecipeLoader {
    public static void init(RecipeOutput provider) {
        ItemCrushRecipe.builder()
            .requires(Items.CREEPER_HEAD)
            .result(Items.GUNPOWDER, 64)
            .save(provider);

        ItemCrushRecipe.builder()
            .requires(Items.SKELETON_SKULL)
            .result(Items.BONE_MEAL, 64)
            .save(provider);

        armor(provider, Items.CHAINMAIL_HELMET, Items.CHAIN);
        armor(provider, Items.CHAINMAIL_CHESTPLATE, Items.CHAIN);
        armor(provider, Items.CHAINMAIL_LEGGINGS, Items.CHAIN);
        armor(provider, Items.CHAINMAIL_BOOTS, Items.CHAIN);

        armor(provider, Items.LEATHER_HELMET, Items.LEATHER);
        armor(provider, Items.LEATHER_CHESTPLATE, Items.LEATHER);
        armor(provider, Items.LEATHER_LEGGINGS, Items.LEATHER);
        armor(provider, Items.LEATHER_BOOTS, Items.LEATHER);
        armor(provider, Items.LEATHER_HORSE_ARMOR, Items.LEATHER);

        tool(provider, Items.IRON_SWORD, Items.IRON_INGOT);
        tool(provider, Items.IRON_PICKAXE, Items.IRON_INGOT);
        tool(provider, Items.IRON_AXE, Items.IRON_INGOT);
        tool(provider, Items.IRON_HOE, Items.IRON_INGOT);
        tool(provider, Items.IRON_SHOVEL, Items.IRON_INGOT);
        armor(provider, Items.IRON_HELMET, Items.IRON_INGOT);
        armor(provider, Items.IRON_CHESTPLATE, Items.IRON_INGOT);
        armor(provider, Items.IRON_LEGGINGS, Items.IRON_INGOT);
        armor(provider, Items.IRON_BOOTS, Items.IRON_INGOT);
        armor(provider, Items.IRON_HORSE_ARMOR, Items.IRON_INGOT);

        tool(provider, Items.GOLDEN_SWORD, Items.GOLD_INGOT);
        tool(provider, Items.GOLDEN_PICKAXE, Items.GOLD_INGOT);
        tool(provider, Items.GOLDEN_AXE, Items.GOLD_INGOT);
        tool(provider, Items.GOLDEN_HOE, Items.GOLD_INGOT);
        tool(provider, Items.GOLDEN_SHOVEL, Items.GOLD_INGOT);
        armor(provider, Items.GOLDEN_HELMET, Items.GOLD_INGOT);
        armor(provider, Items.GOLDEN_CHESTPLATE, Items.GOLD_INGOT);
        armor(provider, Items.GOLDEN_LEGGINGS, Items.GOLD_INGOT);
        armor(provider, Items.GOLDEN_BOOTS, Items.GOLD_INGOT);
        armor(provider, Items.GOLDEN_HORSE_ARMOR, Items.GOLD_INGOT);

        tool(provider, Items.DIAMOND_SWORD, Items.DIAMOND);
        tool(provider, Items.DIAMOND_PICKAXE, Items.DIAMOND);
        tool(provider, Items.DIAMOND_AXE, Items.DIAMOND);
        tool(provider, Items.DIAMOND_HOE, Items.DIAMOND);
        tool(provider, Items.DIAMOND_SHOVEL, Items.DIAMOND);
        armor(provider, Items.DIAMOND_HELMET, Items.DIAMOND);
        armor(provider, Items.DIAMOND_CHESTPLATE, Items.DIAMOND);
        armor(provider, Items.DIAMOND_LEGGINGS, Items.DIAMOND);
        armor(provider, Items.DIAMOND_BOOTS, Items.DIAMOND);
        armor(provider, Items.DIAMOND_HORSE_ARMOR, Items.DIAMOND);

        blockCrush(provider, Items.STONE, Items.COBBLESTONE);
        blockCrush(provider, Items.COBBLESTONE, Items.GRAVEL);
        blockCrush(provider, Items.GRAVEL, Items.SAND);
        blockCrush(provider, Items.POLISHED_GRANITE, Items.GRANITE);
        blockCrush(provider, Items.GRANITE, Items.RED_SAND);
        blockCrush(provider, Items.POLISHED_ANDESITE, Items.ANDESITE);
        blockCrush(provider, Items.POLISHED_DIORITE, Items.DIORITE);
        blockCrush(provider, Items.STONE_BRICKS, Items.CRACKED_STONE_BRICKS);
        blockCrush(provider, Items.DEEPSLATE_BRICKS, Items.CRACKED_DEEPSLATE_BRICKS);
        blockCrush(provider, Items.NETHER_BRICKS, Items.CRACKED_NETHER_BRICKS);
        blockCrush(provider, Items.DEEPSLATE_TILES, Items.CRACKED_DEEPSLATE_TILES);
        blockCrush(provider, Items.POLISHED_BLACKSTONE_BRICKS, Items.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        blockCrush(provider, Items.SOUL_SOIL, Items.SOUL_SAND);
    }

    private static void tool(RecipeOutput provider, ItemLike tool, ItemLike result) {
        ItemCrushRecipe.builder()
            .requires(tool)
            .result(result, 0.5f)
            .save(provider, AnvilCraftLite.of("item_crush/tool/%s_2_%s".formatted(getName(tool), getName(result))));
    }

    private static void blockCrush(RecipeOutput provider, ItemLike input, ItemLike result) {
        ItemCrushRecipe.builder()
            .requires(input)
            .result(result, 0.8f)
            .save(provider, AnvilCraftLite.of("item_crush/block_crush/%s_from_%s".formatted(getName(result), getName(input))));
    }

    private static void armor(RecipeOutput provider, ItemLike armor, ItemLike result) {
        ItemCrushRecipe.builder()
            .requires(armor)
            .result(result, UniformGenerator.between(0.0f, 2.0f))
            .save(provider, AnvilCraftLite.of("item_crush/armor/%s_2_%s".formatted(getName(armor), getName(result))));
    }

    private static String getName(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }
}

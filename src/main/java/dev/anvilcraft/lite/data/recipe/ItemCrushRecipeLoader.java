package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCrushRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ItemCrushRecipeLoader extends ModRecipeLoader {
    public ItemCrushRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        ItemCrushRecipe.builder(this.items).requires(Items.CREEPER_HEAD).result(Items.GUNPOWDER, 64).save(this.output);

        ItemCrushRecipe.builder(this.items).requires(Items.SKELETON_SKULL).result(Items.BONE_MEAL, 64).save(this.output);

        this.armor(Items.CHAINMAIL_HELMET, Items.CHAIN);
        this.armor(Items.CHAINMAIL_CHESTPLATE, Items.CHAIN);
        this.armor(Items.CHAINMAIL_LEGGINGS, Items.CHAIN);
        this.armor(Items.CHAINMAIL_BOOTS, Items.CHAIN);

        this.armor(Items.LEATHER_HELMET, Items.LEATHER);
        this.armor(Items.LEATHER_CHESTPLATE, Items.LEATHER);
        this.armor(Items.LEATHER_LEGGINGS, Items.LEATHER);
        this.armor(Items.LEATHER_BOOTS, Items.LEATHER);
        this.armor(Items.LEATHER_HORSE_ARMOR, Items.LEATHER);

        this.tool(Items.IRON_SWORD, Items.IRON_INGOT);
        this.tool(Items.IRON_PICKAXE, Items.IRON_INGOT);
        this.tool(Items.IRON_AXE, Items.IRON_INGOT);
        this.tool(Items.IRON_HOE, Items.IRON_INGOT);
        this.tool(Items.IRON_SHOVEL, Items.IRON_INGOT);
        this.armor(Items.IRON_HELMET, Items.IRON_INGOT);
        this.armor(Items.IRON_CHESTPLATE, Items.IRON_INGOT);
        this.armor(Items.IRON_LEGGINGS, Items.IRON_INGOT);
        this.armor(Items.IRON_BOOTS, Items.IRON_INGOT);
        this.armor(Items.IRON_HORSE_ARMOR, Items.IRON_INGOT);

        this.tool(Items.GOLDEN_SWORD, Items.GOLD_INGOT);
        this.tool(Items.GOLDEN_PICKAXE, Items.GOLD_INGOT);
        this.tool(Items.GOLDEN_AXE, Items.GOLD_INGOT);
        this.tool(Items.GOLDEN_HOE, Items.GOLD_INGOT);
        this.tool(Items.GOLDEN_SHOVEL, Items.GOLD_INGOT);
        this.armor(Items.GOLDEN_HELMET, Items.GOLD_INGOT);
        this.armor(Items.GOLDEN_CHESTPLATE, Items.GOLD_INGOT);
        this.armor(Items.GOLDEN_LEGGINGS, Items.GOLD_INGOT);
        this.armor(Items.GOLDEN_BOOTS, Items.GOLD_INGOT);
        this.armor(Items.GOLDEN_HORSE_ARMOR, Items.GOLD_INGOT);

        this.tool(Items.DIAMOND_SWORD, Items.DIAMOND);
        this.tool(Items.DIAMOND_PICKAXE, Items.DIAMOND);
        this.tool(Items.DIAMOND_AXE, Items.DIAMOND);
        this.tool(Items.DIAMOND_HOE, Items.DIAMOND);
        this.tool(Items.DIAMOND_SHOVEL, Items.DIAMOND);
        this.armor(Items.DIAMOND_HELMET, Items.DIAMOND);
        this.armor(Items.DIAMOND_CHESTPLATE, Items.DIAMOND);
        this.armor(Items.DIAMOND_LEGGINGS, Items.DIAMOND);
        this.armor(Items.DIAMOND_BOOTS, Items.DIAMOND);
        this.armor(Items.DIAMOND_HORSE_ARMOR, Items.DIAMOND);

        this.blockCrush(Items.STONE, Items.COBBLESTONE);
        this.blockCrush(Items.COBBLESTONE, Items.GRAVEL);
        this.blockCrush(Items.GRAVEL, Items.SAND);
        this.blockCrush(Items.POLISHED_GRANITE, Items.GRANITE);
        this.blockCrush(Items.GRANITE, Items.RED_SAND);
        this.blockCrush(Items.POLISHED_ANDESITE, Items.ANDESITE);
        this.blockCrush(Items.POLISHED_DIORITE, Items.DIORITE);
        this.blockCrush(Items.STONE_BRICKS, Items.CRACKED_STONE_BRICKS);
        this.blockCrush(Items.DEEPSLATE_BRICKS, Items.CRACKED_DEEPSLATE_BRICKS);
        this.blockCrush(Items.NETHER_BRICKS, Items.CRACKED_NETHER_BRICKS);
        this.blockCrush(Items.DEEPSLATE_TILES, Items.CRACKED_DEEPSLATE_TILES);
        this.blockCrush(Items.POLISHED_BLACKSTONE_BRICKS, Items.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        this.blockCrush(Items.SOUL_SOIL, Items.SOUL_SAND);
    }

    private void tool(ItemLike tool, ItemLike result) {
        ItemCrushRecipe.builder(this.items)
            .requires(tool)
            .result(result, 0.5f)
            .save(this.output, AnvilCraftLite.of("item_crush/tool/%s_2_%s".formatted(getName(tool), getName(result))));
    }

    private void blockCrush(ItemLike input, ItemLike result) {
        ItemCrushRecipe.builder(this.items)
            .requires(input)
            .result(result, 0.8f)
            .save(this.output, AnvilCraftLite.of("item_crush/block_crush/%s_from_%s".formatted(getName(result), getName(input))));
    }

    private void armor(ItemLike armor, ItemLike result) {
        ItemCrushRecipe.builder(this.items)
            .requires(armor)
            .result(result, UniformGenerator.between(0.0f, 2.0f))
            .save(this.output, AnvilCraftLite.of("item_crush/armor/%s_2_%s".formatted(getName(armor), getName(result))));
    }

    private static String getName(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }
}

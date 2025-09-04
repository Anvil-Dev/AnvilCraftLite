package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.BulgingRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class BulgingRecipeLoader {
    public static void init(RecipeOutput provider) {
        BulgingRecipeLoader.bulging(provider, Items.DIRT, Items.CLAY);
        BulgingRecipeLoader.bulging(provider, Items.CRIMSON_FUNGUS, Items.NETHER_WART_BLOCK);
        BulgingRecipeLoader.bulging(provider, Items.WARPED_FUNGUS, Items.WARPED_WART_BLOCK);
        BulgingRecipeLoader.bulging(provider, Items.SPIDER_EYE, Items.FERMENTED_SPIDER_EYE);
        BulgingRecipeLoader.bulging(provider, Items.BRAIN_CORAL, Items.BRAIN_CORAL_BLOCK);
        BulgingRecipeLoader.bulging(provider, Items.BUBBLE_CORAL, Items.BUBBLE_CORAL_BLOCK);
        BulgingRecipeLoader.bulging(provider, Items.FIRE_CORAL, Items.FIRE_CORAL_BLOCK);
        BulgingRecipeLoader.bulging(provider, Items.HORN_CORAL, Items.HORN_CORAL_BLOCK);
        BulgingRecipeLoader.bulging(provider, Items.TUBE_CORAL, Items.TUBE_CORAL_BLOCK);
        BulgingRecipeLoader.bulging(provider, Items.DRIED_KELP, Items.KELP);

        BulgingRecipe.builder()
            .cauldron(Blocks.WATER_CAULDRON)
            .requires(Items.RED_MUSHROOM)
            .result(Blocks.RED_MUSHROOM_BLOCK)
            .result(Blocks.MUSHROOM_STEM, 0.1f)
            .save(provider);

        BulgingRecipe.builder()
            .cauldron(Blocks.WATER_CAULDRON)
            .requires(Items.BROWN_MUSHROOM)
            .result(Blocks.BROWN_MUSHROOM_BLOCK)
            .result(Blocks.MUSHROOM_STEM, 0.1f)
            .save(provider);
    }

    @SuppressWarnings("SameParameterValue")
    private static void bulging(RecipeOutput provider, ItemLike input, ItemLike result, int consume) {
        BulgingRecipe.builder()
            .cauldron(Blocks.WATER_CAULDRON)
            .requires(input)
            .result(result)
            .consume(consume)
            .save(provider);
    }

    private static void bulging(RecipeOutput provider, ItemLike input, ItemLike result) {
        BulgingRecipeLoader.bulging(provider, input, result, 0);
    }

    @SuppressWarnings("SameParameterValue")
    private static void bulging(RecipeOutput provider, TagKey<Item> input, ItemLike result, int consume) {
        BulgingRecipe.builder()
            .cauldron(Blocks.WATER_CAULDRON)
            .requires(input)
            .result(result)
            .consume(consume)
            .save(provider);
    }

    @SuppressWarnings("SameParameterValue")
    private static void bulging(RecipeOutput provider, TagKey<Item> input, ItemLike result) {
        BulgingRecipeLoader.bulging(provider, input, result, 0);
    }

    @SuppressWarnings("SameParameterValue")
    private static void crystallize(
        RecipeOutput provider, ItemLike input, ItemLike result, int consume
    ) {
        BulgingRecipe.builder()
            .cauldron(Blocks.POWDER_SNOW_CAULDRON)
            .requires(input)
            .result(result)
            .consume(consume)
            .save(provider);
    }
}

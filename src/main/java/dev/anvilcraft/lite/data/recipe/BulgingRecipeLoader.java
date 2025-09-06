package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.BulgingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

public class BulgingRecipeLoader extends ModRecipeLoader {
    public BulgingRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        this.bulging(Items.DIRT, Items.CLAY);
        this.bulging(Items.CRIMSON_FUNGUS, Items.NETHER_WART_BLOCK);
        this.bulging(Items.WARPED_FUNGUS, Items.WARPED_WART_BLOCK);
        this.bulging(Items.SPIDER_EYE, Items.FERMENTED_SPIDER_EYE);
        this.bulging(Items.BRAIN_CORAL, Items.BRAIN_CORAL_BLOCK);
        this.bulging(Items.BUBBLE_CORAL, Items.BUBBLE_CORAL_BLOCK);
        this.bulging(Items.FIRE_CORAL, Items.FIRE_CORAL_BLOCK);
        this.bulging(Items.HORN_CORAL, Items.HORN_CORAL_BLOCK);
        this.bulging(Items.TUBE_CORAL, Items.TUBE_CORAL_BLOCK);
        this.bulging(Items.DRIED_KELP, Items.KELP);

        BulgingRecipe.builder(this.items)
            .cauldron(Blocks.WATER_CAULDRON)
            .requires(Items.RED_MUSHROOM)
            .result(Blocks.RED_MUSHROOM_BLOCK)
            .result(Blocks.MUSHROOM_STEM, 0.1f)
            .save(this.output);

        BulgingRecipe.builder(this.items)
            .cauldron(Blocks.WATER_CAULDRON)
            .requires(Items.BROWN_MUSHROOM)
            .result(Blocks.BROWN_MUSHROOM_BLOCK)
            .result(Blocks.MUSHROOM_STEM, 0.1f)
            .save(this.output);
    }

    @SuppressWarnings("SameParameterValue")
    private void bulging(ItemLike input, ItemLike result, int consume) {
        BulgingRecipe.builder(this.items).cauldron(Blocks.WATER_CAULDRON).requires(input).result(result).consume(consume).save(this.output);
    }

    private void bulging(ItemLike input, ItemLike result) {
        this.bulging(input, result, 0);
    }

    @SuppressWarnings("SameParameterValue")
    private void bulging(TagKey<Item> input, ItemLike result, int consume) {
        BulgingRecipe.builder(this.items).cauldron(Blocks.WATER_CAULDRON).requires(input).result(result).consume(consume).save(this.output);
    }

    @SuppressWarnings("SameParameterValue")
    private void bulging(TagKey<Item> input, ItemLike result) {
        this.bulging(input, result, 0);
    }

    @SuppressWarnings("SameParameterValue")
    private void crystallize(ItemLike input, ItemLike result, int consume) {
        BulgingRecipe.builder(this.items)
            .cauldron(Blocks.POWDER_SNOW_CAULDRON)
            .requires(input)
            .result(result)
            .consume(consume)
            .save(this.output);
    }
}

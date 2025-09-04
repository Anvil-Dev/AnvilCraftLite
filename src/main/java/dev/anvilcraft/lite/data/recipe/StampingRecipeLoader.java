package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.StampingRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class StampingRecipeLoader {
    public static void init(RecipeOutput provider) {
        stamping(provider, Items.IRON_INGOT, Items.HEAVY_WEIGHTED_PRESSURE_PLATE);
        stamping(provider, Items.GOLD_INGOT, Items.LIGHT_WEIGHTED_PRESSURE_PLATE);
        stamping(provider, Items.SNOWBALL, Items.SNOW);
        stamping(provider, Items.CHERRY_LEAVES, Items.PINK_PETALS);
        StampingRecipe.builder()
            .requires(Items.SUGAR_CANE)
            .result(Items.PAPER)
            .result(Items.SUGAR)
            .save(provider, AnvilCraftLite.of("stamping/paper_from_sugar_cane"));
    }

    @SuppressWarnings("SameParameterValue")
    private static void stamping(RecipeOutput provider, ItemLike input, ItemLike result, int count) {
        StampingRecipe.builder().requires(input).result(result, count).save(provider);
    }

    private static void stamping(RecipeOutput provider, ItemLike input, ItemLike result) {
        stamping(provider, input, result, 1);
    }

    private static void stamping(RecipeOutput provider, TagKey<Item> input, ItemLike result) {
        StampingRecipe.builder().requires(input).result(result, 1).save(provider);
    }
}

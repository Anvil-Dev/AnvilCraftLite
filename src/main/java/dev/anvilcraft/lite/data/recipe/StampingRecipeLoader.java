package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.init.item.ModItems;
import dev.anvilcraft.lite.recipe.anvil.wrap.StampingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class StampingRecipeLoader extends ModRecipeLoader {
    public StampingRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        this.stamping(Items.IRON_INGOT, Items.HEAVY_WEIGHTED_PRESSURE_PLATE);
        this.stamping(Items.GOLD_INGOT, Items.LIGHT_WEIGHTED_PRESSURE_PLATE);
        this.stamping(Items.SNOWBALL, Items.SNOW);
        this.stamping(Items.CHERRY_LEAVES, Items.PINK_PETALS);
        StampingRecipe.builder(this.items)
            .requires(Items.SUGAR_CANE)
            .result(Items.PAPER)
            .result(Items.SUGAR)
            .save(this.output, AnvilCraftLite.of("stamping/paper_from_sugar_cane"));
        StampingRecipe.builder(this.items)
            .requires(ItemTags.LOGS)
            .result(ModItems.RESIN)
            .result(Items.STICK, 8)
            .save(this.output, AnvilCraftLite.of("stamping/resin_from_logs"));
    }

    @SuppressWarnings("SameParameterValue")
    private void stamping(ItemLike input, ItemLike result, int count) {
        StampingRecipe.builder(this.items).requires(input).result(result, count).save(this.output);
    }

    private void stamping(ItemLike input, ItemLike result) {
        this.stamping(input, result, 1);
    }

    private void stamping(TagKey<Item> input, ItemLike result) {
        StampingRecipe.builder(this.items).requires(input).result(result, 1).save(this.output);
    }
}

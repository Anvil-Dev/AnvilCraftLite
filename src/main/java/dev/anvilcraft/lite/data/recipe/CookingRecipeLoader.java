package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.init.item.ModItems;
import dev.anvilcraft.lite.recipe.anvil.wrap.BoilingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.CookingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

public class CookingRecipeLoader extends ModRecipeLoader {
    public CookingRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        this.boiling()
            .requires(ModItems.RESIN)
            .result(Items.SLIME_BALL)
            .unlockedBy("has_resin", this.has(ModItems.RESIN))
            .save(this.output);
    }

    public BoilingRecipe.Builder boiling() {
        return BoilingRecipe.builder(this.items);
    }

    public CookingRecipe.Builder cooking() {
        return CookingRecipe.builder(this.items);
    }
}

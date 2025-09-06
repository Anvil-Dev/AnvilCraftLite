package dev.anvilcraft.lite.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;

public class CookingRecipeLoader extends ModRecipeLoader {
    public CookingRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
    }
}

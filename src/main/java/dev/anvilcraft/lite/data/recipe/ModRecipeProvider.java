package dev.anvilcraft.lite.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        BlockCrushRecipeLoader.init(this.output);
        ItemCrushRecipeLoader.init(this.output);
        UnpackRecipeLoader.init(this.output);
        BlockCompressRecipeLoader.init(this.output);
        ItemCompressRecipeLoader.init(this.output);
        MeshRecipeLoader.init(this.output);
        StampingRecipeLoader.init(this.output);
        BlockSmearRecipeLoader.init(this.output);
        CookingRecipeLoader.init(this.output);
        BulgingRecipeLoader.init(this.output);
        ItemInjectRecipeLoader.init(this.output);
        SqueezingRecipeLoader.init(this.output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> future) {
            super(output, future);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new ModRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "AnvilCraft Lite Recipes";
        }
    }
}

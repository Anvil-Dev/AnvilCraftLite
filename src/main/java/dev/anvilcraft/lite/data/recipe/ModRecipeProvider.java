package dev.anvilcraft.lite.data.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class ModRecipeProvider extends RecipeProvider {
    public final List<ModRecipeLoader> providers = new ArrayList<>();

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        this.addProvider(BlockCrushRecipeLoader::new);
        this.addProvider(ItemCrushRecipeLoader::new);
        this.addProvider(UnpackRecipeLoader::new);
        this.addProvider(BlockCompressRecipeLoader::new);
        this.addProvider(ItemCompressRecipeLoader::new);
        this.addProvider(MeshRecipeLoader::new);
        this.addProvider(CookingRecipeLoader::new);
        this.addProvider(BulgingRecipeLoader::new);
        this.addProvider(StampingRecipeLoader::new);
        this.addProvider(BlockSmearRecipeLoader::new);
        this.addProvider(ItemInjectRecipeLoader::new);
        this.addProvider(SqueezingRecipeLoader::new);
        this.addProvider(VanillaRecipeLoader::new);
        providers.forEach(ModRecipeLoader::buildRecipes);
    }

    public <T extends ModRecipeLoader> void addProvider(BiFunction<HolderLookup.Provider, RecipeOutput, T> provider) {
        this.providers.add(provider.apply(this.registries, this.output));
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

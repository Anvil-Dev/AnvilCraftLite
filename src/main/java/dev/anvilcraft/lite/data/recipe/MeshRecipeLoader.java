package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import dev.anvilcraft.lite.recipe.anvil.wrap.MeshRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

public class MeshRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        MeshRecipe.builder(provider.getItems())
            .requires(Items.GRAVEL)
            .result(Items.GRAVEL, 0.5f)
            .result(Items.FLINT, 0.25f)
            .result(Items.IRON_NUGGET, 0.2f)
            .save(provider.getOutput());

        MeshRecipe.builder(provider.getItems())
            .requires(Items.SAND)
            .result(Items.SAND, 0.5f)
            .result(Items.CLAY_BALL, 0.25f)
            .result(Items.GOLD_NUGGET, 0.05f)
            .save(provider.getOutput());

        MeshRecipe.builder(provider.getItems())
            .requires(Items.SOUL_SAND)
            .result(Items.SOUL_SAND, 0.5f)
            .result(Items.NETHER_WART, 0.005f)
            .save(provider.getOutput());
    }
}

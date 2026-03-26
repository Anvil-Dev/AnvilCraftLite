package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import dev.anvilcraft.lite.recipe.anvil.wrap.SuperHeatingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class SuperHeatingRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        SuperHeatingRecipe.builder(provider.getItems())
            .requires(Blocks.COAL_BLOCK, 8)
            .result(Items.DIAMOND)
            .save(provider.getOutput());
    }
}

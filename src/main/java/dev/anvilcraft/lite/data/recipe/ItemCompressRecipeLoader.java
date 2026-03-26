package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCompressRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ItemCompressRecipeLoader {
    public static void init(RegistrumRecipeProvider provider) {
        ItemCompressRecipe.builder(provider.getItems())
            .requires(Items.BONE, 3)
            .result(new ItemStack(Items.BONE_BLOCK))
            .save(provider.getOutput());
    }
}

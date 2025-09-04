package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCompressRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ItemCompressRecipeLoader {
    public static void init(RecipeOutput provider) {
        ItemCompressRecipe.builder()
            .requires(Items.BONE, 3)
            .result(new ItemStack(Items.BONE_BLOCK))
            .save(provider);
    }
}

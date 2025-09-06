package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.SuperHeatingRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class SuperHeatingRecipeLoader {
    public static void init(RecipeOutput provider) {
        SuperHeatingRecipe.builder()
            .requires(Blocks.COAL_BLOCK, 8)
            .result(Items.DIAMOND)
            .save(provider);
    }
}

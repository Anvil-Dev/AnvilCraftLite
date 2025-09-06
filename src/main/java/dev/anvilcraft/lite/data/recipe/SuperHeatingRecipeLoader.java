package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.SuperHeatingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class SuperHeatingRecipeLoader extends ModRecipeLoader {
    public SuperHeatingRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        SuperHeatingRecipe.builder(this.items)
            .requires(Blocks.COAL_BLOCK, 8)
            .result(Items.DIAMOND)
            .save(this.output);
    }
}

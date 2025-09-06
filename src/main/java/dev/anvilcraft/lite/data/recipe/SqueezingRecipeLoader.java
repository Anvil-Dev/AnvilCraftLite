package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.SqueezingRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class SqueezingRecipeLoader extends ModRecipeLoader {
    public SqueezingRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        this.squeezing(Blocks.WET_SPONGE, Blocks.SPONGE, Blocks.WATER_CAULDRON, 333, "water_from_wet_sponge");
        this.squeezing(Blocks.MOSS_BLOCK, Blocks.MOSS_CARPET, Blocks.WATER_CAULDRON, 333, "water_from_moss_block");
        this.squeezing(Blocks.SNOW_BLOCK, Blocks.ICE, Blocks.POWDER_SNOW_CAULDRON, 333, "power_snow_from_ice");
    }

    public void squeezing(
        Block requires,
        Block result,
        Block cauldron,
        int produce,
        String save
    ) {
        SqueezingRecipe.builder(this.blocks)
            .requires(requires)
            .result(result)
            .transform(cauldron)
            .produce(produce)
            .save(this.output, AnvilCraftLite.of("squeezing/%s".formatted(save)));
    }
}

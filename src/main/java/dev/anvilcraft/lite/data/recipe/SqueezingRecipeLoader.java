package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.SqueezingRecipe;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class SqueezingRecipeLoader {
    public static void init(RecipeOutput provider) {
        squeezing(provider, Blocks.WET_SPONGE, Blocks.SPONGE, Blocks.WATER_CAULDRON, 333, "water_from_wet_sponge");
        squeezing(provider, Blocks.MOSS_BLOCK, Blocks.MOSS_CARPET, Blocks.WATER_CAULDRON, 333, "water_from_moss_block");
        squeezing(provider, Blocks.SNOW_BLOCK, Blocks.ICE, Blocks.POWDER_SNOW_CAULDRON, 333, "power_snow_from_ice");
    }

    public static void squeezing(
        RecipeOutput provider,
        Block requires,
        Block result,
        Block cauldron,
        int produce,
        String save
    ) {
        SqueezingRecipe.builder()
            .requires(requires)
            .result(result)
            .transform(cauldron)
            .produce(produce)
            .save(provider, AnvilCraftLite.of("squeezing/%s".formatted(save)));
    }
}

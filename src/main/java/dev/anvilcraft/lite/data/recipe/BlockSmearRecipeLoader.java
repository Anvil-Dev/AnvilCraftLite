package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.BlockSmearRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockSmearRecipeLoader {
    public static void init(RecipeOutput provider) {
        blockSmear(provider, Blocks.MOSS_BLOCK, Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE);
        blockSmear(provider, Blocks.MOSS_BLOCK, Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS);
        blockSmear(provider, Blocks.MOSS_BLOCK, Blocks.DIRT, Blocks.GRASS_BLOCK);
        BuiltInRegistries.BLOCK.stream()
            .forEach(block -> HoneycombItem.getWaxed(block.defaultBlockState())
                .ifPresent(state -> blockSmear(provider, Blocks.HONEYCOMB_BLOCK, block, state.getBlock())));
    }

    private static void blockSmear(RecipeOutput provider, Block block1, Block block2, Block result) {
        BlockSmearRecipe.builder().input(block1).input(block2).result(result).save(provider);
    }
}

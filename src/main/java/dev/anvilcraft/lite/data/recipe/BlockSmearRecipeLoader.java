package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lite.recipe.anvil.wrap.BlockSmearRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockSmearRecipeLoader extends ModRecipeLoader {
    public BlockSmearRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    public void buildRecipes() {
        this.blockSmear(Blocks.MOSS_BLOCK, Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE);
        this.blockSmear(Blocks.MOSS_BLOCK, Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS);
        this.blockSmear(Blocks.MOSS_BLOCK, Blocks.DIRT, Blocks.GRASS_BLOCK);
        BuiltInRegistries.BLOCK.stream()
            .forEach(block -> HoneycombItem.getWaxed(block.defaultBlockState())
                .ifPresent(state -> this.blockSmear(Blocks.HONEYCOMB_BLOCK, block, state.getBlock())));
    }

    private void blockSmear(Block block1, Block block2, Block result) {
        BlockSmearRecipe.builder(this.blocks).input(block1).input(block2).result(result).save(this.output);
    }
}

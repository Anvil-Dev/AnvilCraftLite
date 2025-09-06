package dev.anvilcraft.lite.dispense;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AnvilBehavior implements ModDispenseItemBehavior {
    @Override
    @SuppressWarnings("resource")
    public ItemStack execute(BlockSource blockSource, ItemStack item) {
        ServerLevel level = blockSource.level();
        BlockState state = blockSource.state();
        Direction value = state.getValue(DispenserBlock.FACING);
        BlockPos pos1 = blockSource.pos().relative(value);
        if (level.getBlockState(pos1).is(BlockTags.REPLACEABLE)) {
            BlockState blockState = Blocks.ANVIL.defaultBlockState();
            if (value != Direction.DOWN && value != Direction.UP) {
                blockState = blockState.setValue(AnvilBlock.FACING, value);
            }
            level.setBlockAndUpdate(pos1, blockState);
            item.shrink(1);
        }
        return item;
    }
}

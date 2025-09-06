package dev.anvilcraft.lite.dispense;

import dev.anvilcraft.lite.init.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MagnetIngotBehavior implements ModDispenseItemBehavior {
    @Override
    @SuppressWarnings("resource")
    public ItemStack execute(BlockSource blockSource, ItemStack item) {
        ServerLevel level = blockSource.level();
        BlockState state = blockSource.state();
        BlockPos pos1 = blockSource.pos().relative(state.getValue(DispenserBlock.FACING));
        if (level.getBlockState(pos1).is(ModBlocks.HOLLOW_MAGNET_BLOCK)) {
            level.setBlockAndUpdate(pos1, ModBlocks.MAGNET_BLOCK.get().defaultBlockState());
            item.shrink(1);
        }
        return item;
    }
}

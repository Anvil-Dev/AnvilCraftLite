package dev.anvilcraft.lite.dispense;

import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PowerSnowBucketBehavior implements ModDispenseItemBehavior {
    @Override
    @SuppressWarnings("resource")
    public ItemStack execute(BlockSource blockSource, ItemStack item) {
        ServerLevel level = blockSource.level();
        BlockState state = blockSource.state();
        BlockPos pos1 = blockSource.pos().relative(state.getValue(DispenserBlock.FACING));
        BlockState blockState = level.getBlockState(pos1);
        if (blockState.is(Blocks.CAULDRON)) {
            BlockState state1 = Blocks.POWDER_SNOW_CAULDRON.defaultBlockState()
                .setValue(LayeredCauldronBlock.LEVEL, 3);
            level.setBlockAndUpdate(pos1, state1);
            return Items.BUCKET.getDefaultInstance();
        } else if (blockState.is(BlockTags.REPLACEABLE)) {
            level.setBlockAndUpdate(pos1, Blocks.POWDER_SNOW.defaultBlockState());
            return Items.BUCKET.getDefaultInstance();
        }
        return item;
    }
}

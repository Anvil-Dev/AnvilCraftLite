package dev.anvilcraft.lite.dispense;

import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.cow.AbstractCow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class EmptyBucketBehavior implements ModDispenseItemBehavior {
    @Override
    @SuppressWarnings("resource")
    public ItemStack execute(BlockSource blockSource, ItemStack item) {
        ServerLevel level = blockSource.level();
        BlockState state = blockSource.state();
        BlockPos pos1 = blockSource.pos().relative(state.getValue(DispenserBlock.FACING));
        BlockState blockState = level.getBlockState(pos1);
        if (blockState.is(Blocks.WATER_CAULDRON) && blockState.getValue(LayeredCauldronBlock.LEVEL) == 3) {
            level.setBlockAndUpdate(pos1, Blocks.CAULDRON.defaultBlockState());
            return ModDispenseItemBehavior.consumeWithRemainder(blockSource, item, Items.WATER_BUCKET.getDefaultInstance());
        } else if (blockState.is(Blocks.WATER) && blockState.getValue(LiquidBlock.LEVEL) == 0) {
            level.setBlockAndUpdate(pos1, Blocks.AIR.defaultBlockState());
            return ModDispenseItemBehavior.consumeWithRemainder(blockSource, item, Items.WATER_BUCKET.getDefaultInstance());
        } else if (blockState.is(Blocks.LAVA_CAULDRON)) {
            level.setBlockAndUpdate(pos1, Blocks.CAULDRON.defaultBlockState());
            return ModDispenseItemBehavior.consumeWithRemainder(blockSource, item, Items.LAVA_BUCKET.getDefaultInstance());
        } else if (blockState.is(Blocks.LAVA) && blockState.getValue(LiquidBlock.LEVEL) == 0) {
            level.setBlockAndUpdate(pos1, Blocks.AIR.defaultBlockState());
            return ModDispenseItemBehavior.consumeWithRemainder(blockSource, item, Items.LAVA_BUCKET.getDefaultInstance());
        } else if (blockState.is(Blocks.POWDER_SNOW_CAULDRON)) {
            level.setBlockAndUpdate(pos1, Blocks.CAULDRON.defaultBlockState());
            return ModDispenseItemBehavior.consumeWithRemainder(blockSource, item, Items.POWDER_SNOW_BUCKET.getDefaultInstance());
        } else if (blockState.is(Blocks.POWDER_SNOW)) {
            level.setBlockAndUpdate(pos1, Blocks.AIR.defaultBlockState());
            return ModDispenseItemBehavior.consumeWithRemainder(blockSource, item, Items.POWDER_SNOW_BUCKET.getDefaultInstance());
        }
        List<AbstractCow> cows = level.getEntities(
            EntityTypeTest.forClass(AbstractCow.class),
            new AABB(pos1.getX(), pos1.getY(), pos1.getZ(), pos1.getX() + 1, pos1.getY() + 1, pos1.getZ() + 1),
            Entity::isAlive
        );
        if (!cows.isEmpty()) {
            return ModDispenseItemBehavior.consumeWithRemainder(blockSource, item, Items.MILK_BUCKET.getDefaultInstance());
        }
        return item;
    }
}

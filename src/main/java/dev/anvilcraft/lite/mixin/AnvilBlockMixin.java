package dev.anvilcraft.lite.mixin;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.block.MagnetBlock;
import dev.anvilcraft.lite.init.block.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AnvilBlock.class)
abstract class AnvilBlockMixin extends FallingBlock {
    public AnvilBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (
            anvilcraftLite$isAttracts(level.getBlockState(pos.above()))
            || !FallingBlock.isFree(level.getBlockState(pos.below()))
            || pos.getY() < level.getMinY()
        ) {
            return;
        }
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(level, pos, state);
        this.falling(fallingBlockEntity);
    }
    @Override
    protected void neighborChanged(
        BlockState state,
        Level level,
        BlockPos pos,
        Block neighborBlock,
        @Nullable Orientation orientation,
        boolean movedByPiston
    ) {
        this.anvilcraftLite$wasAttracted(state, level, pos);
        super.neighborChanged(state, level, pos, neighborBlock, orientation, movedByPiston);
    }

    @Unique
    private boolean anvilcraftLite$isAttracts(BlockState state) {
        return state.is(ModBlockTags.MAGNET) && !state.getValue(MagnetBlock.LIT);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        BlockState state1 = level.getBlockState(pos.above());
        if (!this.anvilcraftLite$isAttracts(state1)) this.anvilcraftLite$wasAttracted(state, level, pos);
    }

    // -1 -56 7, -1 -57 7
    @Unique
    private void anvilcraftLite$wasAttracted(BlockState state, Level level, BlockPos anvil) {
        BlockPos magnet = anvil;
        BlockState aboveState = level.getBlockState(anvil.above());
        if (aboveState.is(ModBlockTags.MAGNET) || aboveState.getBlock() instanceof MagnetBlock) return;
        int distance = AnvilCraftLite.CONFIG.magnetAttractsDistance;
        for (int i = 0; i < distance; i++) {
            magnet = magnet.above();
            BlockState state1 = level.getBlockState(magnet);
            if (!(state1.getBlock() instanceof MagnetBlock) || state1.getValue(MagnetBlock.LIT)) {
                if (level.isEmptyBlock(magnet) || state1.getBlock() instanceof LiquidBlock) {
                    continue;
                } else {
                    return;
                }
            }
            level.destroyBlock(magnet.below(), true);
            level.setBlockAndUpdate(magnet.below(), state);
            level.setBlockAndUpdate(anvil, Blocks.AIR.defaultBlockState());
            return;
        }
    }
}

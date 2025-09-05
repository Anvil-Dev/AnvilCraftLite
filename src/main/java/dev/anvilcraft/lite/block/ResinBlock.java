package dev.anvilcraft.lite.block;

import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ResinBlock extends SlimeBlock {
    public ResinBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSlimeBlock(BlockState state) {
        return true;
    }
}

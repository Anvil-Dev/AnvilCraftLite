package dev.anvilcraft.lite.event;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.api.event.LightningBoltStrikeEvent;
import dev.anvilcraft.lite.init.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = AnvilCraftLite.MOD_ID)
public class LightningEventListener {
    /**
     * 侦听雷击事件
     *
     * @param event 雷击事件
     */
    @SubscribeEvent
    public static void onLightingStrike(LightningBoltStrikeEvent event) {
        BlockPos pos = event.getPos();
        BlockState state = event.getLevel().getBlockState(pos);
        if (state.is(Blocks.LIGHTNING_ROD)) pos = pos.below();
        int depth = AnvilCraftLite.CONFIG.lightningStrikeDepth;
        int radius = AnvilCraftLite.CONFIG.lightningStrikeRadius;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = 0; y < depth; y++) {
                    BlockPos offset = pos.offset(x, -y, z);
                    state = event.getLevel().getBlockState(offset);
                    if (!state.is(Blocks.IRON_BLOCK) && !state.is(ModBlocks.FERRITE_CORE_MAGNET_BLOCK.get()) && !state.is(ModBlocks.MAGNET_BLOCK.get())) {
                        continue;
                    }
                    BlockState state1 = ModBlocks.HOLLOW_MAGNET_BLOCK.get().defaultBlockState();
                    event.getLevel().setBlockAndUpdate(offset, state1);
                }
            }
        }
    }
}

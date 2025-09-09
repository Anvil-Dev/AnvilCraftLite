package dev.anvilcraft.lite.init.block;

import dev.anvilcraft.lite.block.FerriteCoreMagnetBlock;
import dev.anvilcraft.lite.block.HollowMagnetBlock;
import dev.anvilcraft.lite.block.MagnetBlock;
import dev.anvilcraft.lite.block.ResinBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import static dev.anvilcraft.lite.AnvilCraftLite.REGISTER;

public class ModBlocks {
    public static final DeferredBlock<ResinBlock> RESIN_BLOCK = REGISTER.block("resin_block", ResinBlock::new)
        .properties(() -> Blocks.SLIME_BLOCK)
        .lang("Block of Resin")
        .register();

    public static final DeferredBlock<MagnetBlock> MAGNET_BLOCK = REGISTER.block("magnet_block", MagnetBlock::new)
        .properties(() -> Blocks.IRON_BLOCK)
        .lang("Block of Magnet")
        .register();

    public static final DeferredBlock<HollowMagnetBlock> HOLLOW_MAGNET_BLOCK = REGISTER.block("hollow_magnet_block", HollowMagnetBlock::new)
        .properties(() -> Blocks.IRON_BLOCK)
        .lang("Hollowed Block of Magnet")
        .register();

    public static final DeferredBlock<FerriteCoreMagnetBlock> FERRITE_CORE_MAGNET_BLOCK = REGISTER.block(
        "ferrite_core_magnet_block",
        FerriteCoreMagnetBlock::new
        )
        .properties(() -> Blocks.IRON_BLOCK)
        .properties(BlockBehaviour.Properties::randomTicks)
        .lang("Ferrite-Cored Block of Magnet")
        .register();

    public static void init() {
    }
}

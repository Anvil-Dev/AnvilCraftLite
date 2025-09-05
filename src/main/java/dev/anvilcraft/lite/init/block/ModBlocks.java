package dev.anvilcraft.lite.init.block;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.block.FerriteCoreMagnetBlock;
import dev.anvilcraft.lite.block.HollowMagnetBlock;
import dev.anvilcraft.lite.block.MagnetBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks DR = DeferredRegister.createBlocks(AnvilCraftLite.MOD_ID);

    public static final DeferredBlock<Block> RESIN_BLOCK = DR.register("resin_block", () -> new Block(Block.Properties.of()));

    public static final DeferredBlock<Block> MAGNET_BLOCK = DR.register("resin_block", () -> new MagnetBlock(Block.Properties.of()));

    public static final DeferredBlock<Block> HOLLOW_MAGNET_BLOCK = DR.register(
        "hollow_magnet_block",
        () -> new HollowMagnetBlock(Block.Properties.of())
    );

    public static final DeferredBlock<Block> FERRITE_CORE_MAGNET_BLOCK = DR.register(
        "ferrite_core_magnet_block",
        () -> new FerriteCoreMagnetBlock(Block.Properties.of())
    );
}

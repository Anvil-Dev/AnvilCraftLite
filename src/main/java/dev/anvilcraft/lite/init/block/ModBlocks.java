package dev.anvilcraft.lite.init.block;

import dev.anvilcraft.lib.v2.registrum.util.entry.BlockEntry;
import dev.anvilcraft.lite.block.FerriteCoreMagnetBlock;
import dev.anvilcraft.lite.block.HollowMagnetBlock;
import dev.anvilcraft.lite.block.MagnetBlock;
import dev.anvilcraft.lite.block.ResinBlock;
import dev.anvilcraft.lite.init.item.ModItemGroups;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static dev.anvilcraft.lite.AnvilCraftLite.REGISTRUM;

public class ModBlocks {
    static {
        REGISTRUM.defaultCreativeTab(ModItemGroups.ANVILCRAFT_LITE.getKey());
    }

    public static final BlockEntry<ResinBlock> RESIN_BLOCK = REGISTRUM.block("resin_block", ResinBlock::new)
        .initialProperties(() -> Blocks.SLIME_BLOCK)
        .lang("Block of Resin")
        .blockstate(() -> (ctx, generator) -> generator.create(ctx.get(), generator.modLoc(ctx.getName()).withPrefix("block/")))
        .simpleItem()
        .register();

    public static final BlockEntry<MagnetBlock> MAGNET_BLOCK = REGISTRUM.block("magnet_block", MagnetBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .lang("Block of Magnet")
        .blockstate(() -> (ctx, generator) -> generator.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(ctx.get()).with(
                BlockModelGenerators.createBooleanModelDispatch(
                    BlockStateProperties.LIT,
                    BlockModelGenerators.plainVariant(generator.modLoc(ctx.getName()).withPrefix("block/demagnetized_")),
                    BlockModelGenerators.plainVariant(generator.modLoc(ctx.getName()).withPrefix("block/"))
                )
            )
        ))
        .simpleItem()
        .register();

    public static final BlockEntry<HollowMagnetBlock> HOLLOW_MAGNET_BLOCK = REGISTRUM.block("hollow_magnet_block", HollowMagnetBlock::new)
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .lang("Hollowed Block of Magnet")
        .blockstate(() -> (ctx, generator) -> generator.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(ctx.get()).with(
                BlockModelGenerators.createBooleanModelDispatch(
                    BlockStateProperties.LIT,
                    BlockModelGenerators.plainVariant(generator.modLoc(ctx.getName()).withPrefix("block/demagnetized_")),
                    BlockModelGenerators.plainVariant(generator.modLoc(ctx.getName()).withPrefix("block/"))
                )
            )
        ))
        .simpleItem()
        .register();

    public static final BlockEntry<FerriteCoreMagnetBlock> FERRITE_CORE_MAGNET_BLOCK = REGISTRUM.block(
            "ferrite_core_magnet_block",
            FerriteCoreMagnetBlock::new
        )
        .initialProperties(() -> Blocks.IRON_BLOCK)
        .properties(BlockBehaviour.Properties::randomTicks)
        .lang("Ferrite-Cored Block of Magnet")
        .blockstate(() -> (ctx, generator) -> generator.blockStateOutput.accept(
            MultiVariantGenerator.dispatch(ctx.get()).with(
                BlockModelGenerators.createBooleanModelDispatch(
                    BlockStateProperties.LIT,
                    BlockModelGenerators.plainVariant(generator.modLoc(ctx.getName()).withPrefix("block/demagnetized_")),
                    BlockModelGenerators.plainVariant(generator.modLoc(ctx.getName()).withPrefix("block/"))
                )
            )
        ))
        .simpleItem()
        .register();

    public static void init() {
    }
}

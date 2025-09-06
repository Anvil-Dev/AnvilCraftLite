package dev.anvilcraft.lite.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.client.RenderTypeHelper;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.LinkedHashMap;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RenderHelper {

    private static final int MAX_CACHE_SIZE = 64;
    private static final LinkedHashMap<BlockState, BlockEntity> BLOCK_ENTITY_CACHE = new LinkedHashMap<>();
    private static final RandomSource RANDOM = RandomSource.createNewThreadLocalInstance();
    public static final Vector3f L1 = new Vector3f(0.4F, 0.0F, 1.0F).normalize();
    public static final Vector3f L2 = new Vector3f(-0.4F, 1.0F, -0.2F).normalize();

    private static ClientLevel currentClientLevel = null;
    private static LevelLike.AirLevelLike airLevelLike = null;

    public static final BlockRenderFunction SINGLE_BLOCK = (blockState, poseStack, buffers) -> {
        BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();
        dispatcher.renderSingleBlock(
            blockState,
            poseStack,
            buffers,
            0xF000F0,
            OverlayTexture.NO_OVERLAY
        );
        if (Minecraft.getInstance().level == null) return;
        getCachedBlockEntity(blockState).ifPresent(blockEntity -> {
            blockEntity.setLevel(currentClientLevel);
            blockEntity.setBlockState(blockState);
            renderBlockEntity(blockEntity, poseStack, buffers);
        });
    };

    public static void renderBlockWithRotation(
        GuiGraphics guiGraphics,
        BlockState block,
        float x,
        float y,
        float z,
        float scale,
        BlockRenderFunction fn,
        Quaternionf rotationX,
        Quaternionf rotationY
    ) {
        ClientLevel level = Minecraft.getInstance().level;
        if (currentClientLevel != level) {
            airLevelLike = new LevelLike.AirLevelLike(level);
            currentClientLevel = level;
        }
        PoseStack poseStack = new PoseStack();

        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.scale(-scale, -scale, -scale);
        poseStack.translate(-0.5f, -0.5f, 0);
        poseStack.mulPose(rotationX);
        poseStack.translate(0.5F, 0, -0.5F);
        poseStack.mulPose(rotationY);
        poseStack.translate(-0.5F, 0, 0.5F);

        FluidState fluidState = block.getFluidState();
        MultiBufferSource.BufferSource buffers =
            Minecraft.getInstance().renderBuffers().bufferSource();

        fn.renderBlock(block, poseStack, buffers);
        buffers.endBatch();
        if (!fluidState.isEmpty()) {
            if (block.getBlock() instanceof LiquidBlock) {
                block = block.setValue(LiquidBlock.LEVEL, fluidState.getAmount());
            }
            BlockRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();
            blockRenderDispatcher.renderLiquid(
                BlockPos.ZERO,
                airLevelLike,
                buffers.getBuffer(RenderTypeHelper.getEntityRenderType(ItemBlockRenderTypes.getRenderLayer(fluidState))),
                block,
                fluidState
            );
            buffers.endBatch();
        }

        poseStack.popPose();
    }


    public static void renderBlock(
        GuiGraphics guiGraphics,
        BlockState block,
        float x,
        float y,
        float z,
        float scale,
        BlockRenderFunction fn
    ) {
        renderBlockWithRotation(
            guiGraphics,
            block,
            x,
            y,
            z,
            scale,
            fn,
            Axis.XP.rotationDegrees(-30F),
            Axis.YP.rotationDegrees(45f)
        );
    }

    private static Optional<BlockEntity> getCachedBlockEntity(BlockState state) {
        if (!state.hasBlockEntity()) return Optional.empty();
        if (BLOCK_ENTITY_CACHE.containsKey(state)) return Optional.of(BLOCK_ENTITY_CACHE.get(state));
        Optional<BlockEntity> opt = Optional.of(state.getBlock())
            .filter(b -> b instanceof EntityBlock)
            .map(b -> ((EntityBlock) b).newBlockEntity(BlockPos.ZERO, state));
        opt.ifPresent(be -> {
            BLOCK_ENTITY_CACHE.put(state, be);
            if (BLOCK_ENTITY_CACHE.size() > MAX_CACHE_SIZE) {
                BLOCK_ENTITY_CACHE.pollFirstEntry();
            }
        });
        return opt;
    }

    private static void renderBlockEntity(
        BlockEntity blockEntity,
        PoseStack poseStack,
        MultiBufferSource.BufferSource buffers
    ) {
        try {
            Minecraft.getInstance().getBlockEntityRenderDispatcher().render(blockEntity, getPartialTick(), poseStack, buffers);
        } catch (Exception ignored) {
        }
    }

    private static float getPartialTick() {
        return Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(Minecraft.getInstance().isPaused());
    }

    @FunctionalInterface
    public interface BlockRenderFunction {
        void renderBlock(BlockState block, PoseStack poseStack, MultiBufferSource.BufferSource buffers);
    }
}
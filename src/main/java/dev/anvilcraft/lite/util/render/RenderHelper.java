package dev.anvilcraft.lite.util.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import dev.anvilcraft.lite.util.LevelLike;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.entity.state.FallingBlockRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.slf4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RenderHelper {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int MAX_CACHE_SIZE = 64;
    private static final LinkedHashMap<BlockState, BlockEntity> BLOCK_ENTITY_CACHE = new LinkedHashMap<>();
    private static final LinkedHashMap<BlockState, FallingBlockEntity> ENTITIED_BLOCK_CACHE = new LinkedHashMap<>();
    private static final Quaternionf BLOCK_DEFAULT_ROTATION = new Quaternionf().rotationXYZ(
        (float) Math.toRadians(30),
        (float) Math.toRadians(-45),
        (float) Math.PI
    );

    private static ClientLevel currentClientLevel = null;
    public static LevelLike.AirLevelLike airLevelLike = null;

    /**
     * 按方块状态渲染多个从上到下并列的方块
     *
     * @param graphics GuiGraphics
     * @param states   所有方块状态（顺序敏感）
     * @param x        x轴位置
     * @param y        y轴位置
     * @param scale    缩放量
     * @param rotation 旋转量
     */
    public static void renderMultipleBlocks(
        GuiGraphics graphics,
        List<BlockState> states,
        float x,
        float y,
        float scale,
        Quaternionf rotation
    ) {
        ClientLevel level = Minecraft.getInstance().level;
        if (currentClientLevel != level) {
            airLevelLike = new LevelLike.AirLevelLike(level);
            currentClientLevel = level;
        }
        if (states.isEmpty()) return;
        EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        var pose = graphics.pose();
        pose.pushMatrix();
        pose.translate(x, y);
        for (int i = states.size() - 1; i >= 0; i--) {
            BlockState state = states.get(i);
            if (state.isEmpty()) continue;
            FallingBlockEntity block = RenderHelper.getCachedEntitiedBlock(state);
            FallingBlockRenderer renderer = (FallingBlockRenderer) dispatcher.getRenderer(block);
            FallingBlockRenderState renderState = renderer.createRenderState();
            RenderHelper.extractRenderState(dispatcher, block, renderState);
            renderState.hitboxesRenderState = null;
            graphics.submitEntityRenderState(
                renderState,
                scale,
                new Vector3f(0, 0.865f * i, 0),
                rotation,
                null,
                Math.round(x),
                Math.round(y - 2.5f * scale * (states.size() - 1)),
                Math.round(x + 1.5f * scale),
                Math.round(y + 2.5f * scale * states.size())
            );
        }
        pose.popMatrix();
    }


    /**
     * 使用默认旋转量按方块状态渲染多个从上到下并列的方块
     *
     * @param graphics GuiGraphics
     * @param states   所有方块状态（顺序敏感）
     * @param x        x轴位置
     * @param y        y轴位置
     * @param scale    缩放量
     */
    public static void renderMultipleBlocks(GuiGraphics graphics, List<BlockState> states, float x, float y, float scale) {
        RenderHelper.renderMultipleBlocks(graphics, states, x, y, scale, BLOCK_DEFAULT_ROTATION);
    }

    /**
     * 按方块状态渲染单个方块
     *
     * @param graphics GuiGraphics
     * @param x        x轴位置
     * @param y        y轴位置
     * @param scale    缩放量
     * @param rotation 旋转量
     * @param state    方块状态
     */
    public static void renderSingleBlock(
        GuiGraphics graphics,
        BlockState state,
        float x,
        float y,
        float scale,
        Quaternionf rotation
    ) {
        ClientLevel level = Minecraft.getInstance().level;
        if (currentClientLevel != level) {
            airLevelLike = new LevelLike.AirLevelLike(level);
            currentClientLevel = level;
        }
        if (state.isEmpty()) return;
        EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        var pose = graphics.pose();
        pose.pushMatrix();
        pose.translate(x, y);
        FallingBlockEntity block = RenderHelper.getCachedEntitiedBlock(state);
        FallingBlockRenderer renderer = (FallingBlockRenderer) dispatcher.getRenderer(block);
        FallingBlockRenderState renderState = renderer.createRenderState();
        RenderHelper.extractRenderState(dispatcher, block, renderState);
        renderState.hitboxesRenderState = null;
        graphics.submitEntityRenderState(
            renderState,
            scale,
            new Vector3f(0, 0, 0),
            rotation,
            null,
            (int) x,
            (int) y,
            Math.round(x + 1.5f * scale),
            Math.round(y + 2.5f * scale)
        );
        pose.popMatrix();
    }


    /**
     * 使用默认旋转量按方块状态渲染单个方块
     *
     * @param graphics GuiGraphics
     * @param x        x轴位置
     * @param y        y轴位置
     * @param scale    缩放量
     * @param state    方块状态
     */
    public static void renderSingleBlock(GuiGraphics graphics, BlockState state, float x, float y, float scale) {
        RenderHelper.renderSingleBlock(graphics, state, x, y, scale, BLOCK_DEFAULT_ROTATION);
    }

    static FallingBlockEntity getCachedEntitiedBlock(BlockState state) {
        Level level = Minecraft.getInstance().level;
        if (level == null) throw new IllegalStateException("Render block with no level");
        if (ENTITIED_BLOCK_CACHE.containsKey(state)) return ENTITIED_BLOCK_CACHE.get(state);
        FallingBlockEntity entitiedBlock = new FallingBlockEntity(level, 0.5, 0, 0.5, state);
        ENTITIED_BLOCK_CACHE.put(state, entitiedBlock);
        if (ENTITIED_BLOCK_CACHE.size() > MAX_CACHE_SIZE) {
            ENTITIED_BLOCK_CACHE.pollFirstEntry();
        }
        return entitiedBlock;
    }

    public static final Component REPLACE_LEVEL_TAG = Component.literal("AnvilCraft Lite: Replace Level");

    static void extractRenderState(
        EntityRenderDispatcher dispatcher,
        FallingBlockEntity entity,
        FallingBlockRenderState reusedState
    ) {
        reusedState.entityType = entity.getType();
        reusedState.x = entity.getX();
        reusedState.y = entity.getY();
        reusedState.z = entity.getZ();
        reusedState.isInvisible = true;
        reusedState.ageInTicks = entity.tickCount + 1;
        reusedState.boundingBoxWidth = entity.getBbWidth();
        reusedState.boundingBoxHeight = entity.getBbHeight();
        reusedState.eyeHeight = entity.getEyeHeight();
        reusedState.passengerOffset = null;
        reusedState.distanceToCameraSq = dispatcher.distanceToSqr(entity);
        reusedState.nameTag = REPLACE_LEVEL_TAG;
        reusedState.isDiscrete = entity.isDiscrete();
        reusedState.leashStates = null;
        reusedState.displayFireAnimation = entity.displayFireAnimation();
        reusedState.hitboxesRenderState = null;
        reusedState.serverHitboxesRenderState = null;
        reusedState.partialTick = 1;
        reusedState.blockState = entity.getBlockState();
        reusedState.level = airLevelLike;
    }

    static Optional<BlockEntity> getCachedBlockEntity(BlockState state) {
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

    static void renderBlockEntity(
        BlockEntity blockEntity,
        PoseStack poseStack,
        MultiBufferSource.BufferSource buffers
    ) {
        try {
            Minecraft.getInstance().getBlockEntityRenderDispatcher().render(blockEntity, getPartialTick(), poseStack, buffers);
        } catch (Exception e) {
            LOGGER.debug("Failed on render block entity {}.", blockEntity, e);
        }
    }

    private static float getPartialTick() {
        return Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(Minecraft.getInstance().isPaused());
    }
}
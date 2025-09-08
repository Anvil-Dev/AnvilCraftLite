package dev.anvilcraft.lite.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.anvilcraft.lite.util.Util;
import dev.anvilcraft.lite.util.render.RenderHelper;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.entity.state.FallingBlockRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.function.Function;

@Mixin(FallingBlockRenderer.class)
public class FallingBlockRendererMixin {
    @WrapOperation(
        method = "render("
                 + "Lnet/minecraft/client/renderer/entity/state/FallingBlockRenderState;"
                 + "Lcom/mojang/blaze3d/vertex/PoseStack;"
                 + "Lnet/minecraft/client/renderer/MultiBufferSource;"
                 + "I"
                 + ")V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/block/ModelBlockRenderer;tesselateBlock("
                     + "Lnet/minecraft/world/level/BlockAndTintGetter;"
                     + "Ljava/util/List;Lnet/minecraft/world/level/block/state/BlockState;"
                     + "Lnet/minecraft/core/BlockPos;Lcom/mojang/blaze3d/vertex/PoseStack;"
                     + "Ljava/util/function/Function;"
                     + "Z"
                     + "I"
                     + ")V"
        )
    )
    private void replaceLevel(
        ModelBlockRenderer instance,
        BlockAndTintGetter level,
        List<BlockModelPart> parts,
        BlockState state,
        BlockPos flag,
        PoseStack poseStack,
        Function<ChunkSectionLayer, VertexConsumer> function,
        boolean checkSides,
        int packedOverlay,
        Operation<Void> original
    ) {
        BlockAndTintGetter neoLevel = Util.castSafely(level, FallingBlockRenderState.class)
            .filter(renderState -> renderState.nameTag == RenderHelper.REPLACE_LEVEL_TAG)
            .map(it -> (BlockAndTintGetter) RenderHelper.airLevelLike)
            .orElse(level);
        original.call(instance, neoLevel, parts, state, flag, poseStack, function, checkSides, packedOverlay);
    }
}

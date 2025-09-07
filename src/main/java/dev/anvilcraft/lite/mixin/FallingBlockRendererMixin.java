package dev.anvilcraft.lite.mixin;

import dev.anvilcraft.lite.util.Util;
import dev.anvilcraft.lite.util.render.RenderHelper;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.entity.state.FallingBlockRenderState;
import net.minecraft.world.level.BlockAndTintGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FallingBlockRenderer.class)
public class FallingBlockRendererMixin {
    @ModifyArg(
        method = "render(Lnet/minecraft/client/renderer/entity/state/FallingBlockRenderState;"
                 + "Lcom/mojang/blaze3d/vertex/PoseStack;"
                 + "Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/block/ModelBlockRenderer;tesselateBlock("
                     + "Lnet/minecraft/world/level/BlockAndTintGetter;"
                     + "Ljava/util/List;Lnet/minecraft/world/level/block/state/BlockState;"
                     + "Lnet/minecraft/core/BlockPos;Lcom/mojang/blaze3d/vertex/PoseStack;"
                     + "Ljava/util/function/Function;ZI)V"
        ),
        index = 0
    )
    private BlockAndTintGetter replaceLevel(BlockAndTintGetter level) {
        return Util.castSafely(level, FallingBlockRenderState.class)
            .filter(state -> state.nameTag == RenderHelper.REPLACE_LEVEL_TAG)
            .map(it -> (BlockAndTintGetter) RenderHelper.airLevelLike)
            .orElse(level);
    }
}

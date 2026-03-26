package dev.anvilcraft.lite.mixin;

import dev.anvilcraft.lite.util.render.RenderHelper;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.FallingBlockRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LightLayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FallingBlockRenderState.class)
public abstract class FallingBlockRenderStateMixin extends EntityRenderState implements BlockAndTintGetter {
    @Override
    public int getBrightness(LightLayer lightType, BlockPos blockPos) {
        if (this.nameTag == RenderHelper.FULL_LIGHT_TAG) {
            return 15;
        }
        return BlockAndTintGetter.super.getBrightness(lightType, blockPos);
    }

    @Override
    public int getRawBrightness(BlockPos blockPos, int amount) {
        if (this.nameTag == RenderHelper.FULL_LIGHT_TAG) {
            return 15;
        }
        return BlockAndTintGetter.super.getRawBrightness(blockPos, amount);
    }
}

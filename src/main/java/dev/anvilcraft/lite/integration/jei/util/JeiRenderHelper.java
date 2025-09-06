package dev.anvilcraft.lite.integration.jei.util;

import mezz.jei.api.gui.ITickTimer;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;

public class JeiRenderHelper {
    // Animation
    public static float getAnvilAnimationOffset(ITickTimer timer) {
        return timer.getValue() < 30 ? getAnvilAnimationOffset(timer.getValue()) : 8;
    }

    public static float getAnvilAnimationOffset(float time) {
        return (float) Math.sin(time / 30d * 2d * Math.PI + Math.PI / 2) * 8;
    }

    // Arrow
    public static IDrawable getArrowDefault(IGuiHelper helper) {
        return helper.drawableBuilder(TextureConstants.ARROW_DEFAULT, 0, 0, 16, 10)
            .setTextureSize(16, 10)
            .build();
    }

    public static IDrawable getArrowInput(IGuiHelper helper) {
        return helper.drawableBuilder(TextureConstants.ARROW_INPUT, 0, 0, 16, 8)
            .setTextureSize(16, 8)
            .build();
    }

    public static IDrawable getArrowOutput(IGuiHelper helper) {
        return helper.drawableBuilder(TextureConstants.ARROW_OUTPUT, 0, 0, 16, 10)
            .setTextureSize(16, 10)
            .build();
    }

    public static IDrawable getArrowOutputFromBelow(IGuiHelper helper) {
        return helper.drawableBuilder(TextureConstants.ARROW_OUTPUT_FROM_BELOW, 0, 0, 14, 18)
            .setTextureSize(14, 18)
            .build();
    }

    public static IDrawable getArrowBlockConversion(IGuiHelper helper) {
        return helper.drawableBuilder(TextureConstants.ARROW_BLOCK_CONVERSION, 0, 0, 14, 22)
            .setTextureSize(14, 22)
            .build();
    }

    // Slot
    public static IDrawable getSlotDefault(IGuiHelper helper) {
        return helper.drawableBuilder(TextureConstants.SLOT_DEFAULT, 0, 0, 18, 18)
            .setTextureSize(18, 18)
            .build();
    }

    public static IDrawable getSlotChoice(IGuiHelper helper) {
        return helper.drawableBuilder(TextureConstants.SLOT_CHOICE, 0, 0, 18, 18)
            .setTextureSize(18, 18)
            .build();
    }

    public static IDrawable getSlotProbability(IGuiHelper helper) {
        return helper.drawableBuilder(TextureConstants.SLOT_PROBABILITY, 0, 0, 18, 18)
            .setTextureSize(18, 18)
            .build();
    }
}

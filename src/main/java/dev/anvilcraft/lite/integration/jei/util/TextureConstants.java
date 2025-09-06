package dev.anvilcraft.lite.integration.jei.util;

import dev.anvilcraft.lite.AnvilCraftLite;
import net.minecraft.resources.ResourceLocation;

public class TextureConstants {
    public static final String BASE_PATH = "textures/gui/";

    // Arrow
    public static final ResourceLocation ARROW_DEFAULT = AnvilCraftLite.of(BASE_PATH + "sprites/jei/arrow_default.png");
    public static final ResourceLocation ARROW_BLOCK_CONVERSION = AnvilCraftLite.of(BASE_PATH + "sprites/jei/block_conversion.png");
    public static final ResourceLocation ARROW_INPUT = AnvilCraftLite.of(BASE_PATH + "sprites/jei/input.png");
    public static final ResourceLocation ARROW_OUTPUT = AnvilCraftLite.of(BASE_PATH + "sprites/jei/output.png");
    public static final ResourceLocation ARROW_OUTPUT_FROM_BELOW = AnvilCraftLite.of(BASE_PATH + "sprites/jei/output_from_below.png");

    // Slot
    public static final ResourceLocation SLOT_CHOICE = AnvilCraftLite.of(BASE_PATH + "sprites/jei/slot_choice.png");
    public static final ResourceLocation SLOT_DEFAULT = AnvilCraftLite.of(BASE_PATH + "sprites/jei/slot_default.png");
    public static final ResourceLocation SLOT_PROBABILITY = AnvilCraftLite.of(BASE_PATH + "sprites/jei/slot_probability.png");
}

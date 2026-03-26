package dev.anvilcraft.lite.integration.jei.util;

import dev.anvilcraft.lite.AnvilCraftLite;
import net.minecraft.resources.Identifier;

public class TextureConstants {
    public static final String BASE_PATH = "textures/gui/";

    // Arrow
    public static final Identifier ARROW_DEFAULT = AnvilCraftLite.of(BASE_PATH + "sprites/jei/arrow_default.png");
    public static final Identifier ARROW_BLOCK_CONVERSION = AnvilCraftLite.of(BASE_PATH + "sprites/jei/block_conversion.png");
    public static final Identifier ARROW_INPUT = AnvilCraftLite.of(BASE_PATH + "sprites/jei/input.png");
    public static final Identifier ARROW_OUTPUT = AnvilCraftLite.of(BASE_PATH + "sprites/jei/output.png");
    public static final Identifier ARROW_OUTPUT_FROM_BELOW = AnvilCraftLite.of(BASE_PATH + "sprites/jei/output_from_below.png");

    // Slot
    public static final Identifier SLOT_CHOICE = AnvilCraftLite.of(BASE_PATH + "sprites/jei/slot_choice.png");
    public static final Identifier SLOT_DEFAULT = AnvilCraftLite.of(BASE_PATH + "sprites/jei/slot_default.png");
    public static final Identifier SLOT_PROBABILITY = AnvilCraftLite.of(BASE_PATH + "sprites/jei/slot_probability.png");
}

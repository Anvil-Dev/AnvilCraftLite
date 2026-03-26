package dev.anvilcraft.lite;

import dev.anvilcraft.lib.v2.config.BoundedDiscrete;
import dev.anvilcraft.lib.v2.config.Comment;
import dev.anvilcraft.lib.v2.config.Config;
import net.neoforged.fml.config.ModConfig;

@Config(name = AnvilCraftLite.MOD_ID, type = ModConfig.Type.SERVER)
public class AnvilCraftLiteConfig {
    @Comment("Maximum length a magnet attracts")
    @BoundedDiscrete(max = 8, min = 0)
    public int magnetAttractsDistance = 4;

    @Comment("Maximum depth a lightning strike can reach")
    @BoundedDiscrete(max = 16, min = 1)
    public int lightningStrikeDepth = 2;

    @Comment("Maximum radius a lightning strike can reach")
    public int lightningStrikeRadius = 1;

    @Comment("Maximum radius a handheld magnet attracts")
    @BoundedDiscrete(max = 16, min = 1)
    public double magnetItemAttractsRadius = 8;
}

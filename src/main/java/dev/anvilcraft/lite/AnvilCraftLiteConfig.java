package dev.anvilcraft.lite;

import dev.anvilcraft.lib.config.BoundedDiscrete;
import dev.anvilcraft.lib.config.Comment;
import dev.anvilcraft.lib.config.Config;
import net.neoforged.fml.config.ModConfig;

@Config(name = AnvilCraftLite.MOD_ID, type = ModConfig.Type.SERVER)
public class AnvilCraftLiteConfig {
    @Comment("Maximum length a magnet attracts")
    @BoundedDiscrete(max = 8, min = 0)
    public int magnetAttractsDistance;
}

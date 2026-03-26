package dev.anvilcraft.lite.data.lang;

import dev.anvilcraft.lib.v2.config.ConfigData;
import dev.anvilcraft.lib.v2.registrum.providers.RegistrumLangProvider;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.AnvilCraftLiteConfig;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangHandler {
    public static void init(RegistrumLangProvider provider) {
        JeiLang.init(provider);
        ConfigData.readConfigClass(provider, AnvilCraftLiteConfig.class);
    }
}

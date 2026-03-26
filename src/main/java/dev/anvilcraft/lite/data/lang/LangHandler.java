package dev.anvilcraft.lite.data.lang;

import dev.anvilcraft.lib.v2.config.ConfigData;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.AnvilCraftLiteConfig;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangHandler extends LanguageProvider {
    public LangHandler(PackOutput output) {
        super(output, AnvilCraftLite.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        JeiLang.init(this);
        ConfigData.readConfigClass(this, AnvilCraftLiteConfig.class);
        RegisterLang.init(this);
    }
}

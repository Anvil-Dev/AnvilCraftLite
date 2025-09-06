package dev.anvilcraft.lite.data.lang;

import dev.anvilcraft.lite.AnvilCraftLite;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangHandler extends LanguageProvider {
    public LangHandler(PackOutput output) {
        super(output, AnvilCraftLite.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        JeiLang.init(this);
    }
}

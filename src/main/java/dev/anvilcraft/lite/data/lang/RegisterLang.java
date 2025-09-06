package dev.anvilcraft.lite.data.lang;

import dev.anvilcraft.lite.AnvilCraftLite;

public class RegisterLang {
    public static void init(LangHandler provider) {
        AnvilCraftLite.REGISTER.handlerLang(provider);
    }
}

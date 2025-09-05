package dev.anvilcraft.lite;

import com.mojang.logging.LogUtils;
import dev.anvilcraft.lib.config.ConfigManager;
import dev.anvilcraft.lite.init.ModRegister;
import dev.anvilcraft.lite.init.item.ModInits;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(AnvilCraftLite.MOD_ID)
public class AnvilCraftLite {
    public static final String MOD_ID = "anvilcraft_lite";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final AnvilCraftLiteConfig CONFIG = ConfigManager.register(AnvilCraftLite.MOD_ID, AnvilCraftLiteConfig::new);
    public static final ModRegister REGISTER = new ModRegister(AnvilCraftLite.MOD_ID);

    public AnvilCraftLite(IEventBus modEventBus, ModContainer modContainer) {
        ModInits.init(modEventBus);
        REGISTER.init(modEventBus);
    }

    public static ResourceLocation of(String id) {
        return ResourceLocation.fromNamespaceAndPath(AnvilCraftLite.MOD_ID, id);
    }
}

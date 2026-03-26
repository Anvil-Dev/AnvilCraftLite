package dev.anvilcraft.lite;

import com.mojang.logging.LogUtils;
import dev.anvilcraft.lib.v2.config.ConfigManager;
import dev.anvilcraft.lib.v2.integration.IntegrationManager;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.lite.data.AnvilCraftLiteDatagen;
import dev.anvilcraft.lite.init.ModInits;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(AnvilCraftLite.MOD_ID)
public class AnvilCraftLite {
    public static final String MOD_ID = "anvilcraft_lite";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final AnvilCraftLiteConfig CONFIG = ConfigManager.register(AnvilCraftLite.MOD_ID, AnvilCraftLiteConfig::new);
    public static final Registrum REGISTRUM = Registrum.create(AnvilCraftLite.MOD_ID);
    public static IEventBus MOD_BUS = null;

    public static final IntegrationManager INTEGRATION_MANAGER = new IntegrationManager(AnvilCraftLite.MOD_ID);

    public AnvilCraftLite(IEventBus modEventBus, ModContainer ignored) {
        MOD_BUS = modEventBus;
        ModInits.init(modEventBus);
        INTEGRATION_MANAGER.compileContent();
        INTEGRATION_MANAGER.loadAllIntegrations();
        AnvilCraftLiteDatagen.init();
    }

    public static Identifier of(String id) {
        return Identifier.fromNamespaceAndPath(AnvilCraftLite.MOD_ID, id);
    }
}

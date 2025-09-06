package dev.anvilcraft.lite.client;

import dev.anvilcraft.lite.AnvilCraftLite;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = AnvilCraftLite.MOD_ID, dist = Dist.CLIENT)
public class AnvilCraftLiteClient {
    public AnvilCraftLiteClient(IEventBus modBus, ModContainer container) {
        AnvilCraftLite.INTEGRATION_MANAGER.loadAllClientIntegrations();
    }
}

package dev.anvilcraft.lite.client;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.client.renderer.entity.MagnetizedNodeEntityRenderer;
import dev.anvilcraft.lite.init.entity.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(value = AnvilCraftLite.MOD_ID, dist = Dist.CLIENT)
public class AnvilCraftLiteClient {
    public AnvilCraftLiteClient(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::registerEntityRenderers);
        AnvilCraftLite.INTEGRATION_MANAGER.loadAllClientIntegrations();
    }

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.MAGNETIZED_NODE.get(), MagnetizedNodeEntityRenderer::new);
    }
}

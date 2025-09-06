package dev.anvilcraft.lite.client.init;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.client.model.entity.MagnetizedNodeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = AnvilCraftLite.MOD_ID, value = Dist.CLIENT)
public class ModModelLayers {
    public static final ModelLayerLocation MAGNETIZED_NODE = new ModelLayerLocation(AnvilCraftLite.of("magnetized_node"), "main");

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
            MAGNETIZED_NODE,
            MagnetizedNodeModel::createBodyLayer
        );
    }
}

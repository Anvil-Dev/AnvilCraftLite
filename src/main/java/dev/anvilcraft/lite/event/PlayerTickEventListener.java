package dev.anvilcraft.lite.event;

import dev.anvilcraft.lite.item.CrabClawItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class PlayerTickEventListener {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        CrabClawItem.holdingCrabClawIncreasesRange(event.getEntity());
    }
}

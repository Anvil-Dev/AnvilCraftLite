package dev.anvilcraft.lite.init.reicpe;

import net.neoforged.bus.api.IEventBus;

public class ModRecipeInits {
    public static void init(IEventBus modEventBus) {
        ModRecipeTriggers.TRIGGER.register(modEventBus);
        ModRecipePredicateTypes.PREDICATE_TYPE.register(modEventBus);
        ModRecipeOutcomeTypes.OUTCOME_TYPE.register(modEventBus);
        ModRecipeTypes.register(modEventBus);
    }
}

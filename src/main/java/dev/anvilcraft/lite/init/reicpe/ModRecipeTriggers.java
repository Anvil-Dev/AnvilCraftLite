package dev.anvilcraft.lite.init.reicpe;

import dev.anvilcraft.lib.v2.init.LibRegistries;
import dev.anvilcraft.lib.v2.recipe.trigger.IRecipeTrigger;
import dev.anvilcraft.lite.AnvilCraftLite;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeTriggers {
    public static final DeferredRegister<IRecipeTrigger> TRIGGER = DeferredRegister
        .create(LibRegistries.TRIGGER_REGISTRY, AnvilCraftLite.MOD_ID);

    public static final DeferredHolder<IRecipeTrigger, IRecipeTrigger> ON_ANVIL_FALL_ON = TRIGGER.register(
        "on_anvil_fall_on",
        IRecipeTrigger.Impl::new
    );
}

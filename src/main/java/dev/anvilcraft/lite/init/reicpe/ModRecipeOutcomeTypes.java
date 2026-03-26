package dev.anvilcraft.lite.init.reicpe;

import dev.anvilcraft.lib.v2.recipe.init.LibRegistries;
import dev.anvilcraft.lib.v2.recipe.outcome.IRecipeOutcome;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.outcome.DamageAnvil;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeOutcomeTypes {
    public static final DeferredRegister<IRecipeOutcome.Type<?>> OUTCOME_TYPE = DeferredRegister.create(
        LibRegistries.OUTCOME_TYPE_REGISTRY,
        AnvilCraftLite.MOD_ID
    );

    public static final DeferredHolder<IRecipeOutcome.Type<?>, DamageAnvil.Type> DAMAGE_ANVIL = OUTCOME_TYPE.register(
        "damage_anvil",
        DamageAnvil.Type::new
    );
}

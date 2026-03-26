package dev.anvilcraft.lite.init.reicpe;

import dev.anvilcraft.lib.v2.recipe.init.LibRegistries;
import dev.anvilcraft.lib.v2.recipe.predicate.IRecipePredicate;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.predicate.block.HasCauldron;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipePredicateTypes {
    public static final DeferredRegister<IRecipePredicate.Type<?>> PREDICATE_TYPE = DeferredRegister
        .create(LibRegistries.PREDICATE_TYPE_REGISTRY, AnvilCraftLite.MOD_ID);

    public static final DeferredHolder<IRecipePredicate.Type<?>, HasCauldron.Type> HAS_CAULDRON = PREDICATE_TYPE.register(
        "has_cauldron",
        HasCauldron.Type::new
    );
}

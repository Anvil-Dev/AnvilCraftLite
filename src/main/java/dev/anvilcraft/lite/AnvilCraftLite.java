package dev.anvilcraft.lite;

import com.mojang.logging.LogUtils;
import dev.anvilcraft.lite.init.reicpe.ModRecipeInits;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(AnvilCraftLite.MOD_ID)
public class AnvilCraftLite {
    public static final String MOD_ID = "anvilcraft_lite";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AnvilCraftLite(IEventBus modEventBus, ModContainer modContainer) {
        ModRecipeInits.init(modEventBus);
    }

    public static ResourceLocation of(String id) {
        return ResourceLocation.fromNamespaceAndPath(AnvilCraftLite.MOD_ID, id);
    }
}

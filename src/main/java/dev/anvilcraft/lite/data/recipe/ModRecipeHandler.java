package dev.anvilcraft.lite.data.recipe;

import dev.anvilcraft.lib.v2.registrum.providers.generators.RegistrumRecipeProvider;

public class ModRecipeHandler {
    public static void init(RegistrumRecipeProvider provider) {
        SqueezingRecipeLoader.init(provider);
        BlockCompressRecipeLoader.init(provider);
        ItemInjectRecipeLoader.init(provider);
        ItemCrushRecipeLoader.init(provider);
        StampingRecipeLoader.init(provider);
        SuperHeatingRecipeLoader.init(provider);
        CookingRecipeLoader.init(provider);
        BulgingRecipeLoader.init(provider);
        UnpackRecipeLoader.init(provider);
        BlockCrushRecipeLoader.init(provider);
        MeshRecipeLoader.init(provider);
        BlockSmearRecipeLoader.init(provider);
        ItemCompressRecipeLoader.init(provider);
        VanillaRecipeLoader.init(provider);
    }
}

package dev.anvilcraft.lite.init.reicpe;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCompressRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCrushRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.BlockSmearRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.BoilingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.BulgingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.CookingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCompressRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCrushRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemInjectRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.MeshRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.SqueezingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.StampingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.SuperHeatingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.UnpackRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeTypes {
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
        DeferredRegister.create(Registries.RECIPE_TYPE, AnvilCraftLite.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
        DeferredRegister.create(Registries.RECIPE_SERIALIZER, AnvilCraftLite.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<BlockCrushRecipe>> BLOCK_CRUSH_TYPE =
        registerType("block_crush");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BlockCrushRecipe>> BLOCK_CRUSH_SERIALIZER =
        RECIPE_SERIALIZERS.register("block_crush", BlockCrushRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<ItemCrushRecipe>> ITEM_CRUSH_TYPE =
        registerType("item_crush");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ItemCrushRecipe>> ITEM_CRUSH_SERIALIZERS =
        RECIPE_SERIALIZERS.register("item_crush", ItemCrushRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<UnpackRecipe>> UNPACK_TYPE =
        registerType("unpack");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<UnpackRecipe>> UNPACK_SERIALIZERS =
        RECIPE_SERIALIZERS.register("unpack", UnpackRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<BlockCompressRecipe>> BLOCK_COMPRESS_TYPE =
        registerType("block_compress");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BlockCompressRecipe>>
        BLOCK_COMPRESS_SERIALIZER =
        RECIPE_SERIALIZERS.register("block_compress", BlockCompressRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<BlockSmearRecipe>> BLOCK_SMEAR_TYPE =
        registerType("block_smear");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BlockSmearRecipe>>
        BLOCK_SMEAR_SERIALIZER =
        RECIPE_SERIALIZERS.register("block_smear", BlockSmearRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<ItemCompressRecipe>> ITEM_COMPRESS_TYPE =
        registerType("item_compress");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ItemCompressRecipe>>
        ITEM_COMPRESS_SERIALIZER = RECIPE_SERIALIZERS.register("item_compress", ItemCompressRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<MeshRecipe>> MESH_TYPE = registerType("mesh");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MeshRecipe>> MESH_SERIALIZER =
        RECIPE_SERIALIZERS.register("mesh", MeshRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<StampingRecipe>> STAMPING_TYPE =
        registerType("stamping");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<StampingRecipe>> STAMPING_SERIALIZER =
        RECIPE_SERIALIZERS.register("stamping", StampingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<ItemInjectRecipe>> ITEM_INJECT_TYPE =
        registerType("item_inject");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ItemInjectRecipe>> ITEM_INJECT_SERIALIZER =
        RECIPE_SERIALIZERS.register("item_inject", ItemInjectRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<SqueezingRecipe>> SQUEEZING_TYPE =
        registerType("squeezing");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SqueezingRecipe>> SQUEEZING_SERIALIZER =
        RECIPE_SERIALIZERS.register("squeezing", SqueezingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<CookingRecipe>> COOKING_TYPE = registerType("cooking");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CookingRecipe>> COOKING_SERIALIZER =
        RECIPE_SERIALIZERS.register("cooking", CookingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<BoilingRecipe>> BOILING_TYPE = registerType("boiling");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BoilingRecipe>> BOILING_SERIALIZER =
        RECIPE_SERIALIZERS.register("boiling", BoilingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<BulgingRecipe>> BULGING_TYPE = registerType("bulging");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BulgingRecipe>> BULGING_SERIALIZER =
        RECIPE_SERIALIZERS.register("bulging", BulgingRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<SuperHeatingRecipe>> SUPER_HEATING_TYPE =
        registerType("super_heating");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SuperHeatingRecipe>>
        SUPER_HEATING_SERIALIZER = RECIPE_SERIALIZERS.register("super_heating", SuperHeatingRecipe.Serializer::new);

    private static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<T>> registerType(String name) {
        return RECIPE_TYPES.register(name, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return AnvilCraftLite.of(name).toString();
            }
        });
    }
    public static void register(IEventBus bus) {
        RECIPE_TYPES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
    }
}

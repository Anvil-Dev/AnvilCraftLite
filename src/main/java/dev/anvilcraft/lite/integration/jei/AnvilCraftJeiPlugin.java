package dev.anvilcraft.lite.integration.jei;

import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.integration.jei.category.anvil.BlockCompressCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.BlockCrushCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.BlockSmearCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.BoilingCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.BulgingCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.CookingCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.ItemCompressCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.ItemCrushCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.ItemInjectCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.MeshRecipeCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.SqueezingCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.StampingCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.SuperHeatingCategory;
import dev.anvilcraft.lite.integration.jei.category.anvil.UnpackCategory;
import dev.anvilcraft.lite.integration.jei.recipe.MeshRecipeGroup;
import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCompressRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.BlockCrushRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.BlockSmearRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.BoilingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.BulgingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.CookingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCompressRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemCrushRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.ItemInjectRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.SqueezingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.StampingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.SuperHeatingRecipe;
import dev.anvilcraft.lite.recipe.anvil.wrap.UnpackRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.types.IRecipeHolderType;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IAdvancedRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeMap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;

import javax.annotation.ParametersAreNonnullByDefault;

@JeiPlugin
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class AnvilCraftJeiPlugin implements IModPlugin {
    public static RecipeMap recipes = RecipeMap.EMPTY;

    public static final IRecipeType<MeshRecipeGroup> MESH = createIRecipeType("mesh", MeshRecipeGroup.class);

    public static final IRecipeType<RecipeHolder<BlockCompressRecipe>> BLOCK_COMPRESS =
        createRecipeHolderType("block_compress");
    public static final IRecipeType<RecipeHolder<BlockCrushRecipe>> BLOCK_CRUSH = createRecipeHolderType("block_crush");
    public static final IRecipeType<RecipeHolder<BlockSmearRecipe>> BLOCK_SMEAR = createRecipeHolderType("block_smear");
    public static final IRecipeType<RecipeHolder<ItemCrushRecipe>> ITEM_CRUSH = createRecipeHolderType("item_crush");
    public static final IRecipeType<RecipeHolder<ItemInjectRecipe>> ITEM_INJECT = createRecipeHolderType("item_inject");
    public static final IRecipeType<RecipeHolder<ItemCompressRecipe>> ITEM_COMPRESS =
        createRecipeHolderType("item_compress");
    public static final IRecipeType<RecipeHolder<UnpackRecipe>> UNPACK = createRecipeHolderType("unpack");
    public static final IRecipeType<RecipeHolder<CookingRecipe>> COOKING = createRecipeHolderType("cooking");
    public static final IRecipeType<RecipeHolder<BoilingRecipe>> BOILING = createRecipeHolderType("boiling");
    public static final IRecipeType<RecipeHolder<StampingRecipe>> STAMPING = createRecipeHolderType("stamping");
    public static final IRecipeType<RecipeHolder<SuperHeatingRecipe>> SUPER_HEATING =
        createRecipeHolderType("super_heating");
    public static final IRecipeType<RecipeHolder<SqueezingRecipe>> SQUEEZING = createRecipeHolderType("squeezing");
    public static final IRecipeType<RecipeHolder<BulgingRecipe>> BULGING = createRecipeHolderType("bulging");

    @Override
    public ResourceLocation getPluginUid() {
        return AnvilCraftLite.of("jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        MeshRecipeCategory.registerRecipes(registration);
        BlockCompressCategory.registerRecipes(registration);
        BlockCrushCategory.registerRecipes(registration);
        BlockSmearCategory.registerRecipes(registration);
        ItemCrushCategory.registerRecipes(registration);
        SqueezingCategory.registerRecipes(registration);
        ItemInjectCategory.registerRecipes(registration);
        ItemCompressCategory.registerRecipes(registration);
        UnpackCategory.registerRecipes(registration);
        CookingCategory.registerRecipes(registration);
        BoilingCategory.registerRecipes(registration);
        StampingCategory.registerRecipes(registration);
        SuperHeatingCategory.registerRecipes(registration);
        BulgingCategory.registerRecipes(registration);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        MeshRecipeCategory.registerCraftingStations(registration);
        BlockCompressCategory.registerCraftingStations(registration);
        BlockCrushCategory.registerCraftingStations(registration);
        BlockSmearCategory.registerCraftingStations(registration);
        ItemCrushCategory.registerCraftingStations(registration);
        SqueezingCategory.registerCraftingStations(registration);
        ItemInjectCategory.registerCraftingStations(registration);
        ItemCompressCategory.registerCraftingStations(registration);
        UnpackCategory.registerCraftingStations(registration);
        CookingCategory.registerCraftingStations(registration);
        BoilingCategory.registerCraftingStations(registration);
        StampingCategory.registerCraftingStations(registration);
        SuperHeatingCategory.registerCraftingStations(registration);
        BulgingCategory.registerCraftingStations(registration);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registration.addRecipeCategories(new MeshRecipeCategory(guiHelper));
        registration.addRecipeCategories(new BlockCompressCategory(guiHelper));
        registration.addRecipeCategories(new BlockCrushCategory(guiHelper));
        registration.addRecipeCategories(new BlockSmearCategory(guiHelper));
        registration.addRecipeCategories(new ItemCrushCategory(guiHelper));
        registration.addRecipeCategories(new SqueezingCategory(guiHelper));
        registration.addRecipeCategories(new ItemInjectCategory(guiHelper));
        registration.addRecipeCategories(new ItemCompressCategory(guiHelper));
        registration.addRecipeCategories(new UnpackCategory(guiHelper));
        registration.addRecipeCategories(new CookingCategory(guiHelper));
        registration.addRecipeCategories(new BoilingCategory(guiHelper));
        registration.addRecipeCategories(new StampingCategory(guiHelper));
        registration.addRecipeCategories(new SuperHeatingCategory(guiHelper));
        registration.addRecipeCategories(new BulgingCategory(guiHelper));
    }

    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {
        NeoForge.EVENT_BUS.addListener(JeiEventListener::onDatapackSync);
        if (FMLLoader.getDist() != Dist.CLIENT) return;
        NeoForge.EVENT_BUS.addListener(JeiEventListener::onRecipeReceived);
        NeoForge.EVENT_BUS.addListener(JeiEventListener::onLevelUnload);
    }

    public static <T> IRecipeType<T> createIRecipeType(String name, Class<T> clazz) {
        return IRecipeType.create(AnvilCraftLite.of(name), clazz);
    }

    public static <R extends Recipe<?>> IRecipeType<RecipeHolder<R>> createRecipeHolderType(String name) {
        return IRecipeHolderType.create(AnvilCraftLite.of(name));
    }
}

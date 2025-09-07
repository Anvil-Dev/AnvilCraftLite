package dev.anvilcraft.lite.recipe.anvil.wrap;

import dev.anvilcraft.lib.recipe.InWorldRecipe;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.init.item.ModItemTags;
import dev.anvilcraft.lite.mixin.accessor.ShapedRecipeAccessor;
import dev.anvilcraft.lite.mixin.accessor.ShapelessRecipeAccessor;
import dev.anvilcraft.lite.mixin.accessor.SingleItemRecipeAccessor;
import dev.anvilcraft.lite.util.RecipeUtil;
import dev.anvilcraft.lite.util.Util;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.TriState;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.BlastingRecipe;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@SuppressWarnings("DuplicatedCode")
public class VanillaRecipesWrap {
    private static Set<Item> cooked = new HashSet<>();
    private static List<SmeltingRecipe> smeltingRecipes;
    public static List<RecipeHolder<InWorldRecipe>> recipes;

    public static List<RecipeHolder<InWorldRecipe>> init(HolderGetter.Provider registries, Collection<RecipeHolder<?>> recipes) {
        VanillaRecipesWrap.cooked = Collections.synchronizedSet(new HashSet<>());
        VanillaRecipesWrap.smeltingRecipes = Collections.synchronizedList(new ArrayList<>());
        VanillaRecipesWrap.recipes = new ArrayList<>();
        HolderGetter<Item> items = registries.lookupOrThrow(Registries.ITEM);
        for (RecipeHolder<?> recipeHolder : recipes) {
            VanillaRecipesWrap.wrap(items, recipeHolder).ifPresent(VanillaRecipesWrap.recipes::add);
        }
        VanillaRecipesWrap.smeltingRecipes.forEach(recipe -> VanillaRecipesWrap.wrap(items, recipe));
        return VanillaRecipesWrap.recipes;
    }

    public static Optional<RecipeHolder<InWorldRecipe>> wrap(HolderGetter<Item> items, RecipeHolder<?> holder) {
        Recipe<?> recipeRaw = holder.value();
        return switch (recipeRaw) {
            case ShapedRecipe recipe -> {
                ShapedRecipeAccessor accessor = Util.cast(recipe);
                ShapedRecipePattern pattern = recipe.pattern;
                //noinspection ConstantValue
                if (pattern == null) yield Optional.empty();
                if (pattern.height() != pattern.width() || pattern.height() == 1) yield Optional.empty();
                ItemStack result = accessor.getResult();
                if (result == null) yield Optional.empty();
                result = result.copy();
                if (!result.is(ModItemTags.COMPRESS_ITEM)) yield Optional.empty();
                List<Optional<Ingredient>> ingredients = pattern.ingredients();
                Ingredient first = null;
                for (int i = 0, size = pattern.height() * pattern.width(); i < size; i++) {
                    Optional<Ingredient> ingredientOp = ingredients.get(i);
                    if (ingredientOp.isEmpty()) yield Optional.empty();
                    if (first == null) {
                        first = ingredientOp.get();
                    } else if (!first.equals(ingredientOp.get())) {
                        yield Optional.empty();
                    }
                }
                if (first == null) yield Optional.empty();
                yield Optional.of(new RecipeHolder<>(
                    key(
                        "compress_warp_%s_2_%s".formatted(
                            RecipeUtil.getName(first),
                            BuiltInRegistries.ITEM.getKey(result.getItem()).getPath()
                        )
                    ),
                    ItemCompressRecipe.builder(items)
                        .result(result)
                        .requires(RecipeUtil.wrapIngredient(items, first).withCount(pattern.height() * pattern.width()).build())
                        .buildRecipe()
                ));
            }
            case ShapelessRecipe recipe -> {
                ShapelessRecipeAccessor accessor = Util.cast(recipe);
                List<Ingredient> ingredients = accessor.getIngredients();
                if (ingredients.isEmpty()) yield Optional.empty();
                Ingredient first = ingredients.getFirst();
                ItemStack result = accessor.getResult();
                if (result == null) yield Optional.empty();
                result = result.copy();
                if (ingredients.size() == 1) {
                    yield Optional.of(new RecipeHolder<>(
                        key(
                            "unpack_warp_%s_2_%s".formatted(
                                RecipeUtil.getName(first),
                                BuiltInRegistries.ITEM.getKey(result.getItem()).getPath()
                            )
                        ),
                        UnpackRecipe.builder(items)
                            .result(result)
                            .requires(RecipeUtil.wrapIngredient(items, first).build())
                            .buildRecipe()
                    ));
                }
                if (ingredients.size() != 4 && ingredients.size() != 9) yield Optional.empty();
                if (!result.is(Tags.Items.STORAGE_BLOCKS) && !result.is(ModItemTags.COMPRESS_ITEM)) yield Optional.empty();
                for (Ingredient ingredient : ingredients) {
                    if (!ingredient.equals(first)) yield Optional.empty();
                }
                yield Optional.of(new RecipeHolder<>(
                    key(
                        "compress_warp_%s_2_%s".formatted(
                            RecipeUtil.getName(first),
                            BuiltInRegistries.ITEM.getKey(result.getItem()).getPath()
                        )
                    ),
                    ItemCompressRecipe.builder(items)
                        .result(result)
                        .requires(RecipeUtil.wrapIngredient(items, first).withCount(ingredients.size()).build())
                        .buildRecipe()
                ));
            }
            case BlastingRecipe recipe -> {
                SingleItemRecipeAccessor accessor = Util.cast(recipe);
                ItemStack result = accessor.getResult();
                if (result == null) yield Optional.empty();
                result = result.copy();
                Ingredient input = accessor.getInput();
                boolean boost = RecipeUtil.testIngredient(
                    input,
                    itemHolder -> itemHolder.is(ModItemTags.SUPER_HEATING_BOOST_PRODUCTION)
                ) == TriState.TRUE;
                VanillaRecipesWrap.cooked.add(result.getItem());
                yield Optional.of(new RecipeHolder<>(
                    key(
                        "super_heating_warp_%s_2_%s".formatted(
                            RecipeUtil.getName(input),
                            BuiltInRegistries.ITEM.getKey(result.getItem()).getPath()
                        )
                    ),
                    SuperHeatingRecipe.builder(items)
                        .result(result.copyWithCount(boost ? 2 : 1))
                        .requires(RecipeUtil.wrapIngredient(items, input).build())
                        .buildRecipe()
                ));
            }
            case SmokingRecipe recipe -> {
                SingleItemRecipeAccessor accessor = Util.cast(recipe);
                ItemStack result = accessor.getResult();
                if (result == null) yield Optional.empty();
                result = result.copy();
                Ingredient input = accessor.getInput();
                VanillaRecipesWrap.cooked.add(result.getItem());
                yield Optional.of(new RecipeHolder<>(
                    key(
                        "smoking_warp_%s_2_%s".formatted(
                            RecipeUtil.getName(input),
                            BuiltInRegistries.ITEM.getKey(result.getItem()).getPath()
                        )
                    ),
                    CookingRecipe.builder(items)
                        .result(result)
                        .requires(RecipeUtil.wrapIngredient(items, input).build())
                        .buildRecipe()
                ));
            }
            case CampfireCookingRecipe recipe -> {
                SingleItemRecipeAccessor accessor = Util.cast(recipe);
                ItemStack result = accessor.getResult();
                if (result == null) yield Optional.empty();
                result = result.copy();
                Ingredient input = accessor.getInput();
                VanillaRecipesWrap.cooked.add(result.getItem());
                yield Optional.of(new RecipeHolder<>(
                    key(
                        "cooking_warp_%s_2_%s".formatted(
                            RecipeUtil.getName(input),
                            BuiltInRegistries.ITEM.getKey(result.getItem()).getPath()
                        )
                    ),
                    CookingRecipe.builder(items)
                        .result(result)
                        .requires(RecipeUtil.wrapIngredient(items, input).build())
                        .buildRecipe()
                ));
            }
            case SmeltingRecipe recipe -> {
                VanillaRecipesWrap.smeltingRecipes.add(recipe);
                yield Optional.empty();
            }
            default -> Optional.empty();
        };
    }

    public static void wrap(HolderGetter<Item> items, SmeltingRecipe recipe) {
        SingleItemRecipeAccessor accessor = Util.cast(recipe);
        ItemStack result = accessor.getResult();
        if (result == null) return;
        result = result.copy();
        if (VanillaRecipesWrap.cooked.contains(result.getItem())) return;
        Ingredient input = accessor.getInput();
        VanillaRecipesWrap.recipes.add(new RecipeHolder<>(
            key("heating_warp_%s_2_%s".formatted(RecipeUtil.getName(input), BuiltInRegistries.ITEM.getKey(result.getItem()).getPath())),
            SuperHeatingRecipe.builder(items)
                .result(result)
                .requires(RecipeUtil.wrapIngredient(items, input).build())
                .buildRecipe()
        ));
    }

    public static ResourceKey<Recipe<?>> key(String path) {
        return ResourceKey.create(Registries.RECIPE, AnvilCraftLite.of(path));
    }
}

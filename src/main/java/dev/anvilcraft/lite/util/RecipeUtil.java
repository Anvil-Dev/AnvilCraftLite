package dev.anvilcraft.lite.util;

import com.mojang.datafixers.util.Either;
import dev.anvilcraft.lib.recipe.component.ItemIngredientPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.TriState;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Predicate;

public final class RecipeUtil {
    private RecipeUtil() {
    }

    public static ItemIngredientPredicate.Builder wrapIngredient(HolderGetter<Item> items, Ingredient ingredient) {
        if (ingredient.isCustom()) return ItemIngredientPredicate.Builder.item(items);
        Either<TagKey<Item>, List<Holder<Item>>> data = ingredient.getValues().unwrap();
        final ItemIngredientPredicate.Builder[] result = new ItemIngredientPredicate.Builder[1];
        data.ifLeft(tag -> result[0] = ItemIngredientPredicate.of(items, tag));
        if (result[0] != null) return result[0];
        data.ifRight(holders -> result[0] = ItemIngredientPredicate.of(
            items,
            holders.stream().map(Holder::value).toArray(ItemLike[]::new)
        ));
        if (result[0] != null) return result[0];
        return ItemIngredientPredicate.Builder.item(items);
    }

    public static String getName(Ingredient ingredient) {
        if (ingredient.isCustom()) return "empty";
        Either<TagKey<Item>, List<Holder<Item>>> data = ingredient.getValues().unwrap();
        final String[] result = new String[1];
        data.ifLeft(tag -> result[0] = tag.location().getPath().replace("/", "_"));
        if (result[0] != null) return result[0];
        data.ifRight(holders -> result[0] = BuiltInRegistries.ITEM.getKey(holders.getFirst().value()).getPath());
        if (result[0] != null) return result[0];
        return "empty";
    }

    public static TriState testIngredient(Ingredient ingredient, Predicate<Holder<Item>> tester) {
        if (ingredient.isCustom()) return TriState.DEFAULT;
        Either<TagKey<Item>, List<Holder<Item>>> data = ingredient.getValues().unwrap();
        final TriState[] result = new TriState[] {TriState.DEFAULT};
        data.ifLeft(tag -> {
            for (Holder<Item> holder : BuiltInRegistries.ITEM.getOrThrow(tag)) {
                if (!tester.test(holder)) {
                    result[0] = TriState.FALSE;
                    return;
                }
            }
            result[0] = TriState.TRUE;
        });
        if (result[0] != TriState.DEFAULT) return result[0];
        data.ifRight(tag -> {
            for (Holder<Item> holder : tag) {
                if (!tester.test(holder)) {
                    result[0] = TriState.FALSE;
                    return;
                }
            }
            result[0] = TriState.TRUE;
        });
        if (result[0] != TriState.DEFAULT) return result[0];
        return TriState.DEFAULT;
    }
}

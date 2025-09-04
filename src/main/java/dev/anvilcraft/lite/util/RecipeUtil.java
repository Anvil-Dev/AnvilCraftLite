package dev.anvilcraft.lite.util;

import net.minecraft.core.NonNullList;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.display.RecipeDisplay;

import java.util.List;

public record RecipeUtil(Recipe<?> recipe) {
    public ItemStack getResultItem(){
        for (RecipeDisplay display : recipe.display()) {
            return display.result().resolveForFirstStack(ContextMap.EMPTY);
        }
        return ItemStack.EMPTY;
    }

    public NonNullList<Ingredient> getIngredients() {
        for (RecipeDisplay display : recipe.display()) {

        }
        return null;
    }
}

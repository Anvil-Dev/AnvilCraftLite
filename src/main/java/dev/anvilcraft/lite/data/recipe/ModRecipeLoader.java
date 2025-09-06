package dev.anvilcraft.lite.data.recipe;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public abstract class ModRecipeLoader extends RecipeProvider {
    protected final HolderGetter<Item> items;
    protected final HolderGetter<Block> blocks;

    public ModRecipeLoader(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
        this.items = registries.lookupOrThrow(Registries.ITEM);
        this.blocks = registries.lookupOrThrow(Registries.BLOCK);
    }

    @Override
    public abstract void buildRecipes();
}

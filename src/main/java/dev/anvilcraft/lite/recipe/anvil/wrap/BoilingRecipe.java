package dev.anvilcraft.lite.recipe.anvil.wrap;

import dev.anvilcraft.lib.recipe.component.BlockStatePredicate;
import dev.anvilcraft.lib.recipe.component.ChanceItemStack;
import dev.anvilcraft.lib.recipe.component.ItemIngredientPredicate;
import dev.anvilcraft.lite.init.reicpe.ModRecipeTypes;
import dev.anvilcraft.lite.recipe.component.HasCauldronSimple;
import lombok.Getter;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * 煮沸配方类
 * <p>
 * 该配方用于在铁砧下落时煮沸物品，需要在铁砧下方放置炼药锅和点燃的营火作为触发条件
 * </p>
 */
@Getter
public class BoilingRecipe extends AbstractProcessRecipe<BoilingRecipe> {
    /**
     * 构造一个煮沸配方
     *
     * @param itemIngredients 物品原料列表
     * @param results         结果物品列表
     */
    public BoilingRecipe(
        List<ItemIngredientPredicate> itemIngredients,
        List<ChanceItemStack> results
    ) {
        //noinspection DataFlowIssue
        super(
            new Property()
                .setItemInputOffset(new Vec3(0.0, -0.375, 0.0))
                .setItemInputRange(new Vec3(0.75, 0.75, 0.75))
                .setInputItems(itemIngredients)
                .setItemOutputOffset(new Vec3(0.0, -0.75, 0.0))
                .setResultItems(results)
                .setCauldronOffset(new Vec3i(0, -1, 0))
                .setHasCauldron(
                    HasCauldronSimple
                        .fluid(ResourceLocation.withDefaultNamespace("water"))
                        .build()
                )
                .setBlockInputOffset(new Vec3i(0, -2, 0))
                .setInputBlocks(
                    BlockStatePredicate.builder(null)
                        .of(Blocks.CAMPFIRE)
                        .with(CampfireBlock.LIT, true)
                        .build()
                )
        );
    }

    @Override
    public RecipeSerializer<BoilingRecipe> getSerializer() {
        return ModRecipeTypes.BOILING_SERIALIZER.get();
    }

    @Override
    public RecipeType<BoilingRecipe> getType() {
        return ModRecipeTypes.BOILING_TYPE.get();
    }

    /**
     * 创建一个构建器实例
     *
     * @return 构建器实例
     */
    public static Builder builder(HolderGetter<Item> getter) {
        return new Builder(getter);
    }

    /**
     * 煮沸配方序列化器
     */
    public static class Serializer extends AbstractSerializer<BoilingRecipe> {
        @Override
        protected BoilingRecipe of(List<ItemIngredientPredicate> itemIngredients, List<ChanceItemStack> results) {
            return new BoilingRecipe(itemIngredients, results);
        }
    }

    /**
     * 煮沸配方构建器
     */
    public static class Builder extends SimpleAbstractBuilder<BoilingRecipe, Builder> {
        protected Builder(HolderGetter<Item> getter) {
            super(getter);
        }

        @Override
        public String getType() {
            return "boiling";
        }

        @Override
        protected BoilingRecipe of(List<ItemIngredientPredicate> itemIngredients, List<ChanceItemStack> results) {
            return new BoilingRecipe(itemIngredients, results);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
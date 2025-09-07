package dev.anvilcraft.lite.recipe.anvil.wrap;

import dev.anvilcraft.lib.recipe.component.BlockStatePredicate;
import dev.anvilcraft.lib.recipe.component.ChanceItemStack;
import dev.anvilcraft.lib.recipe.component.ItemIngredientPredicate;
import dev.anvilcraft.lite.init.reicpe.ModRecipeTypes;
import dev.anvilcraft.lite.recipe.component.HasCauldronSimple;
import lombok.Getter;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * 超级加热配方类
 * <p>
 * 该配方用于在铁砧下落时加热物品，需要在铁砧下方放置炼药锅合熔岩炼药锅作为触发条件
 * </p>
 */
@Getter
public class SuperHeatingRecipe extends AbstractProcessRecipe<SuperHeatingRecipe> {

    /**
     * 构造一个物品压缩配方
     *
     * @param itemIngredients 物品原料列表
     * @param results         结果物品列表
     */
    public SuperHeatingRecipe(
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
                .setHasCauldron(HasCauldronSimple.empty().build())
                .setBlockInputOffset(new Vec3i(0, -2, 0))
                .setInputBlocks(BlockStatePredicate.builder(null).of(Blocks.LAVA_CAULDRON).build())
        );
    }

    @Override
    public RecipeSerializer<SuperHeatingRecipe> getSerializer() {
        return ModRecipeTypes.SUPER_HEATING_SERIALIZER.get();
    }

    @Override
    public RecipeType<SuperHeatingRecipe> getType() {
        return ModRecipeTypes.SUPER_HEATING_TYPE.get();
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
     * 物品压缩配方序列化器
     */
    public static class Serializer extends AbstractSerializer<SuperHeatingRecipe> {
        @Override
        protected SuperHeatingRecipe of(List<ItemIngredientPredicate> itemIngredients, List<ChanceItemStack> results) {
            return new SuperHeatingRecipe(itemIngredients, results);
        }
    }

    /**
     * 物品压缩配方构建器
     */
    public static class Builder extends SimpleAbstractBuilder<SuperHeatingRecipe, Builder> {
        protected Builder(HolderGetter<Item> getter) {
            super(getter);
        }

        @Override
        public String getType() {
            return "super_heating";
        }

        @Override
        protected SuperHeatingRecipe of(List<ItemIngredientPredicate> itemIngredients, List<ChanceItemStack> results) {
            return new SuperHeatingRecipe(itemIngredients, results);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
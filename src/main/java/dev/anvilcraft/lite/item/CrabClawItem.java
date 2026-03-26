package dev.anvilcraft.lite.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import dev.anvilcraft.lite.AnvilCraftLite;
import dev.anvilcraft.lite.init.item.ModItems;
import dev.anvilcraft.lite.item.better.BetterItem;
import dev.anvilcraft.lite.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CrabClawItem extends BetterItem {
    public static final AttributeModifier RANGE_ATTRIBUTE_MODIFIER = new AttributeModifier(
        AnvilCraftLite.of("range_modifier"),
        3,
        AttributeModifier.Operation.ADD_VALUE
    );
    private static final Supplier<Multimap<Holder<Attribute>, AttributeModifier>> RANGE_MODIFIER_SUPPLIER = Suppliers.memoize(() -> ImmutableMultimap.of(
            Attributes.BLOCK_INTERACTION_RANGE, RANGE_ATTRIBUTE_MODIFIER,
            Attributes.ENTITY_INTERACTION_RANGE, RANGE_ATTRIBUTE_MODIFIER
        ));
    public static final String CRAB_CLAW_MARKER = "crabClaw";
    public static final String DUAL_CRAB_CLAW_MARKER = "dualCrabClaw";

    public CrabClawItem(Properties properties) {
        super(properties);
    }

    /**
     * 蟹钳增加交互距离
     */
    public static void holdingCrabClawIncreasesRange(LivingEntity entity) {
        if (!(entity instanceof Player player)) return;
        if (entity.level().isClientSide()) return;
        CompoundTag customData = entity.getPersistentData();
        boolean inOffHand = player.getOffhandItem().is(ModItems.CRAB_CLAW);
        boolean inMainHand = player.getMainHandItem().is(ModItems.CRAB_CLAW);
        boolean holdingDualCrabClaw = inOffHand && inMainHand;
        boolean holdingCrabClaw = inOffHand || inMainHand;
        boolean wasHoldingCrabClaw = customData.contains(CRAB_CLAW_MARKER);
        boolean wasHoldingDualCrabClaw = customData.contains(DUAL_CRAB_CLAW_MARKER);
        if (!holdingCrabClaw) {
            player.getAttributes().removeAttributeModifiers(RANGE_MODIFIER_SUPPLIER.get());
            if (wasHoldingCrabClaw) {
                customData.remove(CRAB_CLAW_MARKER);
            }
        } else {
            player.getAttributes().addTransientAttributeModifiers(RANGE_MODIFIER_SUPPLIER.get());
            if (!wasHoldingDualCrabClaw) {
                customData.putBoolean(CRAB_CLAW_MARKER, true);
            }
        }

        if (!holdingDualCrabClaw) {
            if (wasHoldingDualCrabClaw) {
                customData.remove(DUAL_CRAB_CLAW_MARKER);
            }
        } else {
            if (!wasHoldingDualCrabClaw) {
                customData.putBoolean(DUAL_CRAB_CLAW_MARKER, true);
            }
        }
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!(target instanceof Shulker shulker) || !shulker.isAlive()) return InteractionResult.PASS;
        if (!player.level().isClientSide()) {
            Optional.ofNullable(shulker.getAttribute(Attributes.ARMOR))
                .ifPresent(attribute -> attribute.removeModifier(Shulker.COVERED_ARMOR_MODIFIER_ID));
            shulker.getEntityData().set(Shulker.DATA_PEEK_ID, (byte) 100);
        }

        return Util.sidedSuccess(player.level().isClientSide());

    }

    @Override
    public void appendTooltip(
        ItemStack stack,
        TooltipContext context,
        TooltipDisplay display,
        Consumer<Component> adder,
        TooltipFlag flag
    ) {
        adder.accept(CommonComponents.EMPTY);
        adder.accept(Component.translatable("item.modifiers.hand").withStyle(ChatFormatting.GRAY));
        adder.accept(Component.translatable(
            "attribute.modifier.plus." + RANGE_ATTRIBUTE_MODIFIER.operation().id(),
            ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(RANGE_ATTRIBUTE_MODIFIER.amount()),
            Component.translatable(Attributes.BLOCK_INTERACTION_RANGE.value().getDescriptionId())
        ).withStyle(Attributes.BLOCK_INTERACTION_RANGE.value().getStyle(true)));
        adder.accept(Component.translatable(
            "attribute.modifier.plus." + RANGE_ATTRIBUTE_MODIFIER.operation().id(),
            ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(RANGE_ATTRIBUTE_MODIFIER.amount()),
            Component.translatable(Attributes.ENTITY_INTERACTION_RANGE.value().getDescriptionId())
        ).withStyle(Attributes.ENTITY_INTERACTION_RANGE.value().getStyle(true)));
    }
}

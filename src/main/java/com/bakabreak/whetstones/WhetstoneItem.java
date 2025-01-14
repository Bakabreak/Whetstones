package com.bakabreak.whetstones;

import com.bakabreak.whetstones.extensions.ItemExtension;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.item.BaseItem;
import com.supermartijn642.core.item.ItemProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public class WhetstoneItem extends BaseItem {
    private final WhetstoneTiers tier;

    public WhetstoneItem(ItemProperties properties, WhetstoneTiers tier) {
        super(properties);
        this.tier = tier;
    }

    @Override
    protected void appendItemInformation(ItemStack stack, Consumer<Component> info, boolean advanced) {
        info.accept(this.tier.getDescriptionTranslation());
        Component durability = TextComponents.number(stack.getMaxDamage() - stack.getDamageValue()).color(ChatFormatting.GOLD).get();
        if (stack.getDamageValue() > 0)
            info.accept(TextComponents.translation("whetstones.item.description.durability.used", durability).color(ChatFormatting.GRAY).get());
        else
            info.accept(TextComponents.translation("whetstones.item.description.durability", durability).color(ChatFormatting.GRAY).get());
    }

    @Override
    public ItemUseResult interact(ItemStack stack, Player player, InteractionHand hand, Level level) {
        ItemStack repairItem = player.getItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
        if (repairItem.isDamageableItem() && repairItem.isDamaged() && repairItem.has(DataComponents.REPAIRABLE)) {
            Tool tool = repairItem.get(DataComponents.TOOL);
            if (tool != null || !WhetstonesConfig.onlyTools.get()) {
                ToolMaterial toolMaterial = ((ItemExtension) repairItem.getItem()).whetstones_getToolMaterial();
                //noinspection DataFlowIssue
                int durability = repairItem.get(DataComponents.MAX_DAMAGE);
                if (!WhetstonesConfig.onlyLowerTiers.get() || (toolMaterial != null ? TierHelper.canRepairTier(this.tier.tier, toolMaterial) : TierHelper.canRepairTier(this.tier.tier, durability))) {
                    int repairAmount = (int) Math.max(1, Math.min(repairItem.getDamageValue(), stack.getMaxDamage() / 75d));
                    repairItem.setDamageValue(repairItem.getDamageValue() - repairAmount);
                    if (!player.isCreative())
                        stack.hurtAndBreak(repairAmount, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
                    return ItemUseResult.consume(stack);
                } else
                    player.displayClientMessage(TextComponents.translation("whetstones.item.wrong_tier").color(ChatFormatting.RED).get(), true);
            }
        }
        return ItemUseResult.fail(stack);
    }
}

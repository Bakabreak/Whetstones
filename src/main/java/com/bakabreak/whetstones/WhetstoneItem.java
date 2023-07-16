package com.bakabreak.whetstones;

import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.item.BaseItem;
import com.supermartijn642.core.item.ItemProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.TierSortingRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class WhetstoneItem extends BaseItem {
    private final WhetstoneTiers tier;

    public WhetstoneItem(ItemProperties properties, WhetstoneTiers tier) {
        super(properties);
        this.tier = tier;
    }

    @Override
    protected void appendItemInformation(ItemStack stack, @Nullable BlockGetter level, Consumer<Component> info, boolean advanced) {
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
        if (repairItem.getItem().canBeDepleted() && repairItem.isDamaged() && (!WhetstonesConfig.onlyTools.get() || (repairItem.getItem() instanceof TieredItem && (!WhetstonesConfig.onlyLowerTiers.get() || (this.tier.tier == null || this.tier.tier == ((TieredItem) repairItem.getItem()).getTier() || TierSortingRegistry.getTiersLowerThan(this.tier.tier).contains(((TieredItem) repairItem.getItem()).getTier())))))) {

            int repairAmount = (int) Math.max(1, Math.min(repairItem.getDamageValue(), stack.getMaxDamage() / 75d));
            repairItem.setDamageValue(repairItem.getDamageValue() - repairAmount);
            if (!player.isCreative())
                stack.hurtAndBreak(repairAmount, player, p -> p.broadcastBreakEvent(hand));
            return ItemUseResult.consume(stack);
        }
        return ItemUseResult.fail(stack);
    }
}

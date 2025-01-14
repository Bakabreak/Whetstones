package com.bakabreak.whetstones;

import net.minecraft.world.item.Tier;

public class TierHelper {

    public static boolean canRepairTier(Tier whetstoneTier, Tier toolTier) {
        return getTierValue(whetstoneTier) >= getTierValue(toolTier);
    }

    public static float getTierValue(Tier tier) {
        return (float) Math.sqrt(tier.getUses())
                + tier.getSpeed() * 1.8f
                + tier.getAttackDamageBonus()
                + tier.getEnchantmentValue() / 3f;
    }
}

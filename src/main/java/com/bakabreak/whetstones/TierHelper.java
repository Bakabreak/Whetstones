package com.bakabreak.whetstones;

import net.minecraft.world.item.ToolMaterial;

public class TierHelper {

    public static boolean canRepairTier(ToolMaterial whetstoneTier, ToolMaterial toolTier) {
        return getTierValue(whetstoneTier) >= getTierValue(toolTier);
    }

    public static boolean canRepairTier(ToolMaterial whetstoneTier, int durability) {
        return getTierValue(whetstoneTier) >= getTierValue(durability);
    }

    private static float getTierValue(ToolMaterial tier) {
        return (float) Math.sqrt(tier.durability())
                + tier.speed() * 1.8f
                + tier.attackDamageBonus()
                + tier.enchantmentValue() / 3f;
    }

    private static float getTierValue(int durability) {
        return (float) Math.sqrt(durability) * 1.5f;
    }
}

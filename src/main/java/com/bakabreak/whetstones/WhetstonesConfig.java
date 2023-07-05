package com.bakabreak.whetstones;

import com.supermartijn642.configlib.ConfigBuilder;
import com.supermartijn642.configlib.api.ConfigBuilders;
import com.supermartijn642.configlib.api.IConfigBuilder;

import java.util.function.Supplier;

public class WhetstonesConfig {
    public static final Supplier<Integer> woodenWhetstoneDurability;
    public static final Supplier<Integer> stoneWhetstoneDurability;
    public static final Supplier<Integer> ironWhetstoneDurability;
    public static final Supplier<Integer> goldenWhetstoneDurability;
    public static final Supplier<Integer> diamondWhetstoneDurability;
    public static final Supplier<Integer> netheriteWhetstoneDurability;
    public static final Supplier<Boolean> onlyTools;
    public static final Supplier<Boolean> onlyLowerTiers;

    static {
        IConfigBuilder builder = ConfigBuilders.newTomlConfig("whetstones", null, false);
        builder.push("General");
        onlyTools = builder.comment("If true, only tools can be repaired, so no other items like armor.").define("onlyTools", true);
        onlyLowerTiers = builder.comment("If true, only tools with a tier lower than or equal to the whetstone can be repaired. Netherite can repair all tiers. Only takes effect if 'onlyTools' is enabled.").define("onlyLowerTiers", true);
        builder.pop();
        builder.push("Durability");
        woodenWhetstoneDurability = builder.comment("How much durability should the wooden whetstone restore?").gameRestart().define("woodenWhetstoneDurability", 59, 1, 10000);
        stoneWhetstoneDurability = builder.comment("How much durability should the stone whetstone restore?").gameRestart().define("stoneWhetstoneDurability", 131, 1, 10000);
        ironWhetstoneDurability = builder.comment("How much durability should the iron whetstone restore?").gameRestart().define("ironWhetstoneDurability", 250, 1, 10000);
        goldenWhetstoneDurability = builder.comment("How much durability should the golden whetstone restore?").gameRestart().define("goldenWhetstoneDurability", 32, 1, 10000);
        diamondWhetstoneDurability = builder.comment("How much durability should the diamond whetstone restore?").gameRestart().define("diamondWhetstoneDurability", 1561, 1, 10000);
        netheriteWhetstoneDurability = builder.comment("How much durability should the netherite whetstone restore?").gameRestart().define("netheriteWhetstoneDurability", 2031, 1, 10000);
        builder.pop();
        builder.build();
    }
}

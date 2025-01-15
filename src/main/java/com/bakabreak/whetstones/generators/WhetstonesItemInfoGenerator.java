package com.bakabreak.whetstones.generators;

import com.bakabreak.whetstones.WhetstoneTiers;
import com.supermartijn642.core.generator.ItemInfoGenerator;
import com.supermartijn642.core.generator.ResourceCache;

public class WhetstonesItemInfoGenerator extends ItemInfoGenerator {
    public WhetstonesItemInfoGenerator(ResourceCache cache) {
        super("whetstones", cache);
    }

    @Override
    public void generate() {
        for (WhetstoneTiers tier : WhetstoneTiers.values())
            this.simpleInfo(tier.getItem(), "item/" + tier.getIdentifier());
    }
}

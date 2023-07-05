package com.bakabreak.whetstones.generators;

import com.bakabreak.whetstones.WhetstoneTiers;
import com.supermartijn642.core.generator.ModelGenerator;
import com.supermartijn642.core.generator.ResourceCache;
import net.minecraft.resources.ResourceLocation;

public class WhetstonesModelGenerator extends ModelGenerator {
    public WhetstonesModelGenerator(ResourceCache cache) {
        super("whetstones", cache);
    }

    @Override
    public void generate() {
        for (WhetstoneTiers tier : WhetstoneTiers.values())
            this.itemGenerated(tier.getItem(), new ResourceLocation("whetstones", tier.getIdentifier()));
    }
}

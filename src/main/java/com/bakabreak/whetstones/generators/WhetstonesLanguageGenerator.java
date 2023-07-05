package com.bakabreak.whetstones.generators;

import com.bakabreak.whetstones.WhetstoneTiers;
import com.bakabreak.whetstones.Whetstones;
import com.supermartijn642.core.generator.LanguageGenerator;
import com.supermartijn642.core.generator.ResourceCache;

public class WhetstonesLanguageGenerator extends LanguageGenerator {
    public WhetstonesLanguageGenerator(ResourceCache cache) {
        super("whetstones", cache, "en_us");
    }

    @Override
    public void generate() {
        for (WhetstoneTiers tier : WhetstoneTiers.values()) {
            this.item(tier.getItem(), tier.displayName);
            this.translation("whetstones." + tier.getIdentifier() + ".description", tier.description);
        }
        this.translation("whetstones.item.description.durability", "Durability: %s");
        this.translation("whetstones.item.description.durability.used", "Remaining durability: %s");
        this.itemGroup(Whetstones.GROUP, "Whetstones");
    }
}

package com.bakabreak.whetstones.generators;

import com.bakabreak.whetstones.WhetstoneTiers;
import com.supermartijn642.core.generator.RecipeGenerator;
import com.supermartijn642.core.generator.ResourceCache;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

public class WhetstoneRecipeGenerator extends RecipeGenerator {
    public WhetstoneRecipeGenerator(ResourceCache cache) {
        super("whetstones", cache);
    }

    @Override
    public void generate() {
        for (WhetstoneTiers tier : WhetstoneTiers.values()) {
            this.shaped(tier.getItem())
                    .pattern("#")
                    .pattern("$")
                    .input('#', tier.craftingMaterial)
                    .input('$', ItemTags.PLANKS)
                    .unlockedBy(tier.craftingMaterial);
        }
    }
}

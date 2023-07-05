package com.bakabreak.whetstones;

import com.bakabreak.whetstones.generators.WhetstoneRecipeGenerator;
import com.bakabreak.whetstones.generators.WhetstonesLanguageGenerator;
import com.bakabreak.whetstones.generators.WhetstonesModelGenerator;
import com.supermartijn642.core.item.BaseItem;
import com.supermartijn642.core.item.CreativeItemGroup;
import com.supermartijn642.core.item.ItemProperties;
import com.supermartijn642.core.registry.GeneratorRegistrationHandler;
import com.supermartijn642.core.registry.RegistrationHandler;
import com.supermartijn642.core.registry.RegistryEntryAcceptor;
import net.minecraftforge.fml.common.Mod;

@Mod("whetstones")
public class Whetstones {

    @RegistryEntryAcceptor(namespace = "whetstones", identifier = "diamond_whetstone", registry = RegistryEntryAcceptor.Registry.ITEMS)
    public static BaseItem diamondWhetstone;

    public static final CreativeItemGroup GROUP = CreativeItemGroup.create("whetstones", () -> diamondWhetstone);

    public Whetstones() {
        RegistrationHandler handler = RegistrationHandler.get("whetstones");
        for (WhetstoneTiers tier : WhetstoneTiers.values())
            handler.registerItemCallback(tier::createItem);
        GeneratorRegistrationHandler generatorHandler = GeneratorRegistrationHandler.get("whetstones");
        generatorHandler.addGenerator(WhetstonesLanguageGenerator::new);
        generatorHandler.addGenerator(WhetstonesModelGenerator::new);
        generatorHandler.addGenerator(WhetstoneRecipeGenerator::new);
    }
}

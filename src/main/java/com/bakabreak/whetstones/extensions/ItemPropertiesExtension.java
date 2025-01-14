package com.bakabreak.whetstones.extensions;

import net.minecraft.world.item.ToolMaterial;

public interface ItemPropertiesExtension {

    ToolMaterial whetstones_getToolMaterial();

    void whetstones_setToolMaterial(ToolMaterial toolMaterial);
}

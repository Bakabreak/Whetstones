package com.bakabreak.whetstones.mixin;

import com.bakabreak.whetstones.extensions.ItemPropertiesExtension;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.Properties.class)
public class ItemPropertiesMixin implements ItemPropertiesExtension {

    @Unique
    private ToolMaterial toolMaterial;

    @Override
    public ToolMaterial whetstones_getToolMaterial() {
        return this.toolMaterial;
    }

    @Override
    public void whetstones_setToolMaterial(ToolMaterial toolMaterial) {
        this.toolMaterial = toolMaterial;
    }
}

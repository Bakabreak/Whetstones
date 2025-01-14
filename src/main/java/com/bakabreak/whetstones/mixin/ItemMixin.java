package com.bakabreak.whetstones.mixin;

import com.bakabreak.whetstones.extensions.ItemExtension;
import com.bakabreak.whetstones.extensions.ItemPropertiesExtension;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin implements ItemExtension {

    @Unique
    private ToolMaterial toolMaterial;

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void init(Item.Properties properties, CallbackInfo ci) {
        this.toolMaterial = ((ItemPropertiesExtension) properties).whetstones_getToolMaterial();
    }

    @Override
    public ToolMaterial whetstones_getToolMaterial() {
        return this.toolMaterial;
    }
}

package com.bakabreak.whetstones.mixin;

import com.bakabreak.whetstones.extensions.ItemPropertiesExtension;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolMaterial.class)
public class ToolMaterialMixin {

    @Inject(
            method = "applyCommonProperties(Lnet/minecraft/world/item/Item$Properties;)Lnet/minecraft/world/item/Item$Properties;",
            at = @At("HEAD")
    )
    private void applyCommonProperties(Item.Properties properties, CallbackInfoReturnable<Item.Properties> ci) {
        //noinspection DataFlowIssue
        ToolMaterial toolMaterial = (ToolMaterial) (Object) this;
        ((ItemPropertiesExtension) properties).whetstones_setToolMaterial(toolMaterial);
    }
}

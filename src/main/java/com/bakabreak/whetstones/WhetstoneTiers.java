package com.bakabreak.whetstones;

import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.item.ItemProperties;
import com.supermartijn642.core.registry.RegistrationHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.Tags;

import java.awt.*;
import java.util.Locale;
import java.util.function.Supplier;

public enum WhetstoneTiers {
    WOODEN("Wooden Whetstone", "Restores durability for wooden tools and below", WhetstonesConfig.woodenWhetstoneDurability, Tags.Items.RODS_WOODEN, Tiers.WOOD),
    STONE("Stone Whetstone", "Restores durability for stone tools and below", WhetstonesConfig.stoneWhetstoneDurability, ItemTags.STONE_CRAFTING_MATERIALS, Tiers.STONE),
    IRON("Iron Whetstone", "Restores durability for iron tools and below", WhetstonesConfig.ironWhetstoneDurability, Tags.Items.INGOTS_IRON, Tiers.IRON),
    GOLDEN("Golden Whetstone", "Restores durability for golden tools and below", WhetstonesConfig.goldenWhetstoneDurability, Tags.Items.INGOTS_GOLD, Tiers.GOLD),
    DIAMOND("Diamond Whetstone", "Restores durability for diamond tools and below", WhetstonesConfig.diamondWhetstoneDurability, Tags.Items.GEMS_DIAMOND, Tiers.DIAMOND),
    NETHERITE("Netherite Whetstone", "Restores durability for any tools", WhetstonesConfig.netheriteWhetstoneDurability, Tags.Items.INGOTS_NETHERITE, null);

    public final String displayName, description;
    private final Supplier<Integer> durability;
    public final TagKey<Item> craftingMaterial;
    private WhetstoneItem item;
    public final Tier tier;

    WhetstoneTiers(String displayName, String description, Supplier<Integer> durability, TagKey<Item> craftingMaterial, Tier tier) {
        this.displayName = displayName;
        this.description = description;
        this.durability = durability;
        this.craftingMaterial = craftingMaterial;
        this.tier = tier;
    }

    public String getIdentifier() {
        return this.name().toLowerCase(Locale.ROOT) + "_whetstone";
    }

    public int getDurability() {
        return this.durability.get();
    }

    public void createItem(RegistrationHandler.Helper<Item> helper) {
        this.item = new WhetstoneItem(ItemProperties.create().group(Whetstones.GROUP).durability(this.getDurability()), this);
        helper.register(this.getIdentifier(), this.item);
    }

    public Component getDescriptionTranslation(){
        return TextComponents.translation("whetstones." + this.getIdentifier() + ".description").color(ChatFormatting.DARK_GRAY).get();
    }

    public WhetstoneItem getItem() {
        if (this.item == null)
            throw new RuntimeException("Item has not yet been created!!");
        return item;
    }
}

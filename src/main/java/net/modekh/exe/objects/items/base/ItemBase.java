package net.modekh.exe.objects.items.base;

import net.minecraft.item.Item;
import net.modekh.exe.registry.ItemRegistry;
import net.modekh.exe.utils.customization.ExeCreativeTab;
import net.modekh.exe.utils.Reference;

public class ItemBase extends Item {
    public ItemBase(String id) {
        this.setUnlocalizedName(id);
        this.setRegistryName(Reference.MOD_ID, id);
        this.setCreativeTab(ExeCreativeTab.TAB);

        ItemRegistry.registerItem(this);
    }
}

package net.modekh.exe.items.base;

import net.minecraft.item.Item;
import net.modekh.exe.init.ItemRegistry;
import net.modekh.exe.utils.customization.ExeCreativeTab;
import net.modekh.exe.utils.Reference;

public class ItemBase extends Item {
    public ItemBase(String id) {
        setUnlocalizedName(id);
        setRegistryName(Reference.MOD_ID, id);
        setCreativeTab(ExeCreativeTab.TAB);

        ItemRegistry.registerItem(this);
    }
}

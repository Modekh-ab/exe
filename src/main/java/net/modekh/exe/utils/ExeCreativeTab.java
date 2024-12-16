package net.modekh.exe.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.modekh.exe.init.ItemRegistry;

public class ExeCreativeTab {
    public static final CreativeTabs TAB = new CreativeTabs(Reference.MOD_ID) {
        final ItemStack TAB_ICON_ITEM = new ItemStack(ItemRegistry.EXE_CRYSTAL);

        @Override
        public ItemStack getTabIconItem() {
            return TAB_ICON_ITEM;
        }

        @Override
        public ItemStack getIconItemStack() {
            return TAB_ICON_ITEM;
        }
    };
}

package net.modekh.exe.utils.customization;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.modekh.exe.registry.ItemRegistry;
import net.modekh.exe.utils.Reference;

public class ExeCreativeTab {
    public static final CreativeTabs TAB = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemRegistry.EXE_CRYSTAL);
        }

        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(ItemRegistry.EXE_CRYSTAL);
        }
    };
}

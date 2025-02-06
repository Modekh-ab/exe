package net.modekh.exe.objects.items.custom;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;
import net.modekh.exe.objects.items.base.ItemBase;
import net.modekh.exe.utils.customization.ExeRarity;

public class ExeCrystalItem extends ItemBase {
    public ExeCrystalItem(String id) {
        super(id);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return ExeRarity.EXE_GOLD;
    }

    @Override
    public int getItemStackLimit(ItemStack p_getItemStackLimit_1_) {
        return 4;
    }
}

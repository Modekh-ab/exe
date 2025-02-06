package net.modekh.exe.objects.items.base;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IRarity;
import net.modekh.exe.utils.customization.ExeCreativeTab;
import net.modekh.exe.utils.customization.ExeRarity;

public class ArmorBase extends ItemArmor {
    public ArmorBase(String id, ArmorMaterial material, EntityEquipmentSlot slot) {
        super(material, 0, slot);

        this.setUnlocalizedName(id);
        this.setRegistryName(id);
        this.setCreativeTab(ExeCreativeTab.TAB);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return ExeRarity.EXE_GREEN;
    }
}

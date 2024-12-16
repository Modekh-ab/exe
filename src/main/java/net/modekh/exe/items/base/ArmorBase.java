package net.modekh.exe.items.base;

import divinerpg.enums.ArmorInfo;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.modekh.exe.init.ItemRegistry;
import net.modekh.exe.utils.ExeCreativeTab;

public class ArmorBase extends ItemArmor {
    public ArmorBase(String id, ArmorMaterial material, EntityEquipmentSlot slot) {
        super(material, 0, slot);

        setUnlocalizedName(id);
        setRegistryName(id);
        setCreativeTab(ExeCreativeTab.TAB);

        ItemRegistry.ITEMS.add(this);
    }
}

package net.modekh.exe.registry;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.modekh.exe.client.render.PunkDirtChestItemRender;
import net.modekh.exe.client.render.PunkDirtChestRender;
import net.modekh.exe.objects.entities.tile.PunkDirtChestTileEntity;
import net.modekh.exe.objects.items.custom.DigitalTerminalItem;
import net.modekh.exe.objects.items.custom.ExeCrystalItem;
import net.modekh.exe.objects.items.custom.ExeStoneItem;
import net.modekh.exe.objects.items.base.ArmorBase;
import net.modekh.exe.utils.Reference;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    // == ITEMS ==

    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item DIGITAL_TERMINAL = new DigitalTerminalItem("digital_terminal");
    public static final Item EXE_CRYSTAL = new ExeCrystalItem("exe_crystal");
    public static final Item EXE_STONE = new ExeStoneItem("exe_stone");

    // == ARMOR ==

    public static final ItemArmor.ArmorMaterial ARMOR_EXE = EnumHelper.addArmorMaterial("armor_exe", Reference.MOD_ID + ":exe",
            993, new int[] {4, 7, 9, 5}, 17, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 4.0F);

    // == REGISTRY ==

    public static void registerItem(Item item) {
        ITEMS.add(item);
    }

    public static void registerArmorSet(String prefix, ItemArmor.ArmorMaterial material) {
        registerItem(new ArmorBase(prefix + "_helmet", material, EntityEquipmentSlot.HEAD));
        registerItem(new ArmorBase(prefix + "_chestplate", material, EntityEquipmentSlot.CHEST));
        registerItem(new ArmorBase(prefix + "_leggings", material, EntityEquipmentSlot.LEGS));
        registerItem(new ArmorBase(prefix + "_boots", material, EntityEquipmentSlot.FEET));
    }

    public static void registerTESRs() {
        Item.getItemFromBlock(BlockRegistry.PUNK_DIRT_CHEST).setTileEntityItemStackRenderer(new PunkDirtChestItemRender());
        ClientRegistry.bindTileEntitySpecialRenderer(PunkDirtChestTileEntity.class, new PunkDirtChestRender());
    }
}

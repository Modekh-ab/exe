package net.modekh.exe.init;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.modekh.exe.Exe;
import net.modekh.exe.items.custom.DigitalTerminalItem;
import net.modekh.exe.items.custom.ExeCrystalItem;
import net.modekh.exe.items.custom.ExeStoneItem;
import net.modekh.exe.items.base.ArmorBase;
import net.modekh.exe.utils.Reference;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ItemRegistry {
    // == ITEMS ==

    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item DIGITAL_TERMINAL = new DigitalTerminalItem("digital_terminal");
    public static final Item EXE_CRYSTAL = new ExeCrystalItem("exe_crystal");
    public static final Item EXE_STONE = new ExeStoneItem("exe_stone");

    // == ARMOR ==

    public static final ItemArmor.ArmorMaterial ARMOR_EXE = EnumHelper.addArmorMaterial("armor_exe", Reference.MOD_ID + ":exe",
            1500, new int[] {4, 7, 9, 5}, 17, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 4.0F);

    // == REGISTRY ==

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        registerArmorSet("exe", ARMOR_EXE);

        ITEMS.forEach(item -> event.getRegistry().register(item));
    }

    @SubscribeEvent
    public static void registerModel(ModelRegistryEvent event) {
        ITEMS.forEach(item -> Exe.proxy.registerRenderer(item, 0, "inventory"));
    }

    public static void registerItem(Item item) {
        ITEMS.add(item);
    }

    private static void registerArmorSet(String prefix, ItemArmor.ArmorMaterial material) {
        registerItem(new ArmorBase(prefix + "_helmet", material, EntityEquipmentSlot.HEAD));
        registerItem(new ArmorBase(prefix + "_chestplate", material, EntityEquipmentSlot.CHEST));
        registerItem(new ArmorBase(prefix + "_leggings", material, EntityEquipmentSlot.LEGS));
        registerItem(new ArmorBase(prefix + "_boots", material, EntityEquipmentSlot.FEET));
    }
}

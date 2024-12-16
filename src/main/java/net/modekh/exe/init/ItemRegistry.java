package net.modekh.exe.init;

import divinerpg.enums.ArmorInfo;
import divinerpg.objects.items.base.ItemDivineArmor;
import divinerpg.registry.MaterialRegistry;
import divinerpg.utils.LocalizeUtils;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.text.TextComponentBase;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.modekh.exe.Exe;
import net.modekh.exe.items.base.ArmorBase;
import net.modekh.exe.items.base.ItemBase;
import net.modekh.exe.utils.Reference;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ItemRegistry {
    public static final List<Item> ITEMS = new ArrayList<>();

    // items

    public static final Item EXE_CRYSTAL = new ItemBase("exe_crystal");

    // armor

    public static final ItemArmor.ArmorMaterial ARMOR_EXE = EnumHelper.addArmorMaterial("armor_exe", Reference.MOD_ID + ":exe",
            1500, new int[] {4, 7, 9, 5}, 17, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);

//    public static final Item HELMET_EXE = new ArmorBase("exe_helmet", ARMOR_EXE, 1, EntityEquipmentSlot.HEAD);
//    public static final Item CHESTPLATE_EXE = new ArmorBase("exe_chestplate", ARMOR_EXE, 1, EntityEquipmentSlot.CHEST);
//    public static final Item LEGGINGS_EXE = new ArmorBase("exe_leggings", ARMOR_EXE, 2, EntityEquipmentSlot.LEGS);
//    public static final Item BOOTS_EXE = new ArmorBase("exe_boots", ARMOR_EXE, 1, EntityEquipmentSlot.FEET);

    public static void registerItem(Item item) {
        ITEMS.add(item);
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        registerArmorSet("exe", ARMOR_EXE);

        ITEMS.forEach(item -> event.getRegistry().register(item));
    }

    @SubscribeEvent
    public static void registerModel(ModelRegistryEvent event) {
        ITEMS.forEach(item -> Exe.proxy.registerRenderer(item, 0, "inventory"));
    }

    private static void registerArmorSet(String prefix, ItemArmor.ArmorMaterial material) {
        registerItem(new ArmorBase(prefix + "_helmet", material, EntityEquipmentSlot.HEAD));
        registerItem(new ArmorBase(prefix + "_chestplate", material, EntityEquipmentSlot.CHEST));
        registerItem(new ArmorBase(prefix + "_leggings", material, EntityEquipmentSlot.LEGS));
        registerItem(new ArmorBase(prefix + "_boots", material, EntityEquipmentSlot.FEET));
    }
}

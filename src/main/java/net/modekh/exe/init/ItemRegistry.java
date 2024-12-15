package net.modekh.exe.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.modekh.exe.Exe;
import net.modekh.exe.items.base.ItemBase;
import net.modekh.exe.utils.Reference;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ItemRegistry {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item EXE_CRYSTALL = new ItemBase("exe_crystall");

    public static void registerItem(Item item) {
        ITEMS.add(item);
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        ITEMS.forEach(item -> event.getRegistry().register(item));
    }

    @SubscribeEvent
    public static void registerModel(ModelRegistryEvent event) {
        ITEMS.forEach(item -> Exe.proxy.registerItemRenderer(item, 0, "exe_crystall"));
    }
}

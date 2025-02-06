package net.modekh.exe.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.modekh.exe.Exe;
import net.modekh.exe.objects.entities.tile.PunkDirtChestTileEntity;
import net.modekh.exe.utils.Reference;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class RegistryHandler {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ItemRegistry.registerArmorSet("exe", ItemRegistry.ARMOR_EXE);
        ItemRegistry.ITEMS.forEach(item -> event.getRegistry().register(item));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        BlockRegistry.BLOCKS.forEach(block -> event.getRegistry().register(block));
        ItemRegistry.registerTESRs();

        // tile entity registry
        GameRegistry.registerTileEntity(PunkDirtChestTileEntity.class,
                new ResourceLocation(Reference.MOD_ID, BlockRegistry.PUNK_DIRT_CHEST.getUnlocalizedName()));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ItemRegistry.ITEMS.forEach(item -> Exe.proxy.registerRenderer(item, 0, "inventory"));
    }
}

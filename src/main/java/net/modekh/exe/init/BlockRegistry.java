package net.modekh.exe.init;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.modekh.exe.utils.Reference;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class BlockRegistry {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static void registerBlock(Block block, ItemBlock item) {
        BLOCKS.add(block);

        item.setRegistryName(block.getUnlocalizedName());
        ItemRegistry.registerItem(item);
    }

    public static void registerBlock(Block block) {
        registerBlock(block, new ItemBlock(block));
    }

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Block> event) {
        BLOCKS.forEach(block -> event.getRegistry().register(block));
    }
}

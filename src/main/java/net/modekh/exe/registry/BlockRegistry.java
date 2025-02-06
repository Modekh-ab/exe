package net.modekh.exe.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.modekh.exe.objects.blocks.PunkDirtBlock;
import net.modekh.exe.objects.blocks.PunkDirtChestBlock;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block PUNK_DIRT = new PunkDirtBlock("punk_dirt");
    public static final Block PUNK_DIRT_CHEST = new PunkDirtChestBlock("punk_dirt_chest");

    // == REGISTRY ==

    public static void registerBlock(Block block, ItemBlock item) {
        BLOCKS.add(block);

        String unlocalizedName = block.getUnlocalizedName();
        unlocalizedName = unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
        ItemRegistry.registerItem(item.setRegistryName(unlocalizedName));
    }

    public static void registerBlock(Block block) {
        registerBlock(block, new ItemBlock(block));
    }
}

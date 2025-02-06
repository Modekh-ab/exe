package net.modekh.exe.objects.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modekh.exe.registry.BlockRegistry;
import net.modekh.exe.utils.customization.ExeCreativeTab;

public class BlockBase extends Block {
    public BlockBase(Material material, String id) {
        super(material);

        this.setUnlocalizedName(id);
        this.setRegistryName(id);
        this.setCreativeTab(ExeCreativeTab.TAB);

        BlockRegistry.registerBlock(this);
    }
}

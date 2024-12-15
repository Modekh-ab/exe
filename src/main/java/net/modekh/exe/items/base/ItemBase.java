package net.modekh.exe.items.base;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.modekh.exe.init.ItemRegistry;
import net.modekh.exe.utils.ExeCreativeTab;
import net.modekh.exe.utils.ExeRarity;
import net.modekh.exe.utils.Reference;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBase extends Item {
    public ItemBase(String id) {
        setUnlocalizedName(id);
        setRegistryName(Reference.MOD_ID, id);
        setCreativeTab(ExeCreativeTab.tab);

        ItemRegistry.registerItem(this);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return ExeRarity.EXE_GOLD;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add("Shift");
    }

    @Override
    public boolean shouldRotateAroundWhenRendering() {
        return false;
    }
}

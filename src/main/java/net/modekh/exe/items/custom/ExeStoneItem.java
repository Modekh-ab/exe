package net.modekh.exe.items.custom;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.modekh.exe.items.base.ItemBase;
import net.modekh.exe.utils.customization.ExeRarity;
import net.modekh.exe.utils.functions.TooltipUtils;

import javax.annotation.Nullable;
import java.util.List;

public class ExeStoneItem extends ItemBase {
    public ExeStoneItem(String id) {
        super(id);
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return ExeRarity.EXE_GOLD;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> components, ITooltipFlag flag) {
        TooltipUtils.addTooltipOnShift(components, this.getUnlocalizedName() + ".info");
    }
}

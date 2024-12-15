package net.modekh.exe.utils;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.IRarity;

public enum ExeRarity implements IRarity {
    EXE_GOLD(TextFormatting.GOLD, "exe_gold");

    public final TextFormatting rarityColor;
    public final String rarityName;

    ExeRarity(TextFormatting rarityColor, String rarityName) {
        this.rarityColor = rarityColor;
        this.rarityName = rarityName;
    }

    @Override
    public TextFormatting getColor() {
        return rarityColor;
    }

    @Override
    public String getName() {
        return rarityName;
    }
}

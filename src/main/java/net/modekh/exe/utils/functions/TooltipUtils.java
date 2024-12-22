package net.modekh.exe.utils.functions;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.*;

import java.util.List;

public class TooltipUtils {
    public static void addTooltipOnShift(List<String> components, TextComponentString message) {
        if (GuiScreen.isShiftKeyDown()) {
            components.add(message.getUnformattedText());
        } else {
            components.add(new TextComponentTranslation("tooltip.press").getUnformattedText()
                    + new TextComponentString("Shift")
                    .setStyle(new Style().setColor(TextFormatting.GREEN)).getFormattedText()
                    + ">");
        }
    }

    public static void addTooltipOnShift(List<String> components, String message) {
        addTooltipOnShift(components, new TextComponentString(new TextComponentTranslation(message).getFormattedText()));
    }
}

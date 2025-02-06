package net.modekh.exe.utils.functions;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.text.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TooltipUtils {
    public static void addTooltipOnShift(List<String> components, List<TextComponentString> messages) {
        if (GuiScreen.isShiftKeyDown()) {
            messages.forEach(message -> components.add(message.getFormattedText()));
        } else {
            components.add(new TextComponentTranslation("tooltip.press").getUnformattedText()
                    + new TextComponentString("Shift")
                    .setStyle(new Style().setColor(TextFormatting.GREEN)).getFormattedText()
                    + ">");
        }
    }

    public static void addTooltipTranslationOnShift(List<String> components, List<String> messages) {
        List<TextComponentString> messagesTranslate = new ArrayList<>();
        messages.forEach(message ->
                messagesTranslate.add(
                        new TextComponentString(new TextComponentTranslation(message).getFormattedText())));

        addTooltipOnShift(components, messagesTranslate);
    }

    public static void addTooltipOnShift(List<String> components, TextComponentString message) {
        addTooltipOnShift(components, Collections.singletonList(message));
    }

    public static void addTooltipOnShift(List<String> components, String message) {
        addTooltipOnShift(components, Collections.singletonList(
                new TextComponentString(new TextComponentTranslation(message).getFormattedText())));
    }
}

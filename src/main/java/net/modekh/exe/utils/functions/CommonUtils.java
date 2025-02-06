package net.modekh.exe.utils.functions;

import appeng.api.AEApi;
import appeng.api.features.IGrinderRecipe;
import appeng.api.features.IGrinderRecipeBuilder;
import appeng.api.features.IGrinderRegistry;
import net.minecraft.item.ItemStack;
import net.modekh.exe.registry.ItemRegistry;

public class CommonUtils {
    public static void loadGrinderRecipes() {
        IGrinderRegistry grinderRegistry = AEApi.instance().registries().grinder();
        IGrinderRecipeBuilder builder = grinderRegistry.builder();

        IGrinderRecipe grinderRecipe = builder
                .withInput(new ItemStack(ItemRegistry.EXE_STONE))
                .withOutput(new ItemStack(ItemRegistry.EXE_CRYSTAL, 4))
                .withTurns(11)
                .build();

        grinderRegistry.addRecipe(grinderRecipe);
    }
}

package net.modekh.exe;

import divinerpg.DivineRPG;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.modekh.exe.client.gui.GuiHandler;
import net.modekh.exe.proxy.ClientProxy;
import net.modekh.exe.utils.functions.CommonUtils;
import net.modekh.exe.utils.Reference;
import sonar.fluxnetworks.FluxNetworks;

@Mod(
        modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION,
        dependencies =
                "required-after:appliedenergistics2;" +
                "required-after:cosmeticarmorreworked;" +
                "required-after:" + DivineRPG.MODID + ";" +
                "required-after:" + FluxNetworks.MODID
)
public class Exe {
    @Mod.Instance(Reference.MOD_ID)
    public static Exe instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
    public static ClientProxy proxy = new ClientProxy();

//    public static SimpleNetworkWrapper network;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
//        network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

        CommonUtils.loadGrinderRecipes(); // recipes for AE2 grinder
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    }
}

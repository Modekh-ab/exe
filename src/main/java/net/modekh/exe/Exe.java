package net.modekh.exe;

import com.yogpc.qp.QuarryPlus;
import divinerpg.DivineRPG;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.modekh.exe.proxy.ClientProxy;
import net.modekh.exe.utils.Reference;

@Mod(
        modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION,
        dependencies =
                "required-after:cosmeticarmorreworked;" +
                "required-after:" + DivineRPG.MODID + ";" +
                "required-after:" + QuarryPlus.modID
)
public class Exe {
    @SidedProxy(
            clientSide = "net.modekh.exe.proxy.ClientProxy",
            serverSide = "net.modekh.exe.proxy.BaseProxy"
    )
    public static ClientProxy proxy = new ClientProxy();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }
}

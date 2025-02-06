package net.modekh.exe.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.modekh.exe.objects.blocks.containers.PunkDirtChestContainer;
import net.modekh.exe.client.gui.blocks.PunkDirtChestGui;
import net.modekh.exe.objects.entities.tile.PunkDirtChestTileEntity;
import net.modekh.exe.utils.Reference;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == Reference.GUI_PUNK_DIRT_CHEST) {
            TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

            if (tileEntity != null) {
                return new PunkDirtChestContainer(player.inventory, (PunkDirtChestTileEntity) tileEntity, player);
            }
        }

        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == Reference.GUI_PUNK_DIRT_CHEST) {
            TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

            if (tileEntity != null) {
                return new PunkDirtChestGui(player.inventory, (PunkDirtChestTileEntity) tileEntity, player);
            }
        }

        return null;
    }
}

package net.modekh.exe.client.render;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.modekh.exe.client.models.PunkDirtChestModel;
import net.modekh.exe.objects.entities.tile.PunkDirtChestTileEntity;
import net.modekh.exe.utils.Reference;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class PunkDirtChestItemRender extends TileEntityItemStackRenderer {
    private final ResourceLocation TEXTURE =
            new ResourceLocation(Reference.MOD_ID, "textures/blocks/punk_dirt_chest/all.png");
    private final PunkDirtChestModel MODEL = new PunkDirtChestModel();

    @Override
    public void renderByItem(ItemStack stack) {
        RenderHelper.enableStandardItemLighting();
        TileEntityRendererDispatcher.instance.render(new PunkDirtChestTileEntity(),
                0.0D, 0.0D, 0.0D, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    }
}

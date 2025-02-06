package net.modekh.exe.client.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.ResourceLocation;
import net.modekh.exe.client.models.PunkDirtChestModel;
import net.modekh.exe.objects.entities.tile.PunkDirtChestTileEntity;
import net.modekh.exe.utils.Reference;

import java.util.Random;

public class PunkDirtChestRender extends TileEntitySpecialRenderer<PunkDirtChestTileEntity> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Reference.MOD_ID, "textures/blocks/punk_dirt_chest/all.png");

    private Random random;
    private RenderEntityItem itemRenderer;
    private EntityItem customItem;

    private final PunkDirtChestModel model;

    public PunkDirtChestRender() {
        this.model = new PunkDirtChestModel();
    }

    @Override
    public void render(PunkDirtChestTileEntity tileEntity,
                       double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.enableDepth();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);

        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 1.0F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
            this.bindTexture(TEXTURE);
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);

        int meta = 0, angle = 0;

        if (tileEntity.hasWorld()) {
            meta = tileEntity.getBlockMetadata();
        }

        if (meta == 2) {
            angle = 180;
        }

        if (meta == 4) {
            angle = 90;
        }

        if (meta == 5) {
            angle = -90;
        }

        GlStateManager.rotate((float) angle, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        float f = tileEntity.prevLidAngle + (tileEntity.lidAngle - tileEntity.prevLidAngle) * partialTicks;
        f = 1.0F - f;
        f = 1.0F - f * f * f;
        model.lid.rotateAngleX = -(f * ((float) Math.PI / 2F));
        model.renderAll();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
}

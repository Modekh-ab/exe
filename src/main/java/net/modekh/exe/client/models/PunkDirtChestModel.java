package net.modekh.exe.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PunkDirtChestModel extends ModelBase {
    public ModelRenderer handle;
    public ModelRenderer lid;
    public ModelRenderer storage;

    public PunkDirtChestModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(8.0F, 7.0F, 15.0F);
        this.handle.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);

        this.lid = new ModelRenderer(this, 0, 0);
        this.lid.setRotationPoint(1.0F, 7.0F, 15.0F);
        this.lid.addBox(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);

        this.storage = new ModelRenderer(this, 0, 19);
        this.storage.setRotationPoint(1.0F, 6.0F, 1.0F);
        this.storage.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
    }

    public void renderAll() {
        this.handle.rotateAngleX = this.lid.rotateAngleX;

        this.handle.render(0.0625F);
        this.lid.render(0.0625F);
        this.storage.render(0.0625F);
    }
}

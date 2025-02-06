package net.modekh.exe.client.gui.blocks;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.modekh.exe.objects.blocks.containers.PunkDirtChestContainer;
import net.modekh.exe.objects.entities.tile.PunkDirtChestTileEntity;
import net.modekh.exe.utils.Reference;

public class PunkDirtChestGui extends GuiContainer {
    private static final ResourceLocation GUI_PUNK_DIRT_CHEST_PATH =
            new ResourceLocation(Reference.MOD_ID, "textures/gui/punk_dirt_chest.png");
    private final InventoryPlayer playerInventory;
    private final PunkDirtChestTileEntity tileEntity;

    public PunkDirtChestGui(InventoryPlayer playerInventory, PunkDirtChestTileEntity tileEntity, EntityPlayer player) {
        super(new PunkDirtChestContainer(playerInventory, tileEntity, player));

        this.playerInventory = playerInventory;
        this.tileEntity = tileEntity;

        this.xSize = 176;
        this.ySize = 168;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        // player inventory title
//        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(),
//                8, this.ySize - 92, 6556941); // #640D0D
        // custom in-chest string
        this.fontRenderer.drawString(new TextComponentTranslation("gui.punk_dirt_chest.text").getUnformattedText(),
                71, this.ySize - 129, 16752777);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(GUI_PUNK_DIRT_CHEST_PATH);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}

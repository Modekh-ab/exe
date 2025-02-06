package net.modekh.exe.objects.blocks.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.modekh.exe.objects.entities.tile.PunkDirtChestTileEntity;

public class PunkDirtChestContainer extends Container {
    private final PunkDirtChestTileEntity chestInventory;

    public PunkDirtChestContainer(InventoryPlayer playerInventory, PunkDirtChestTileEntity chestInventory, EntityPlayer player) {
        this.chestInventory = chestInventory;

        chestInventory.openInventory(player);

        int nextY = 17;

        // chest inventory
        this.addSlotToContainer(new Slot(chestInventory, 0, 56, nextY));
        nextY += 18 * 3 + 15; // 3 rows [which are actually unused] + margin + 1 px [as started from 17]

        // player inventory
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, nextY));
            }

            nextY += 18;
        }

        nextY += 4;

        // hotbar
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, nextY)); // 233
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.chestInventory.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackToMerge = slot.getStack();
            stack = stackToMerge.copy();

            int chestInventorySize = this.chestInventory.getSizeInventory();

            if (index < chestInventorySize) {
                if (!this.mergeItemStack(stackToMerge, chestInventorySize, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(stackToMerge, 0, chestInventorySize, false)) {
                return ItemStack.EMPTY;
            }

            if (stackToMerge.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return stack;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        chestInventory.closeInventory(player);
    }

    public PunkDirtChestTileEntity getChestInventory() {
        return this.chestInventory;
    }
}

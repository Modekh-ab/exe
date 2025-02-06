package net.modekh.exe.objects.entities.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.modekh.exe.objects.blocks.containers.PunkDirtChestContainer;
import net.modekh.exe.registry.BlockRegistry;
import net.modekh.exe.utils.Reference;

public class PunkDirtChestTileEntity extends TileEntityLockableLoot implements ITickable {
    private static final ItemStack PUNK_DIRT_STACK = new ItemStack(BlockRegistry.PUNK_DIRT, 4);
    private NonNullList<ItemStack> chestItems;
    private EnumFacing facing;

    public int numPlayersUsing, ticksSinceSync;
    public float lidAngle, prevLidAngle;

    public PunkDirtChestTileEntity() {
        this.chestItems = NonNullList.withSize(1, PUNK_DIRT_STACK);
        this.facing = EnumFacing.NORTH;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return chestItems;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return chestItems.isEmpty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 56;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.initChestItems();

        if (!this.checkLootAndRead(tag)) {
            ItemStackHelper.loadAllItems(tag, chestItems);
        }

        if (tag.hasKey("CustomName", 8)) {
            this.customName = tag.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        if (!this.checkLootAndWrite(tag)) {
            ItemStackHelper.saveAllItems(tag, chestItems);
        }

        if (tag.hasKey("CustomName", 8)) {
            tag.setString("CustomName", this.customName);
        }

        return tag;
    }

    @Override
    public Container createContainer(InventoryPlayer inventory, EntityPlayer player) {
        return new PunkDirtChestContainer(inventory, this, player);
    }

    @Override
    public void openInventory(EntityPlayer player) {
        ++this.numPlayersUsing;
        this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
    }

    @Override
    public void closeInventory(EntityPlayer player) {
        --this.numPlayersUsing;
        this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
    }

    @Override
    public void update() {
        ++this.ticksSinceSync;

        if (!this.world.isRemote && this.numPlayersUsing != 0
                && (this.ticksSinceSync + pos.getX() + pos.getY() + pos.getZ()) % 200 == 0) {
            this.numPlayersUsing = 0;

            final AxisAlignedBB AABB = new AxisAlignedBB((float) pos.getX() - 5.0F, (float) pos.getY() - 5.0F, (float) pos.getZ() - 5.0F,
                    (float) (pos.getX() + 1) + 5.0F, (float) (pos.getY() + 1) + 5.0F, (float) (pos.getZ() + 1) + 5.0F);

            for (EntityPlayer player : this.world.getEntitiesWithinAABB(EntityPlayer.class, AABB)) {
                if (player.openContainer instanceof PunkDirtChestContainer) {
                    if (((PunkDirtChestContainer) player.openContainer).getChestInventory() == this) {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            this.world.playSound(null,
                    pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                    SoundEvents.BLOCK_ENDERCHEST_OPEN, SoundCategory.BLOCKS, 0.5F,
                    this.world.rand.nextFloat() * 0.1F + 0.9F);
        }

        if ((this.numPlayersUsing == 0 && this.lidAngle > 0.0F) || (this.numPlayersUsing > 0 && this.lidAngle < 1.0F)) {
            float la = this.lidAngle;

            if (this.numPlayersUsing > 0) {
                this.lidAngle += 0.1F;
            } else {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            if (this.lidAngle < 0.5F && la >= 0.5F) {
                this.world.playSound(null,
                        pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                        SoundEvents.BLOCK_ENDERCHEST_CLOSE, SoundCategory.BLOCKS, 0.5F,
                        this.world.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }
    }

    @Override
    public String getGuiID() {
        return Reference.MOD_ID + ":" + BlockRegistry.PUNK_DIRT_CHEST.getUnlocalizedName();
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "gui.punk_dirt_chest.title";
    }

    public void initChestItems() {
        this.chestItems = NonNullList.withSize(this.getSizeInventory(), PUNK_DIRT_STACK);
    }

    public NonNullList<ItemStack> getChestItems() {
        return chestItems;
    }

    public EnumFacing getFacing() {
        return facing;
    }
}

package net.modekh.exe.objects.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.modekh.exe.Exe;
import net.modekh.exe.objects.entities.tile.PunkDirtChestTileEntity;
import net.modekh.exe.registry.BlockRegistry;
import net.modekh.exe.utils.Reference;
import net.modekh.exe.utils.customization.ExeCreativeTab;

import javax.annotation.Nullable;

public class PunkDirtChestBlock extends BlockContainer {
    protected static final AxisAlignedBB AABB_BLOCK = new AxisAlignedBB(
            0.0625D, 0.0D, 0.0625D, // min X Y Z
            0.9375D, 0.875D, 0.9375D // max X Y Z
    );
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    private final String id;

    public PunkDirtChestBlock(String id) {
        super(Material.GROUND);

        this.id = id;
        this.setUnlocalizedName(id);
        this.setRegistryName(id);
        this.setCreativeTab(ExeCreativeTab.TAB);

        BlockRegistry.registerBlock(this);

        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setHardness(1.7F);
    }

    @Override
    public String getUnlocalizedName() {
        return this.id;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState,
                                    EntityPlayer player, EnumHand hand, EnumFacing facing,
                                    float side, float hitX, float hitY) {
        TileEntity tileEntity = world.getTileEntity(blockPos);

        if (world.isRemote || !(tileEntity instanceof PunkDirtChestTileEntity)
                || world.getBlockState(blockPos.up()).doesSideBlockChestOpening(world, blockPos.up(), EnumFacing.DOWN)) {
            return false;
        }

        player.openGui(Exe.instance, Reference.GUI_PUNK_DIRT_CHEST,
                world, blockPos.getX(), blockPos.getY(), blockPos.getZ());

        return true;
    }

    @Override
    public void getSubBlocks(CreativeTabs tabs, NonNullList<ItemStack> list) {
        list.add(new ItemStack(this, 1));
    }

    @Override
    public void breakBlock(World world, BlockPos blockPos, IBlockState state) {
        PunkDirtChestTileEntity tileEntity = (PunkDirtChestTileEntity) world.getTileEntity(blockPos);
        InventoryHelper.dropInventoryItems(world, blockPos, tileEntity);

        super.breakBlock(world, blockPos, state);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(world, pos, state);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileEntity = world.getTileEntity(blockPos);

            if (tileEntity != null) {
                ((PunkDirtChestTileEntity) tileEntity).setCustomName(stack.getDisplayName());
            }
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess blockAccess, BlockPos blockPos) {
        return AABB_BLOCK;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing,
                                            float hitX, float hitY, float hitZ,
                                            int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new PunkDirtChestTileEntity();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new PunkDirtChestTileEntity();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirror) {
        return state.withRotation(mirror.toRotation(state.getValue(FACING)));
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rotation) {
        return state.withProperty(FACING, rotation.rotate(state.getValue(FACING)));
    }

    private void setDefaultFacing(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote) {
            IBlockState stateNorth = world.getBlockState(pos.north());
            IBlockState stateSouth = world.getBlockState(pos.south());
            IBlockState stateWest = world.getBlockState(pos.west());
            IBlockState stateEast = world.getBlockState(pos.east());
            EnumFacing enumFacing = state.getValue(FACING);

            if (enumFacing == EnumFacing.NORTH && stateNorth.isFullBlock() && !stateSouth.isFullBlock()) {
                enumFacing = EnumFacing.SOUTH;
            } else if (enumFacing == EnumFacing.SOUTH && stateSouth.isFullBlock() && !stateNorth.isFullBlock()) {
                enumFacing = EnumFacing.NORTH;
            } else if (enumFacing == EnumFacing.WEST && stateWest.isFullBlock() && !stateEast.isFullBlock()) {
                enumFacing = EnumFacing.EAST;
            } else if (enumFacing == EnumFacing.EAST && stateEast.isFullBlock() && !stateWest.isFullBlock()) {
                enumFacing = EnumFacing.WEST;
            }

            world.setBlockState(pos, state.withProperty(FACING, enumFacing), 2);
        }
    }
}

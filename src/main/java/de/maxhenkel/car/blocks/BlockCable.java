package de.maxhenkel.car.blocks;

import cofh.api.energy.IEnergyConnection;
import de.maxhenkel.car.ModCreativeTabs;
import de.maxhenkel.car.blocks.tileentity.TileEntityCable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCable extends BlockContainer {

	public static final IProperty<Boolean> DOWN = PropertyBool.create("down");
	public static final IProperty<Boolean> UP = PropertyBool.create("up");
	public static final IProperty<Boolean> NORTH = PropertyBool.create("north");
	public static final IProperty<Boolean> SOUTH = PropertyBool.create("south");
	public static final IProperty<Boolean> WEST = PropertyBool.create("west");
	public static final IProperty<Boolean> EAST = PropertyBool.create("east");

	protected BlockCable() {
		super(new Material(MapColor.AIR));
		setRegistryName("cable");
		setUnlocalizedName("cable");
		setCreativeTab(ModCreativeTabs.TAB_CAR);
		setHardness(0.25F);
		useNeighborBrightness=true;
		setSoundType(SoundType.CLOTH);
		
		setDefaultState(blockState.getBaseState()
				.withProperty(UP, false)
				.withProperty(DOWN, false)
				.withProperty(NORTH, false)
				.withProperty(SOUTH, false)
				.withProperty(EAST, false)
				.withProperty(WEST, false)
				);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		
		float x1=0.40625F;
		float y1=0.40625F;
		float z1=0.40625F;
		float x2=0.59375F;
		float y2=0.59375F;
		float z2=0.59375F;
		
		
		if (isConnectedTo(source, pos, EnumFacing.UP)) {
			y2=1.0F;
		}

		if (isConnectedTo(source, pos, EnumFacing.DOWN)) {
			y1=0.0F;
		}

		if (isConnectedTo(source, pos, EnumFacing.SOUTH)) {
			z2=1.0F;
		}

		if (isConnectedTo(source, pos, EnumFacing.NORTH)) {
			z1=0.0F;
		}

		if (isConnectedTo(source, pos, EnumFacing.EAST)) {
			x2=1.0F;
		}

		if (isConnectedTo(source, pos, EnumFacing.WEST)) {
			x1=0.0F;
		}
		
		return new AxisAlignedBB(x1, y1, z1, x2, y2, z2);
	}
	
	/*private boolean isConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		TileEntity te=world.getTileEntity(pos);
		
		if(!(te instanceof TileEntityCable)){
			return false;
		}
		
		TileEntityCable cable=(TileEntityCable) te;
		
		return cable.isSideConnected(facing);
	}*/
	
	public static boolean isConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing) {
		IBlockState state=world.getBlockState(pos.offset(facing));
		
		if(state.getBlock().equals(ModBlocks.CABLE)){
			return true;
		}
		
		TileEntity te=world.getTileEntity(pos.offset(facing));
		
		if(!(te instanceof IEnergyConnection)){
			return false;
		}
		
		IEnergyConnection conn=(IEnergyConnection) te;

		return conn.canConnectEnergy(facing.getOpposite());
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCable();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, UP, DOWN, EAST, WEST, NORTH, SOUTH);
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		/*TileEntity te = world.getTileEntity(pos);
		
		if(!(te instanceof TileEntityCable)){
			return getDefaultState();
		}
		
		TileEntityCable cable=(TileEntityCable) te;
		
		return state
				.withProperty(UP, cable.isSideConnected(EnumFacing.UP))
				.withProperty(DOWN, cable.isSideConnected(EnumFacing.DOWN))
				.withProperty(NORTH, cable.isSideConnected(EnumFacing.NORTH))
				.withProperty(SOUTH, cable.isSideConnected(EnumFacing.SOUTH))
				.withProperty(EAST, cable.isSideConnected(EnumFacing.EAST))
				.withProperty(WEST, cable.isSideConnected(EnumFacing.WEST));
		*/
		IBlockState actualState= getDefaultState();
		
		if (isConnectedTo(world, pos, EnumFacing.UP)) {
			actualState=actualState.withProperty(UP, true);
		}

		if (isConnectedTo(world, pos, EnumFacing.DOWN)) {
			actualState=actualState.withProperty(DOWN, true);
		}

		if (isConnectedTo(world, pos, EnumFacing.SOUTH)) {
			actualState=actualState.withProperty(SOUTH, true);
		}

		if (isConnectedTo(world, pos, EnumFacing.NORTH)) {
			actualState=actualState.withProperty(NORTH, true);
		}

		if (isConnectedTo(world, pos, EnumFacing.EAST)) {
			actualState=actualState.withProperty(EAST, true);
		}

		if (isConnectedTo(world, pos, EnumFacing.WEST)) {
			actualState=actualState.withProperty(WEST, true);
		}
		
		return actualState;
	}

}
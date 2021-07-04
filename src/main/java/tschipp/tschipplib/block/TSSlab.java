package tschipp.tschipplib.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class TSSlab extends BlockSlab {

	public TSSlab(Material materialIn)
	{
		super(materialIn);
	}

	public static final PropertyBool VARIANT = PropertyBool.create("variant");
	private Block blockMaterial;
	private int blockMeta;
	private Block singleSlab;

	public TSSlab(Material materialIn, Block parent, int parentMeta, Block halfSlab)
	{
		super(materialIn);
		this.blockMaterial = parent;
		this.blockMeta = parentMeta;
		this.singleSlab = halfSlab;
		this.setHardness(this.blockMaterial.getBlockHardness(null, null, null));
		this.setResistance(this.blockMaterial.getExplosionResistance(null));
		this.setSoundType(this.blockMaterial.getSoundType());
		IBlockState state = this.blockState.getBaseState();
		state.withProperty(VARIANT, false);
		if (!this.isDouble())
		{
			state.withProperty(HALF, EnumBlockHalf.BOTTOM);
		}
		setDefaultState(state);
	}
	
	public Block getParent() 
	{
		return this.blockMaterial;
	}
	
	public int getParentMeta()
	{
		return this.blockMeta;
	}

	@Override
	public String getUnlocalizedName(int meta)
	{
		return this.getUnlocalizedName();
	}

	
	@Override
	public IProperty<?> getVariantProperty()
	{
		return VARIANT;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

	@Override
	public final IBlockState getStateFromMeta(final int meta)
	{
		IBlockState blockState = this.getDefaultState();
		blockState = blockState.withProperty(VARIANT, false);
		if (!this.isDouble())
		{
			EnumBlockHalf value = EnumBlockHalf.BOTTOM;
			if ((meta & 8) != 0)
			{
				value = EnumBlockHalf.TOP;
			}

			blockState = blockState.withProperty(HALF, value);
		}

		return blockState;
	}

	@Override
	public final int getMetaFromState(final IBlockState state)
	{
		if (this.isDouble())
		{
			return 0;
		}

		if ((EnumBlockHalf) state.getValue(HALF) == EnumBlockHalf.TOP)
		{
			return 8;
		} else
		{
			return 0;
		}
	}

	@Override
	protected final BlockStateContainer createBlockState()
	{
		if (this.isDouble())
		{
			return new BlockStateContainer(this, new IProperty[] { VARIANT });
		} else
		{
			return new BlockStateContainer(this, new IProperty[] { VARIANT, HALF });
		}
	}

	

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if(this.isDouble())
		{
			return Item.getItemFromBlock(this.singleSlab);
		}
		else
		{
			return Item.getItemFromBlock(this);
		}
	}  


	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		if(this.isDouble())
		{
			return new ItemStack(Item.getItemFromBlock(this.singleSlab));
		}
		else
		{
			return new ItemStack(Item.getItemFromBlock(this));
		}
			
	} 

}

package tschipp.tschipplib.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TSStair extends BlockStairs {
	
	private Block parent;
	private int parentMeta;
	
	private String MODID;

	public TSStair(String name, IBlockState parentState, String modid) {
		super(parentState);
		this.parent = parentState.getBlock();
		this.parentMeta = this.parent.getMetaFromState(parentState);
		this.useNeighborBrightness = true;
		this.MODID = modid;
		
		registerBlock(name);
	}
	
	public void registerBlock(String name)
	{
		super.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(MODID + ":" + name));
		Item item = new ItemBlock(this);
		item.setRegistryName(new ResourceLocation(MODID + ":" + name));
		ForgeRegistries.BLOCKS.register(this);
		ForgeRegistries.ITEMS.register(item);
	}

	public Block getParent() {
		return parent;
	}

	public int getParentMeta() {
		return parentMeta;
	} 
	
	

}

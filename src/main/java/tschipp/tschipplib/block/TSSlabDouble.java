package tschipp.tschipplib.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tschipp.tschipplib.item.ItemTSSlab;

public class TSSlabDouble extends TSSlab {

	public TSSlabDouble(String name, Material materialIn, Block parent, int parentMeta, Block halfSlab, String modid) {
		super(materialIn, parent, parentMeta, halfSlab);
		if(halfSlab != null)
		{
			registerBlock(name,modid, halfSlab);
		}
		else
		{
			throw new IllegalArgumentException("The Half Slab for " + this.toString() + " needs to be registred before the Double Slab");		
		}
	}

	@Override
	public boolean isDouble() {
		return true;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return null;
	}

	public void registerBlock(String name, String MODID, Block halfSlab)
	{
		super.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(MODID + ":" + name));
		Item item = new ItemTSSlab(halfSlab, (TSSlabHalf) halfSlab, this);
		item.setRegistryName(new ResourceLocation(MODID + ":" + ((TSSlabHalf) halfSlab).getRawName()));
		ForgeRegistries.BLOCKS.register(this);
		ForgeRegistries.ITEMS.register(item);
	} 


}


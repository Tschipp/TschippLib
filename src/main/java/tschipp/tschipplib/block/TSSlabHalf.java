package tschipp.tschipplib.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class TSSlabHalf extends TSSlab {
	
	private String rawName;

	public TSSlabHalf(String name, Material materialIn, Block parent, int parentMeta, String modid) {
		super(materialIn, parent, parentMeta, null);
		this.useNeighborBrightness = true;
		registerBlock(name, modid);
		rawName = name;
		
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return false;
	}

	public void registerBlock(String name, String MODID)
	{
		super.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(MODID + ":" + name));
		ForgeRegistries.BLOCKS.register(this);
	}

	public String getRawName() {
		return rawName;
	} 
	
	



}


package tschipp.tschipplib.util;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import tschipp.tschipplib.block.TSSlab;

public class TSBlockRendering {
	
	public TSBlockRendering()
	{
	}
	
	public TSBlockRendering(String modid)
	{
	}
	
	/**
	 * Registers Render for a Block
	 * @param block
	 */
	public static void reg(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));		
	}
	
	
	
	public static void regOtherName(Block block, Block other)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(other.getRegistryName(), "inventory"));		
	}
	
	
	/**
	 * Registers Render for a special Block (usually with Metadata)
	 * 
	 * @param block
	 * @param meta
	 * @param name
	 */
	public static void regSpecial(Block block, int meta, String name)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(block.getRegistryName().getResourceDomain() + ":" + name, "inventory"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new ResourceLocation(block.getRegistryName().getResourceDomain() + ":" + name));
		
	}
	
	
	/**
	 * Registers Render for a stair Block
	 * 
	 * @param block
	 */
	public static void regStair(Block block)
	{

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName().getResourceDomain() + ":/stair" + block.getUnlocalizedName().substring(5), "inventory"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new ModelResourceLocation(block.getRegistryName().getResourceDomain() + ":/stair" + block.getUnlocalizedName().substring(5), "inventory"));

	}
	
	
	public static void regStairNormal(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
	
	/**
	 * Registers Render for a slab Block
	 * 
	 * @param block
	 */
	public static void regSlab(Block block)
	{
		ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(TSSlab.VARIANT).build());
		ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new ModelResourceLocation(block.getRegistryName().getResourceDomain() + ":/slab" + block.getUnlocalizedName().substring(5), "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName().getResourceDomain() + ":/slab" + block.getUnlocalizedName().substring(5), "inventory"));

	}
	
	/**
	 * Registers Render for a Double Slab Block
	 * @param block
	 */
	
	public static void regSlabDouble(Block block)
	{
		ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(TSSlab.VARIANT).build());

	}
	
	
	public static void ignoreProperties(Block block, IProperty<?>... properties)
	{
		ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(properties).build());
	}

}

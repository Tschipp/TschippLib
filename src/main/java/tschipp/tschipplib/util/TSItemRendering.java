package tschipp.tschipplib.util;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class TSItemRendering {
	

	/**
	 * Registers Render for a Block
	 * @param item
	 */
	public static void reg(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));		
	}

}

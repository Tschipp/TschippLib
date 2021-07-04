package tschipp.tschipplib.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TSItemBlock extends ItemBlock
{

	public TSItemBlock(Block block, String name, String modid)
	{
		super(block);
		registerItem(name, modid);
	}

	private void registerItem(String name, String modid)
	{
		super.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(modid, name));
		ForgeRegistries.ITEMS.register(this);
	}

}

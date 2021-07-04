package tschipp.tschipplib.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TSBlock extends Block
{

	private String MODID;

	public TSBlock(String name, Material blockMaterialIn, MapColor blockMapColorIn, String modid)
	{
		super(blockMaterialIn, blockMapColorIn);
		MODID = modid;
		registerBlock(name, true);
	}

	public TSBlock(String name, Material blockMaterialIn, MapColor blockMapColorIn, String modid, boolean itemblock)
	{
		super(blockMaterialIn, blockMapColorIn);
		MODID = modid;
		registerBlock(name, itemblock);
	}

	private void registerBlock(String name, boolean itemblock)
	{
		super.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(MODID + ":" + name));
		ForgeRegistries.BLOCKS.register(this);
		if (itemblock)
		{
			Item item = new ItemBlock(this);
			item.setRegistryName(new ResourceLocation(MODID + ":" + name));
			ForgeRegistries.ITEMS.register(item);
		}
	}

}

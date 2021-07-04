package tschipp.tschipplib.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TSItem extends Item {

	
	public TSItem(String name, String modid)
	{
		registerItem(name, modid);

	}
	
	private void registerItem(String name, String MODID)
	{
		super.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(MODID + ":" + name));
		ForgeRegistries.ITEMS.register(this);
	} 

}

package tschipp.tschipplib.item;

import net.minecraft.item.ItemAxe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TSAxe extends ItemAxe {
	
	public TSAxe(String name, ToolMaterial material, String modid, float damage, float speed) {
		super(material, damage, speed);
		registerItem(name, modid);
	}
	
	
	private void registerItem(String name, String MODID)
	{
		super.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(MODID + ":" + name));
		ForgeRegistries.ITEMS.register(this);
	} 

}

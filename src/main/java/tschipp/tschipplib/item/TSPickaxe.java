package tschipp.tschipplib.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TSPickaxe extends ItemPickaxe {

	public TSPickaxe(String name, ToolMaterial material, String modid) {
		super(material);
		registerItem(name, modid);
	}
	
	
	private void registerItem(String name, String MODID)
	{
		super.setUnlocalizedName(name);
		this.setRegistryName(new ResourceLocation(MODID + ":" + name));
		ForgeRegistries.ITEMS.register(this);
	} 

}

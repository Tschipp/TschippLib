package tschipp.tschipplib.helper;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLLog;

public class ItemHelper
{

	public static Item getItemFromString(String string)
	{
		String name = null;
		if (string.contains(";"))
		{
			String meta = string.substring(string.indexOf(";"));
			name = string.replace(meta, "");
		}
		else
			name = string;

		return Item.getByNameOrId(name);
	}

	public static IBlockState getBlockStateFromString(String string)
	{
		Item item = getItemFromString(string);
		int meta = getMetaFromString(string);
		Block block = Block.getBlockFromItem(item);

		return block.getStateFromMeta(meta);
	}

	public static int getMetaFromString(String string)
	{
		if (string.contains(";"))
		{
			String meta = string.substring(string.indexOf(";"));
			meta = meta.replace(";", "");
			try
			{
				return Integer.parseInt(meta);
			}
			catch (Exception e)
			{
				FMLLog.getLogger().log(Level.ERROR, e);
			}
		}

		return 0;
	}

}

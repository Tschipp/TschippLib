package tschipp.tschipplib.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import tschipp.tschipplib.block.TSSlabDouble;
import tschipp.tschipplib.block.TSSlabHalf;

public class ItemTSSlab extends ItemSlab {

	public ItemTSSlab(Block block, TSSlabHalf halfSlab, TSSlabDouble doubleSlab)
	{
		super(block, halfSlab, doubleSlab);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
        return this.getUnlocalizedName();
    }

}

package tschipp.tschipplib.util;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tschipp.tschipplib.block.TSStair;

public class TSCrafting {
	
	
//	/**
//	 * Creates a crafting recipe for a 2x2 recipe
//	 * 
//	 * @param input
//	 * @param output
//	 */
//	
//	public static void craft2by2(ItemStack input, ItemStack output)
//	{
//		GameRegistry.addRecipe(output, new Object[] { "##", "##", '#', input });
//	}
//	
//	/**
//	 * Creates a shapeless OreDict-Recipe
//	 * 
//	 * @param output
//	 * @param params
//	 */
//	public static void craftShapelessOredict(ItemStack output, Object... params)
//	{
//		GameRegistry.addRecipe(new ShapelessOreRecipe(output, params));
//	}
//	
//	/**
//	 * Creates a shaped OreDict-Recipe
//	 * 
//	 * @param output
//	 * @param params
//	 */
//	public static void craftShapedOredict(ItemStack output, Object... params)
//	{
//		GameRegistry.addRecipe(new ShapedOreRecipe(output, params));
//	}
//
//	/**
//	 * Creates a stair crafting Recipe
//	 * 
//	 * @param block
//	 */
//	public static void craftStair(Block block)
//	{
//		TSStair input = ((TSStair)block);
//		Block parent = input.getParent();
//		int parentMeta = input.getParentMeta();
//		GameRegistry.addRecipe(new ItemStack(block, 4, 0), new Object[] { "#  ", "## ", "###", '#', new ItemStack(parent, 1, parentMeta) });
//	}
//
//	/**
//	 * Crafts a Slab using the given Block and its parent
//	 * 
//	 * @param block
//	 */
//	public static void craftSlab(Block block)
//	{
//		BlockExtraSlab input = ((BlockExtraSlab)block);
//		Block parent = input.getParent();
//		int parentMeta = input.getParentMeta();
//
//		GameRegistry.addRecipe(new ItemStack(block, 6, 0), new Object[] { "###", '#', new ItemStack(parent, 1, parentMeta) });
//		GameRegistry.addRecipe(new ItemStack(parent, 1, parentMeta), new Object[] { "#", "#", '#', new ItemStack(block) });
//		
//	}
//
//	/**
//	 * Registers a Smelting Recipe for an Item
//	 * 
//	 * @param input
//	 * @param output
//	 */
//	public static void smelt(ItemStack input, ItemStack output)
//	{
//		GameRegistry.addSmelting(input, output, 0.20F);
//	}
//	
//	/**
//	 * Removes a Recipe
//	 * 
//	 * @param toRemove
//	 */
//	public static void removeRecipe(ItemStack toRemove) 
//	{
//		List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
//		Iterator<IRecipe> recipe = recipeList.iterator();
//
//		while (recipe.hasNext())
//		{
//			ItemStack stack = recipe.next().getRecipeOutput();
//
//			if (stack != null && stack.areItemsEqual(stack, toRemove))
//			{
//				recipe.remove();
//			} 
//		}
//	}
//	
//	/**
//	 * Registers a new IRecipe
//	 * @param recipe
//	 */
//	public static void reg(IRecipe recipe)
//	{
//		GameRegistry.addRecipe(recipe);
//	}
//	
	

}

package tschipp.tschipplib.compat.gamestages;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blamejared.recipestages.handlers.Recipes;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.darkhax.itemstages.ItemStages;
import net.darkhax.itemstages.compat.crt.ActionAddItemRestriction;
import net.darkhax.itemstages.compat.crt.ActionRemoveRestriction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import tschipp.tschipplib.TschippLib;

@ZenRegister
@ZenClass("mods.tschipplib.gamestagehelper")
public class GamestagesHelper
{
	private static Method stagePrimitive;
	private static Class<?> ctIntegration;

	@ZenMethod
	public static void stageRecipes(Map<String, Integer> stageMap, List<String> blacklist)
	{
		CraftTweakerAPI.apply(new StageRecipes(stageMap, blacklist));
	}

	private static class StageRecipes implements IAction
	{

		private Map<String, Integer> stageMap;
		private List<String> blacklist;

		public StageRecipes(Map<String, Integer> stageMap, List<String> blacklist)
		{
			this.stageMap = stageMap;
			this.blacklist = blacklist;
		}

		@Override
		public void apply()
		{
			List<IRecipe> recipes = ForgeRegistries.RECIPES.getValues();
			HashMap<String, Integer> stagedItemStacks = new HashMap<String, Integer>();

			HashMap<String, String> knownIngredients = new HashMap<String, String>();

			long totalTime = System.currentTimeMillis();

			TschippLib.LOGGER.debug("Starting mass auto staging of recipes. Staging " + recipes.size() + " recipes in total");

			for (IRecipe recipe : recipes)
			{
				long startTime = System.currentTimeMillis();

				boolean blacklisted = false;

				if (blacklist.contains(recipe.getRegistryName().toString()))
					blacklisted = true;

				boolean recipeUnstaged = true;
				String stageName = "";

				for (Ingredient ingr : recipe.getIngredients())
				{
					String str = ingredientString(ingr);

					if (knownIngredients.containsKey(str))
					{
						String stage = knownIngredients.get(str);

						if (!stage.equals("TSCHIPPLIBUNSTAGED"))
						{
							recipeUnstaged = false;
							stageName = stage;
						}
						continue;
					}

					boolean ingredientStaged = true;
					int lowestIngredientStage = Integer.MAX_VALUE;
					boolean nonNull = false;

					for (ItemStack stack : ingr.getMatchingStacks())
					{
						if (!stack.isEmpty())
						{

							nonNull = true;

							boolean itemstackStaged = false;
							String stage = ItemStages.ITEM_STAGES.get(stack);
							if (stage != null)
							{
								Integer stageVal = stageMap.get(stage);
								if (stageVal != null)
								{
									if (stageVal < lowestIngredientStage)
									{
										stageName = stage;
										lowestIngredientStage = stageVal;
										itemstackStaged = true;
									}
								}
							}

							if (!itemstackStaged)
								ingredientStaged = false;
						}

					}

					if (ingredientStaged && nonNull)
					{
						knownIngredients.put(str, stageName);
						recipeUnstaged = false;
					}
					else if (!ingredientStaged)
					{
						knownIngredients.put(str, "TSCHIPPLIBUNSTAGED");
					}
				}


				if (recipeUnstaged)
					stagedItemStacks.put(itemString(recipe.getRecipeOutput()), Integer.MIN_VALUE);

				if (!recipeUnstaged && !stageName.isEmpty())
				{
					int stageVal = stageMap.get(stageName);

					if (stagedItemStacks.containsKey(itemString(recipe.getRecipeOutput())))
					{
						int otherStageVal = stagedItemStacks.get(itemString(recipe.getRecipeOutput()));

						if (otherStageVal < stageVal)
							blacklisted = true;
					}

					if (!blacklisted)
						stagedItemStacks.put(itemString(recipe.getRecipeOutput()), stageMap.get(stageName));

					IItemStack output = CraftTweakerMC.getIItemStack(recipe.getRecipeOutput());

					String outputStage = ItemStages.ITEM_STAGES.get(recipe.getRecipeOutput().copy().splitStack(1));

					if (outputStage != null)
					{
						int outputStageNum = stageMap.get(outputStage);
						if (outputStageNum > stageVal)
							stageName = outputStage;
					}


					if (!blacklisted)
					{
						if (!stagedItemStacks.containsKey(itemString(recipe.getRecipeOutput())))
						{
							CraftTweakerAPI.apply(new ActionRemoveRestriction(output));
							CraftTweakerAPI.logInfo("Staging " + output.getName() + " with stage " + stageName);
							CraftTweakerAPI.apply(new ActionAddItemRestriction(stageName, output));
						}
					}

					Recipes.setRecipeStage(stageName, recipe.getRegistryName().toString());
					CraftTweakerAPI.logInfo("Staging Recipe " + recipe.getRegistryName().toString() + " with stage " + stageName);

					if (Loader.isModLoaded("primitivecrafting"))
					{
						if (stagePrimitive == null && ctIntegration == null)
						{
							try
							{
								ctIntegration = Class.forName("tschipp.primitivecrafting.compat.crafttweaker.CTIntegration");
								stagePrimitive = ctIntegration.getMethod("addRecipeStageForStack", String.class, IItemStack.class);
							}
							catch (ClassNotFoundException | NoSuchMethodException | SecurityException e)
							{
								e.printStackTrace();
							}
						}

						if (stagePrimitive != null && ctIntegration != null)
						{
							try
							{
								stagePrimitive.invoke(null, stageName, output);
								CraftTweakerAPI.logInfo("Staging Primitive Recipe " + output.getName() + " with stage " + stageName);
							}
							catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
							{
								e.printStackTrace();
							}
						}
					}

				}

				TschippLib.LOGGER.debug("Processed recipe " + recipe.getRegistryName() + " in " + (System.currentTimeMillis() - startTime) + " ms");

			}

			TschippLib.LOGGER.info("Finished processing recipes. Took " + ((System.currentTimeMillis() - totalTime) / 1000) + " s");
		}

		@Override
		public String describe()
		{
			return "Staging all recipes";
		}

	}

	public static String itemString(ItemStack stack)
	{
		return stack.getItem().getRegistryName() + "@" + stack.getMetadata() + "$" + (stack.hasTagCompound() ? stack.getTagCompound().toString() : "");
	}

	public static String ingredientString(Ingredient ing)
	{
		String name = "";
		for (ItemStack stack : ing.getMatchingStacks())
		{
			name += itemString(stack) + "#";
		}
		return name;
	}

}

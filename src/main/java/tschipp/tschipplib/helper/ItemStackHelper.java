package tschipp.tschipplib.helper;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class ItemStackHelper
{

	public static boolean hasItemHeld(ItemStack stack, EntityPlayer player, boolean sensitive)
	{
		if (stack.isEmpty())
			return false;

		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();

		if (!sensitive)
		{
			if (!main.isEmpty() && main.getItem() == stack.getItem())
				return true;
			else if (!off.isEmpty() && off.getItem() == stack.getItem())
				return true;
			else
				return false;
		} else
		{
			if (ItemStack.areItemStacksEqual(main, stack))
				return true;
			else if (ItemStack.areItemStacksEqual(off, stack))
				return true;
			else
				return false;
		}
	}

	public static boolean hasItemHeld(Class<?> type, EntityPlayer player)
	{
		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();

		if (!main.isEmpty() && type.isInstance(main.getItem()))
			return true;
		else if (!off.isEmpty() && type.isInstance(off.getItem()))
			return true;
		else
			return false;

	}
	
	public static boolean hasTypeInHand(Class<?> type, EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		return !stack.isEmpty() && type.isInstance(stack.getItem());
	}

	@Nullable
	public static EnumHand getHandForStack(ItemStack stack, EntityPlayer player, boolean sensitive)
	{
		if (stack.isEmpty())
			return null;

		if (!hasItemHeld(stack, player, sensitive))
			return null;

		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();

		if (!sensitive)
		{
			if (!main.isEmpty() && main.getItem() == stack.getItem())
				return EnumHand.MAIN_HAND;
			else if (!off.isEmpty() && off.getItem() == stack.getItem())
				return EnumHand.OFF_HAND;
			else
				return null;
		} else
		{
			if (ItemStack.areItemStacksEqual(main, stack))
				return EnumHand.MAIN_HAND;
			else if (ItemStack.areItemStacksEqual(off, stack))
				return EnumHand.OFF_HAND;
			else
				return null;
		}

	}

	@Nullable
	public static EnumHand getHandForType(Class<?> type, EntityPlayer player)
	{

		if (!hasItemHeld(type, player))
			return null;

		ItemStack main = player.getHeldItemMainhand();
		ItemStack off = player.getHeldItemOffhand();

		if (!main.isEmpty() && type.isInstance(main.getItem()))
			return EnumHand.MAIN_HAND;
		else if (!off.isEmpty() && type.isInstance(off.getItem()))
			return EnumHand.OFF_HAND;
		else
			return null;

	}

	public static void giveItem(ItemStack stack, EntityPlayer player)
	{
		if (!player.inventory.addItemStackToInventory(stack))
			player.dropItem(stack, true);
	}

}

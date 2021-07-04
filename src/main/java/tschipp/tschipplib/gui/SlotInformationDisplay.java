package tschipp.tschipplib.gui;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class SlotInformationDisplay extends Gui
{

	private int x;
	private int y;
	private int xWidth;
	private int yWidth;
	private String info;
	private TileEntity tile;
	private int slot;
	private ResourceLocation icon = null;

	/**
	 * The SlotInformationDisplay provides a Hover Text over a Slot
	 * 
	 * @param x The upper left xPosition where the info should be drawn
	 * @param y The upper left yPosition where the info should be drawn
	 * @param info The Information that will be displayed, will be translated
	 * @param xWidth The xWidth of the GUI, most of the time this.guiLeft
	 * @param yWidth The yWidth of the GUI, most of the time this.guiTop
	 * @param tile The Tile entity that has the item Capability
	 * @param slot The slot index of the display
	 * @param icon The Display icon that displays in the background
	 */
	public SlotInformationDisplay(int x, int y, String info, int xWidth, int yWidth, TileEntity tile, int slot, ResourceLocation icon)
	{
		this.x = x;
		this.y = y;
		this.info = info;
		this.xWidth = xWidth;
		this.yWidth = yWidth;
		this.tile = tile;
		this.slot = slot;
		this.icon = icon;
	}

	/**
	 * The SlotInformationDisplay provides a Hover Text over a Slot
	 * 
	 * @param x The upper left xPosition where the info should be drawn
	 * @param y The upper left yPosition where the info should be drawn
	 * @param info The Information that will be displayed, will be translated
	 * @param xWidth The xWidth of the GUI, most of the time this.guiLeft
	 * @param yWidth The yWidth of the GUI, most of the time this.guiTop
	 * @param tile The Tile entity that has the item Capability
	 * @param slot The slot index of the display
	 */
	public SlotInformationDisplay(int x, int y, String info, int xWidth, int yWidth, TileEntity tile, int slot)
	{
		this(x, y, info, xWidth, yWidth, tile, slot, null);
	}

	public void drawDisplay()
	{
		if (this.tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
		{
			IItemHandler handler = this.tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			if (handler.getStackInSlot(slot).isEmpty())
			{
				if (this.icon != null)
				{
					Minecraft mc = Minecraft.getMinecraft();
					mc.getTextureManager().bindTexture(this.icon);
					this.drawModalRectWithCustomSizedTexture(this.xWidth + this.x, this.yWidth + this.y, 0, 0, 16, 16, 16, 16);
				}
			}
		}

	}

	public void drawOverlay(int x, int y)
	{
		if (this.tile.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
		{
			IItemHandler handler = this.tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			ItemStack stack = handler.getStackInSlot(slot);
			if (stack.isEmpty())
			{

				if (x >= this.xWidth + this.x && x <= this.xWidth + this.x + 16 && y >= this.yWidth + this.y && y <= this.yWidth + this.y + 16)
				{
					ArrayList<String> text = new ArrayList<String>();
					text.add(I18n.translateToLocal(this.info));
					GuiUtils.drawHoveringText(text, x, y, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, -1, Minecraft.getMinecraft().fontRenderer);
				}
			}
		}

	}

}

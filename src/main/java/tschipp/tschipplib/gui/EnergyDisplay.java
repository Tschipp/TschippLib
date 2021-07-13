package tschipp.tschipplib.gui;

import java.util.ArrayList;

import cofh.redstoneflux.api.IEnergyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

public class EnergyDisplay extends Gui {

	private IEnergyHandler energy;
	private int xWidth;
	private int yWidth;
	private ResourceLocation texture;
	private int textX;
	private int textY;
	private int drawX;
	private int drawY;
	private int barWidth;
	private int barHeight;
	
	/**
	 * The EnergyDisplay is a simple display that asks for a IEnergyHandler
	 * 
	 * @param energy The IEnergyHandler
	 * @param xWidth The xWidth of the GUI, most of the time this.guiLeft
	 * @param yWidth The yWidth of the GUI, most of the time this.guiTop
	 * @param texture The resource location of the energy texture
	 * @param textX the upper left xPosition of the energy texture
	 * @param textY the upper left yPosition of the energy texture
	 * @param drawX the upper left xPosition where the energy bar should be drawn
	 * @param drawY the upper left yPosition where the energy bar should be drawn
	 * @param barWidth the x width of the bar
	 * @param barHeight the y height of the bar
	 */
	public EnergyDisplay(IEnergyHandler energy, int xWidth, int yWidth, ResourceLocation texture, int textX, int textY, int drawX, int drawY, int barWidth, int barHeight) 
	{

		this.energy = energy;
		this.xWidth = xWidth;
		this.yWidth = yWidth;
		this.textX = textX;
		this.textY = textY;
		this.texture = texture;
		this.drawX = drawX;
		this.drawY = drawY;
		this.barWidth = barWidth;
		this.barHeight = barHeight;
	}


	public void drawDisplay()
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(this.texture);
		int stored = energy.getEnergyStored(null);
		int max = energy.getMaxEnergyStored(null);
		float e = 1- ((float)stored / (float)max);
		
		this.drawTexturedModalRect(this.xWidth + this.drawX, this.yWidth + this.drawY, this.textX, this.textY, this.barWidth, (int) (this.barHeight * e));
	}

	public void drawOverlay(int x, int y)
	{
		if(x >= this.xWidth + this.drawX && x <= this.xWidth + this.drawX + this.barWidth && y >= this.yWidth + this.drawY && y <= this.yWidth + this.drawY + this.barHeight)
		{
			int energy = this.energy.getEnergyStored(null);
			ArrayList<String> text = new ArrayList<String>();
			text.add(energy + "/" + this.energy.getMaxEnergyStored(null) + "RF");	

			GuiUtils.drawHoveringText(text, x, y, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, -1, Minecraft.getMinecraft().fontRenderer);
		}
		
	}
}
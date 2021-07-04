package tschipp.tschipplib.gui;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

public class ProgressDisplay extends Gui {

	private IProgressable progressable;
	private int xWidth;
	private int yWidth;
	private ResourceLocation texture;
	private int textX;
	private int textY;
	private int drawX;
	private int drawY;
	private int barWidth;
	private int barHeight;
	private boolean sideways;

	/**
	 * The ProgressDisplay is a simple display that asks for a IProgressable
	 * 
	 * @param progressable The IProgressable
	 * @param xWidth The xWidth of the GUI, most of the time this.guiLeft
	 * @param yWidth The yWidth of the GUI, most of the time this.guiTop
	 * @param texture The resource location of the progress texture
	 * @param textX the upper left xPosition of the progress texture
	 * @param textY the upper left yPosition of the progress texture
	 * @param drawX the upper left xPosition where the progress bar should be drawn
	 * @param drawY the upper left yPosition where the progress bar should be drawn
	 * @param barWidth the x width of the bar
	 * @param barHeight the y height of the bar
	 * @param sideways Whether the bar should progress to the side (left to right) or down (up to down)
	 * 
	 * @author Tschipp
	 */
	public ProgressDisplay(IProgressable progressable, int xWidth, int yWidth, ResourceLocation texture, int textX, int textY, int drawX, int drawY, int barWidth, int barHeight, boolean sideways) 
	{

		this.xWidth = xWidth;
		this.yWidth = yWidth;
		this.textX = textX;
		this.textY = textY;
		this.texture = texture;
		this.progressable = progressable;
		this.drawX = drawX;
		this.drawY = drawY;
		this.barWidth = barWidth;
		this.barHeight = barHeight;
		this.sideways = sideways;
	}


	public void drawDisplay()
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(texture);
		int progress = progressable.getProgress();
		int max = progressable.getMaxProgress();
		float p =  ((float)progress / (float)max);

		if(this.sideways)
			this.drawTexturedModalRect(this.xWidth + this.drawX, this.yWidth + this.drawY - 1, this.textX, this.textY, (int) (this.barWidth * p), this.barHeight);
		else
			this.drawTexturedModalRect(this.xWidth + this.drawX, this.yWidth + this.drawY - 1, this.textX, this.textY, this.barWidth, (int) (this.barHeight * p));
		
	
	}

	public void drawOverlay(int x, int y)
	{
		if(x >= this.xWidth + this.drawX && x <= this.xWidth + this.drawX + this.barWidth && y >= this.yWidth + this.drawY && y <= this.yWidth + this.drawY + this.barHeight)
		{
			int progress = progressable.getProgress();
			int max = progressable.getMaxProgress();
			float p =  ((float)progress / (float)max);

			ArrayList<String> text = new ArrayList<String>();
			text.add((int)(p * 100) + "%");	

			GuiUtils.drawHoveringText(text, x, y, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, -1, Minecraft.getMinecraft().fontRenderer);
		}

	} 
}
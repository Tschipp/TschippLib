package tschipp.tschipplib.helper;

import java.awt.Color;

import net.minecraft.util.math.MathHelper;

public class ColorHelper {

	public static float[] getRGB(Color color)
	{
		if(color == null) return null;
		
		float r = (float)color.getRed() / (float)255;
		float g = (float)color.getGreen() / (float)255;
		float b = (float)color.getBlue() / (float)255;
		return new float[]{r,g,b};
	}
	
	public static float[] getRGB(int... color)
	{
		if(color == null || color.length < 3) return null;
		
		float r = (float)color[0] / (float)255;
		float g = (float)color[1] / (float)255;
		float b = (float)color[2] / (float)255;
		return new float[]{r,g,b};
	}
	
	public static float[] brighter(float amount, float... rgb)
	{
		if(rgb.length < 3) return null;
		int r = (int) (rgb[0] * 255);
		int g = (int) (rgb[1] * 255);
		int b = (int) (rgb[2] * 255);
		
		float[] hsb = Color.RGBtoHSB(r, g, b, new float[3]);
		hsb[1] += amount;
		
		hsb = clampColor(hsb);
		
		Color col = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
		return getRGB(col);
	}
	
	public static float[] darker(float amount, float... rgb)
	{
		if(rgb.length < 3) return null;
		int r = (int) (rgb[0] * 255);
		int g = (int) (rgb[1] * 255);
		int b = (int) (rgb[2] * 255);
		
		float[] hsb = Color.RGBtoHSB(r, g, b, new float[3]);
		hsb[2] -= amount;
		
		hsb = clampColor(hsb);
		
		Color col = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
		return getRGB(col);
	}
	
	public static float[] changeBrightness(float amount, float... rgb)
	{
		if(amount < 0)
			return darker(Math.abs(amount), rgb);
		else
			return brighter(amount, rgb);
	}
	
	private static float[] clampColor(float[] col)
	{
		col[0] = MathHelper.clamp(col[0], 0.0f, 1.0f);
		col[1] = MathHelper.clamp(col[1], 0.0f, 1.0f);
		col[2] = MathHelper.clamp(col[2], 0.0f, 1.0f);
		
		return col;

	}
	
}

package easyFlyer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageUtil {
	public static BufferedImage makeTransparent(BufferedImage image , float transp)
	{
		BufferedImage result = new BufferedImage(image.getWidth() , 
				image.getHeight() , image.getType());
		
		Graphics g = result.getGraphics();
		for(int i = 0 ; i < image.getWidth() ; i++)
		{
			for(int j = 0 ; j < image.getHeight(); j++)
			{
				Color temp = new Color(image.getRGB(i , j));
				temp = new Color(temp.getRed() , temp.getGreen() , 
						temp.getBlue() , (int)(transp * 255));
				
				result.setRGB(i , j, temp.getRGB());
			}
		}
		
		return result;
	}
}

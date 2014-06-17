package easyFlyer.model.decorator;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import easyFlyer.model.ImageComponent;

public class ImageFrame 
	extends Decorator
{	
	private static final long serialVersionUID = 4762618945764012057L;

	public ImageFrame(ImageComponent content)
	{
		super(content , FRAME);
	}
	
	public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D) gr;
		
		g.setStroke(new BasicStroke(4));
		g.drawRect(bounds.x - 2 , bounds.y - 2 , bounds.width + 2 , bounds.height + 2);
		g.setStroke(new BasicStroke(1));
		
		content.paintComponent(g);
	}
}
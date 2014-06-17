package easyFlyer.model.decorator;

import java.awt.Color;
import java.awt.Graphics;

import easyFlyer.model.TextComponent;

public class ColorDecorator 
	extends TextDecorator
{
	private static final long serialVersionUID = 6968136374679616074L;

	private Color color;
	
	public ColorDecorator(TextComponent component , Color color)
	{
		super(component , COLOR);
		
		this.color = color;
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(color);
		
		content.paintComponent(g);
		
		g.setColor(Color.black);
	}
}

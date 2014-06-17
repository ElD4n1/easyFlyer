package easyFlyer.model.decorator;

import java.awt.Font;
import java.awt.Graphics;

import easyFlyer.model.TextComponent;

public class FontDecorator 
	extends TextDecorator
{
	private static final long serialVersionUID = 2561465775444082056L;

	private Font font;
	
	public FontDecorator(TextComponent component , Font font)
	{
		super(component , FONT);
		
		this.font = font;
	}
	
	public void paintComponent(Graphics g)
	{
		Font tempFont = g.getFont();
		
		g.setFont(font);
		
		content.paintComponent(g);
		
		g.setFont(tempFont);
	}
}
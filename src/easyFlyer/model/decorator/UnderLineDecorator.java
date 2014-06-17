package easyFlyer.model.decorator;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.util.Map;

import easyFlyer.model.TextComponent;

public class UnderLineDecorator 
	extends TextDecorator
{
	private static final long serialVersionUID = 5676293042014496130L;

	public UnderLineDecorator(TextComponent component)
	{
		super(component , TextDecorator.UNDERLINE);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void paintComponent(Graphics g)
	{
		Font tempFont = g.getFont();
		
		Map attributes = tempFont.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		
		g.setFont(tempFont.deriveFont(attributes));
		
		content.paintComponent(g);
		
		g.setFont(tempFont);
	}
}

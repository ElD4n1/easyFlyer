package easyFlyer.model;

import java.awt.Graphics;

import easyFlyer.model.event.FlyerComponentEvent;

public class TextComponent 
	extends FlyerComponent
{
	private static final long serialVersionUID = -5473618717874172363L;

	private String text;
	
	public TextComponent(String text)
	{
		this.text = text;
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawString(text , bounds.x , bounds.y);
	}
	
	public String getText(){return text;}
	
	public void setText(String text)
	{
		this.text = text;
		
		fireComponentChanged(FlyerComponentEvent.CONTENT_CHANGED);
	}
}

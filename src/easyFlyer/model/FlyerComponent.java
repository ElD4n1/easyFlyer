package easyFlyer.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import easyFlyer.model.event.FlyerComponentEvent;
import easyFlyer.model.event.FlyerComponentListener;

public abstract class FlyerComponent 
	implements Serializable
{
	private static final long serialVersionUID = 7715057318882097068L;

	protected Rectangle bounds;
	
	private List<FlyerComponentListener> listeners = new ArrayList<FlyerComponentListener>();
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public void setBounds(Rectangle bounds)
	{
		this.bounds = bounds;
		
		fireComponentChanged(FlyerComponentEvent.COMPONENT_RESIZED);
	}
	
	/*
	 * event handling
	 */
	public void addFlyerComponentListener(FlyerComponentListener listener)
	{
		listeners.add(listener);
	}
	
	protected void fireComponentChanged(int id)
	{
		for(FlyerComponentListener listener : listeners)
			listener.flyerComponentChanged(new FlyerComponentEvent(this , id));
	}
	
	public abstract void paintComponent(Graphics g);
}

package easyFlyer.model.event;

import java.util.EventObject;

import easyFlyer.model.FlyerComponent;

public class FlyerComponentEvent 
	extends EventObject 
{
	private static final long serialVersionUID = -9134913647155412659L;

	public static final int CONTENT_CHANGED = 0;
	public static final int COMPONENT_RESIZED = 1;
	public static final int COMPONENT_DECORATED = 2;
	public static final int COMPONENT_UNDECORATED = 3;

	private int id;
	
	public FlyerComponentEvent(FlyerComponent src , int id) {
		super(src);
		
		this.id = id;
	}
	
	public int getID(){return id;}
}

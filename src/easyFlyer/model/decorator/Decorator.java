package easyFlyer.model.decorator;

import java.awt.Rectangle;

import easyFlyer.model.FlyerComponent;
import easyFlyer.model.event.FlyerComponentEvent;

public abstract class Decorator
	extends FlyerComponent
{
	private static final long serialVersionUID = -4924114506609325464L;
	
	public static final int UNDERLINE = 0;
	public static final int COLOR = 1;
	public static final int FONT = 2;
	
	public static final int FRAME = 3;
	
	protected FlyerComponent content;
	
	private int id;
	
	protected Decorator(FlyerComponent component , int id)
	{
		super(component.getBounds());
		content = component;
		
		fireComponentChanged(FlyerComponentEvent.COMPONENT_DECORATED);
		
		this.id = id;
		
		bounds = component.getBounds();
	}
	
	public FlyerComponent undecorate(int decoratorID)
			throws DecoratorException
		{
			if(decoratorID == id)
			{
				fireComponentChanged(FlyerComponentEvent.COMPONENT_UNDECORATED);
				
				return content;
			}else{
				if(content instanceof Decorator)
				{
					content = ((TextDecorator) content).undecorate(decoratorID);
			
					return this;
				}else
				{
					throw new DecoratorException();
				}
			}
		}
}

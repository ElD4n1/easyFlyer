package easyFlyer.model.decorator;

import easyFlyer.model.TextComponent;
import easyFlyer.model.event.FlyerComponentEvent;

public abstract class TextDecorator 
	extends Decorator
{	
	private static final long serialVersionUID = 2172490838270001685L;

	protected TextComponent content;
	
	protected int id;
	
	protected TextDecorator(TextComponent content , int id)
	{
		super(content , id);
		
		fireComponentChanged(FlyerComponentEvent.COMPONENT_DECORATED);
	}
}
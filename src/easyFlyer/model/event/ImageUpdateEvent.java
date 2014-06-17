package easyFlyer.model.event;

import java.io.File;
import java.util.EventObject;

public class ImageUpdateEvent 
	extends EventObject
{
	public ImageUpdateEvent(File file)
	{
		super(file);
	}
	
	public File getFile()
	{
		return (File)source;
	}
}

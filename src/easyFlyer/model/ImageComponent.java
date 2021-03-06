package easyFlyer.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import easyFlyer.model.event.FlyerComponentEvent;
import easyFlyer.model.event.ImageUpdateEvent;
import easyFlyer.model.event.ImageUpdateListener;

public class ImageComponent 
	extends FlyerComponent
	implements ImageUpdateListener
{
	private static final long serialVersionUID = -823004897985333544L;

	private BufferedImage image;
	
	public ImageComponent(BufferedImage image, int x, int y)
		throws IOException
	{
		super(new Rectangle(x, y, image.getWidth(), image.getHeight()));
		this.image = image;
		
		bounds.setSize(image.getWidth() , image.getHeight());
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(image , bounds.x , bounds.y , null);
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
		
		fireComponentChanged(FlyerComponentEvent.CONTENT_CHANGED);
	}

	@Override
	public void updateImage(ImageUpdateEvent e) // TODO ?? Paul
		throws IOException
	{
		//image = ImageIO.read(file);
		
		bounds.setSize(image.getWidth() , image.getHeight());
		
		fireComponentChanged(FlyerComponentEvent.COMPONENT_RESIZED);
	}
}

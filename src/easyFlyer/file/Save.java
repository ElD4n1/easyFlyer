package easyFlyer.file;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import easyFlyer.model.FlyerComponent;

public class Save {
	/**
	 * Speichert die Komponent in der entsprechenden Datei und fügt den Header hinzu
	 * 
	 * @param components die Komponenten des Flyers
	 * @param header Der Header der Datei
	 * @param info @see {@link easyFlyer.file.Load#load(String...)} info-spezifikation
	 * @throws ZipException
	 * @throws IOException
	 * @throws FileFormatNotSupportedException
	 */
	/* info: 0 - fileName / 1 - format / 2 - zipEntryName */
	@SuppressWarnings("resource")
	public static void save(ArrayList<FlyerComponent> components , Header header , String... info) 
			throws ZipException, IOException, FileFormatNotSupportedException
	{
		ObjectOutputStream oos;
		
		if(info.length < 2)
		{
			throw new IllegalArgumentException("At least two infos required: fileName and format");
		}
		
		String fileName = info[0];
		String format = info[1];
		
		switch(format)
		{
			case "zip":
			{
				if(info.length < 3)
				{
					throw new IllegalArgumentException("At least three infos required:"
							+ "fileName, format and zipentryName");
				}
				
				ZipOutputStream zos = 
						new ZipOutputStream(new FileOutputStream(createFile(fileName)));
				
				ZipEntry entry = new ZipEntry(info[2]);
				zos.putNextEntry(entry);
				
				oos = new ObjectOutputStream(zos);
			}
			break;
				
			case "esf":
			case "temp":
			{	
				oos = new ObjectOutputStream(new FileOutputStream(createFile(fileName)));
			}
			break;
			
			case "jpg":
			{
				int width = 0 , height = 0;
				
				for(FlyerComponent comp : components)
				{
					Rectangle bounds = comp.getBounds();
					
					int nx = bounds.x + bounds.width;
					int ny = bounds.y + bounds.height;
					
					nx = (nx > width ? nx : width);
					ny = (ny > height ? ny : height);
				}
				
				BufferedImage tempImage = new BufferedImage(width , height , BufferedImage.TYPE_INT_RGB);
				
				Graphics g = tempImage.getGraphics();
				for(FlyerComponent comp : components)
				{
					comp.paintComponent(g);
				}
				g.dispose();
				
				ImageIO.write(tempImage , "jpg" , createFile(fileName));
			}
			return;
			
			default:
				throw new FileFormatNotSupportedException(format);
		}
		
		oos.writeObject(header);
		oos.writeObject(components);
		oos.flush();
		oos.close();
	}
	
	private static File createFile(String fileName)
		throws IOException
	{
		File file = new File(fileName);
		
		if(!file.exists())
		{
			if(!file.createNewFile())
			{
				throw new IOException("File creation failed");
			}
		}
		
		return file;
	}
}

package easyFlyer.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import easyFlyer.model.FlyerComponent;

public class Load {
	public static ArrayList<FlyerComponent> load(String... infos)
		throws IOException, FileFormatNotSupportedException
	{
		if(infos.length < 2)
		{
			throw new IllegalArgumentException("At least two infos required: fileName and format");
		}
		
		ObjectInputStream ois;
		
		String fileName = infos[0];
		String format = infos[1];
		
		File file = new File(fileName);
		if(!file.exists())
		{
			throw new IOException("File doesn't exist");
		}
		
		switch(format)
		{
			case "zip":
			{
				if(infos.length < 3)
				{
					throw new IllegalArgumentException("At least three infos required:" +
									"fileName, format and zipentryName");
				}
				
				ZipFile zip = new ZipFile(fileName);
				InputStream is = zip.getInputStream(new ZipEntry(infos[2]));
				if(is == null)
				{
					zip.close();
					throw new IOException("ZipEntry doesn't exist");
				}
				
				ois = new ObjectInputStream(is);
			}
			break;
			
			case "esf":
			case "temp":
			{
				ois = new ObjectInputStream(new FileInputStream(file));
			}
			break;
		
			default:
				throw new FileFormatNotSupportedException(format);
		}
		
		try
		{
			//TODO ?
			Header header = (Header) ois.readObject();
			
			ArrayList<FlyerComponent> flyerComp = (ArrayList<FlyerComponent>) ois.readObject();
			
			return flyerComp;
		}catch(ClassCastException | ClassNotFoundException e)
		{
			throw new IOException("File is damaged");
		}
	}
}

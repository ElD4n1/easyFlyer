package easyFlyer.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import easyFlyer.model.FlyerComponent;

public class Save {
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

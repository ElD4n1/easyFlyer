package easyFlyer.api;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;

import easyFlyer.file.FileFormatNotSupportedException;
import easyFlyer.file.Header;
import easyFlyer.file.Load;
import easyFlyer.file.Save;
import easyFlyer.model.Flyer;
import easyFlyer.model.FlyerComponent;
import easyFlyer.model.ImageComponent;
import easyFlyer.model.TextComponent;

public class GuiApi_Impl implements GuiApi {

	private String filename; //TODO initializein newFile per Dialog
	private Flyer flyer;
	
	@Override
	public void newFile(String author, String name, String description, int height, int width, int border) {
		// TODO Auto-generated method stub
		this.flyer = new Flyer(name, height, width, border);
		
		try {
			Save.save(flyer.getComponents(), new Header(author, description), filename, "temp"); //TODO header
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileFormatNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void loadFile(File file) {
		try {
			Load.load(file.getName(), "esf");
		} catch (IOException | FileFormatNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Name = Path??? with .ext???
	}

	@Override
	public void saveFile(File file) {
		try {
			Save.save(flyer.getComponents(), null, filename, "esf"); // TODO Header, FileExt
		} catch (IOException | FileFormatNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public File getFilePath() {
		return new File(this.filename);
	}

	@Override
	public void exportFile(File file) { //TODO ?? wegtun
		// TODO Auto-generated method stub

	}

	@Override
	public void exportFolder(File file) { //TODO wegtun??
		// TODO Auto-generated method stub

	}

	@Override
	public void changeDoumentSettings(String name, int height, int width,
			int border) {
		this.flyer.setName(name);
		this.flyer.setHeight(height);
		this.flyer.setWidth(width);
		this.flyer.setBorder(border);
	}

	@Override
	public void addPicture(BufferedImage image) {
		try {
			this.flyer.addComponent(new ImageComponent(image));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public FlyerComponent chooseComponent(MouseEvent me) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addText(String text) {
		this.flyer.addComponent(new TextComponent(text));
	}

	@Override
	public void removeComponent(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addText(java.awt.TextComponent text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FlyerComponent getSelected() {
		// TODO Auto-generated method stub
		return null;
	}
}

package easyFlyer.api;

import java.awt.Graphics;
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

	private String filename; 
	private String author;
	private String description;
	private Flyer flyer;
	private Graphics graphics;
	
	@Override
	public void newFile(String author, String name, String filename, String description, int height, int width, int border, Graphics g) {
		this.flyer = new Flyer(name, height, width, border);
		this.author = author;
		this.description = description;
		this.filename = filename;
		
		try {
			Save.save(flyer.getComponents(), new Header(author, description), filename, "temp"); 
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FileFormatNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadFile(File file) {
		try {
			Load.load(file.getName(), "esf");
		} catch (IOException | FileFormatNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveFile(File file) {
		try {
			Save.save(flyer.getComponents(), new Header(this.author, this.description), filename, "esf");
		} catch (IOException | FileFormatNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public File getFilePath() {
		return new File(this.filename);
	}

	@Override
	public void exportFile(File file) { //TODO
	}

	@Override
	public void exportFolder(File file) { //TODO wegtun??
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
			e.printStackTrace();
		}
	}

	@Override
	public FlyerComponent chooseComponent(MouseEvent me) { //TODO
		return null;
	}

	@Override
	public void addText(String text) {
		this.flyer.addComponent(new TextComponent(text));
	}

	@Override
	public void removeComponent(MouseEvent event) {
		//TODO
	}

	@Override
	public FlyerComponent getSelected() { //TODO
		return null;
	}

	@Override
	public void paintComponents() {
		// TODO Auto-generated method stub
		
	}
}

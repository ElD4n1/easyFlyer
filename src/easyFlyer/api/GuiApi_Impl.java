package easyFlyer.api;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	private FlyerComponent selectedComponent;
	
	public GuiApi_Impl(){
		this.newFile("", "", "new_flyer.esf", "", 800, 600, 1);
	}
	
	@Override
	public void newFile(String author, String name, String filename , String description , int height, int width, int border) {
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
	public void exportFolder(File file) { //TODO
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
	public void addPicture(BufferedImage image, int x, int y) {
		try {
			this.flyer.addComponent(new ImageComponent(image, x, y));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void chooseComponent(Point pt) {
		ArrayList<FlyerComponent> components = flyer.getComponents();
		for(FlyerComponent component : components){
			if(component.getBounds().contains(pt)){
				this.selectedComponent = component;
		
				return;
			}
		}
		
		selectedComponent = null;
	}

	@Override
	public void addText(String text, int x, int y , int width , int height) {
		TextComponent temp = new TextComponent(text , x , y);
		temp.getBounds().setSize(width , height);
		
		this.flyer.addComponent(temp);
	}

	@Override
	public void removeComponent(MouseEvent event) {
		ArrayList<FlyerComponent> components = flyer.getComponents();
		for(FlyerComponent component : components){
			if(component.getBounds().contains(event.getPoint())){
				this.flyer.removeComponent(component);
			}
		}
	}

	@Override
	public FlyerComponent getSelected() {
		return this.selectedComponent;
	}

	@Override
	public void paintComponents(Graphics g) {
		this.flyer.paint(g);
	}
	
	public void moveComponent(Point pt)
	{
		if(selectedComponent == null)
		{
			return;
		}
		
		selectedComponent.getBounds().setLocation(pt);
	}
}

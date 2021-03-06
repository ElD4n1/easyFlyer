/**
 * 
 */
package easyFlyer.model;

import java.awt.Graphics;
import java.util.ArrayList;

import easyFlyer.dataTypes.Format;

/**
 * @author Daniel
 *
 */
public class Flyer {

	// Hintergrund
	private String name; // Eigener NAme oder Formatname?? TODO
	private Format format;
	private int border;
	
	private ArrayList<FlyerComponent> components;
	
	public Flyer(String name, int width, int height, int border){
		this.format = new Format(name, width, height);
		this.border = border;
		
		this.components = new ArrayList<FlyerComponent>();
	}

	public void paint(Graphics g) {
		for(FlyerComponent component : components){
			component.paintComponent(g);
		}
	}
	
	public String getName(){
		return format.getName();
	}
	
	public int getWidth(){
		return format.getWidth();
	}
	
	public int getHeight(){
		return format.getHeight();
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public int getBorder() {
		return border;
	}

	public void setBorder(int border) {
		this.border = border;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWidth(int width) {
		this.format.setWidth(width);
	}

	public void setHeight(int height) {
		this.format.setHeight(height);
	}
	
	public void setComponents(ArrayList<FlyerComponent> components) {
		this.components = components;
	}

	public void addComponent(FlyerComponent component){
		this.components.add(component);
	}
	
	public void removeComponent(FlyerComponent component){
		this.components.remove(component);
	}
	
	public ArrayList<FlyerComponent> getComponents(){
		return this.components;
	}
}

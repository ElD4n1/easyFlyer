package dataTyps;

import java.awt.Color;

public class ListColor {

	
	private final String name;
	private final Color color;
	
	public ListColor(String name, Color color){
		this.name= name;
		this.color= color;
	}

	public Color getColor(){
		return color;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

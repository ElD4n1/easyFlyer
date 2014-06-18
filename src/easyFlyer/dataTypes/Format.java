package easyFlyer.dataTypes;

public class Format {

	
	
	private final String name;
	private int width;
	private int height;
	
	public Format(String name, int width, int height){
		this.name=name;
		this.width=width;
		this.height = height;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	@Override
	public String toString(){
		return name;
	}


	public String getName() {
		return name;
	}
}

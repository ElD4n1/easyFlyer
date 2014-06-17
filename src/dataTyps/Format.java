package dataTyps;

public class Format {

	
	
	private final String name;
	private final int width;
	private final int height;
	
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
	
	@Override
	public String toString(){
		return name;
	}
}

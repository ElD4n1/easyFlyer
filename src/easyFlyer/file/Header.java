package easyFlyer.file;

import java.io.Serializable;
import java.util.Date;

public class Header 
	implements Serializable
{	
	private static final long serialVersionUID = -2907161256696379055L;

	public String author;
	
	public Date creationDate;
	
	public String description;
	
	public Header(String author, String description){
		this.author = author;
		this.description = description;
		
		creationDate = new Date();
	}
	
	public void setCreationDate(Date date){
		creationDate = date;
	}
}
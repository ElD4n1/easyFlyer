
package easyFlyer.api;

import java.awt.TextComponent;
import java.awt.event.MouseEvent;
import java.io.File;

import easyFlyer.model.FlyerComponent;
import easyFlyer.model.ImageComponent;

/**
 * @author Daniel
 *
 */
public interface GuiApi {
	
	/**
	 * Generates a new Datamodel with the parameters and saves as new Model.
	 * 
	 * @param name
	 * 		name of the flyer
	 * @param height
	 * 		height of the flyer
	 * @param width
	 * 		width of the flyer
	 * @param border
	 * 		size of the border around the flyer
	 * @return
	 */
	public void newFile(String name, int height, int width, int border);
	
	/**
	 * Loads a file and saves the flyer as model.
	 *  
	 * @param file
	 * 		the file which is loaded
	 */
	public void loadFile(File file);
	
	/**
	 * Saves file.
	 * @param file
	 * 		file where the flyer is saved
	 */
	public void saveFile(File file);
	
	/**
	 * Retruns the path where the flyer is saved
	 * @return the Filepath
	 * 		null if the file has never been saved
	 */
	public File getFilePath ();
	
	/**
	 * Generates a PDF file an saves it in the file
	 * @param file 
	 * 		file where the flyer is saved
	 */
	public void exportFile(File file);
	
	/**
	 * Generates an ZipFolder with all the pictures and the flyer
	 * @param file	
	 * 		file where the zip is saved
	 */
	public void exportFolder(File file);
	
	/**
	 * Changes the datamodel of the flyer with the parameters and saves it as model.
	 * 
	 * @param name
	 * 		name of the flyer
	 * @param height
	 * 		height of the flyer
	 * @param width
	 * 		width of the flyer
	 * @param border
	 * 		size of the border around the flyer
	 * @return
	 */
	public void changeDoumentSettings(String name, int height, int width, int border);
	
	/**
	 * Adds the image to the model
	 * @param image
	 * 		image to add
	 */
	public void addPicture(ImageComponent image);
	
	/**
	 * Selcets a {@link FlyerComponent} in the panel and saves the selected
	 * @param me
	 * 		the {@link MouseEvent} 
	 */
	public void chooseComponent(MouseEvent me);
	
	/**
	 * Selcets a {@link FlyerComponent} in the panel and saves the selected
	 * @param me
	 * 		the {@link MouseEvent} 
	 */
	
	public void addText(TextComponent text);
	
	/**
	 * Returns selected {@link FlyerComponent}
	 * @return selected component
	 */
	public FlyerComponent getSelected();
}
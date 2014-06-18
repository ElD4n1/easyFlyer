package tools;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.ImageIcon;

import easyFlyer.api.GuiApi;
import easyFlyer.model.FlyerComponent;


/**
 * Tool implementation for handling selections. Either the tool selects objects,
 * moves selected objects, or resizes a single selected object.
 * 
 * @author hp
 * @version 1.1
 * @since 1.0
 */
@SuppressWarnings("serial")
public class SelTool extends Tool {

//	/** Enumeration for encoding the different selection states of this tool */
//	private enum State {
//		IDLE, MOVING, SELECTING
//	}

	
	
	/** The Data model */
	private GuiApi model;




	/**
	 * Constructor initializing tool palette and model
	 * 
	 * @param palette
	 *            the tool palette
	 * @param model
	 *            the shape model
	 */
	public SelTool(ToolPalette palette, GuiApi model) {  
		super("Auswahl", new ImageIcon("icons/sel.png"), palette);
		this.model = model;
		putValue(Action.SHORT_DESCRIPTION, "Selection tool");
	}

	/**
	
	 * @param g
	 *            the graphics context
	 */
	@Override
	public void paintToolFeedBack(Graphics g) {
		// TODO paint fedback ( selcetion )
	}

	// selection

	/**
	 * chooses a {@link FlyerComponent}
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		model.chooseComponent(me);
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO insert action
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO insert action
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO insert action
	}

}

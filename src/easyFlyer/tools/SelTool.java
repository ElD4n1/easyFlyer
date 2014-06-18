package easyFlyer.tools;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.ImageIcon;


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

	/** Enumeration for encoding the different selection states of this tool */
	private enum State {
		IDLE, MOVING, RESIZING, SELECTING
	}

	/**
	 * A constant used for testing for selections and painting selections. Same
	 * as {@link DrawPanel#SEL_WIDTH}
	 */
	private static final int SEL_WIDTH =10;
	
	/** The Data model */
	

	/** The current state of this tool */
	private State state = State.IDLE;

	/** Mouse position representing the start of a dragging operation */
	private Point startPoint;

	/** Mouse position representing the end position of a selection action */
	private Point selectionPoint;


	/**
	 * Constructor initializing tool palette and shape model
	 * 
	 * @param palette
	 *            the tool palette
	 * @param model
	 *            the shape model
	 */
	public SelTool(ToolPalette palette) {  //TODO Model in Konstruktor einfügen
		super("Auswahl", new ImageIcon("icons/sel.png"), palette);
		// TODO Model speichern
		putValue(Action.SHORT_DESCRIPTION, "Selection tool");
	}

	/**
	 * Paints the feedback for the current gesture. Called by
	 * {@link DrawPanel#paintComponent(Graphics)}. Feedback is dependent of the
	 * state of this tool.
	 * 
	 * @param g
	 *            the graphics context
	 */
	@Override
	public void paintToolFeedBack(Graphics g) {
		// TODO paint fedback ( selcetion )
	}

	// selection

	/**
	 * Handles mouse clicked events (forwarded from drawing panel) by finding a
	 * selected shape and making it the single selected shape or, if shift down
	 * is pressed, changes the selection of this shape (select when not selected,
	 * deselect when is selected).
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO select objects
	}

	// move, resize, select

	/**
	 * Handles mouse pressed events (forwarded from drawing panel). Finds out
	 * the selected element. Initiates a resizing, moving or selection process
	 * dependent on the selection.
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mousePressed(MouseEvent me) {
		// TODO insert action
	}

	/**
	 * Handles mouse dragged events (forwarded from drawing panel). Dependent of
	 * the state of this tool will either show resizing, moving, or selecting
	 * shapes. Does not actually execute the operation but will show a visual
	 * feedback.
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO insert action
	}

	/**
	 * Handles mouse released events (forwarded from drawing panel). Dependent
	 * of the state of this tool will either resize a selected shape, move
	 * selected shape, or change the selections of shapes.
	 * 
	 * @param me
	 *            the mouse event object
	 */
	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO insert action
	}

}

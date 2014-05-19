/**
 * 
 */
package easyFlyer.components;

/**
 * @author Daniel
 *
 */
public abstract class EffectDecorator extends ComponentDecorator {

	// Mögl. von EffectDecorator ableiten und versch. Effekte machen
	/**
	 * @param component
	 */
	public EffectDecorator(Component component) {
		super(component);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see easyFlyer.components.Component#draw()
	 */
	public void draw() {
		// TODO Auto-generated method stub

	}
	
	protected abstract void applyEffect();
}

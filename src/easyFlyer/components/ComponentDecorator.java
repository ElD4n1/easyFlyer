
package easyFlyer.components;

/**
 * @author Daniel
 *
 */
public abstract class ComponentDecorator implements Component {

	private Component component;
	
	public ComponentDecorator(Component component) {
		this.component = component;
	}
}

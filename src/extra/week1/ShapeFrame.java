package extra.week1;

import javax.swing.*;
import java.awt.event.*;

/** A window for displaying a polygon. 
 * @author Roger Duke (author of Java Genesis)
 * @author Graeme Smith
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ShapeFrame extends JFrame {
		
	// panel for displaying the polygon
	private ShapePanel windowPane; 

	/** Create a new ShapeFrame. */	
	public ShapeFrame() {
		setTitle("Shape");
		setBounds(300, 150, 400, 300);
		windowPane = new ShapePanel();
		setContentPane(windowPane);
		// add listener to respond to the closing of the window
		addWindowListener(new WindowAdapter ( ) {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	/** Draw a hollow polygon in this ShapeFrame. 
	 * @param vertices The vertices of the polygon. 
	 */
	public void draw(PlanarPoint [] vertices) {
		windowPane.draw(vertices);
	}
  
	/** Draw a solid polygon in this ShapeFrame. 
	 * @param vertices The vertices of the polygon.
	 */
	public void fill(PlanarPoint [] vertices) {
		windowPane.fill(vertices);
	}
}
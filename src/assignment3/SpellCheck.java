package assignment3;

import assignment2.SpellChecker;
import java.awt.*;

/** A class for running a GUI-based spell checker.
 */
public class SpellCheck {

	public static void main(String [] args) {
		SpellChecker sc = new SpellChecker();
		SpellCheckerView view = new SpellCheckerView(sc);
		new SpellCheckerController(sc,view);
		view.setBackground(Color.blue);
		view.setVisible(true);
		view.setBounds(0, 0, 800, 600);
	}
	
}

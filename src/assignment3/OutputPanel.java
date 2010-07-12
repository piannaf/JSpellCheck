package assignment3;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;

@SuppressWarnings("serial")
/** A panel for displaying output (including error messages) to the user. It 
 * includes: 
 * 	a text area for displaying output and errors using appropriate colors
 */
public class OutputPanel extends JPanel {

	private JTextArea errorTextArea; 	// text area for spelling errors
	private JTextArea messageTextArea;	// text area for messages
	
	/** Creates a new OutputPanel with an empty text area.
	 */
    public OutputPanel() {
    	errorTextArea = new JTextArea(10, 30);
    	errorTextArea.setEditable(false);
    	this.add(errorTextArea);

    	messageTextArea = new JTextArea(10, 30);
    	messageTextArea.setEditable(false);
    	this.add(messageTextArea);
    }
    
    public void setErrorText(String s) {
    	errorTextArea.setText(s);
    	errorTextArea.setForeground(Color.black);
    }
    
    public void setMessageText(String s, int type) {
    	messageTextArea.setText(s);
    	
    	Color color;
    	switch (type) {
    		case 0:
    			color = Color.blue; break;
    		case -1:
    			color = Color.red; break;
    		default:
    			color = Color.black;
    	}
    	messageTextArea.setForeground(color);
    }
}

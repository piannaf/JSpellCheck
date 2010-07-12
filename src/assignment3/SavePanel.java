package assignment3;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
/** A panel for displaying the GUI components associated with saving a
 *  corrected document. It includes:
 * 	 a labeled text field for the user to enter the name of the file in to
 * 		which the document is to be saved
 * 	 a button for saving the document
 */
public class SavePanel extends JPanel {

	private JTextField newDocumentTextField;	// text field for new document
	private JButton saveButton;					// the save button
	private JLabel saveLabel;					// label for the text field
	
	/** Creates a new OutputPanel with an empty text area.
	 */
    public SavePanel() {
    	this.add(initSaveLabel());
    	this.add(initNewDocumentTextField());
    	this.add(initSaveButton());
    }
    
    private JTextField initNewDocumentTextField() {
    	newDocumentTextField = new JTextField("", 12);
    	return newDocumentTextField;
    }
    
    private JButton initSaveButton() {
    	saveButton = new JButton("Save");
    	return saveButton;
    }
    
    private JLabel initSaveLabel() {
    	saveLabel = new JLabel("Save as:", JLabel.RIGHT);
    	return saveLabel;
    }
    
    public String getNewDocumentText() {
    	return newDocumentTextField.getText();
    }
    
    /** Associates a given action listener with the save button. 
     */
    public void addSaveButtonListener(ActionListener sb) {
    	saveButton.addActionListener(sb);
	}
}

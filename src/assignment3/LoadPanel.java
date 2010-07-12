package assignment3;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
/** A panel for displaying the GUI components associated with loading a
 *  dictionary and document. It includes:
 * 	 a labeled text field for the user to enter the name of the dictionary file
 * 	 a labeled text field for the user to enter the name of the document file
 * 	 a button for loading the dictionary and document
 */
public class LoadPanel extends JPanel {

	private JTextField dictionaryTextField; 	// text field for dictionary
	private JTextField documentTextField; 		// text field for document
	private JButton loadButton;					// the load button
	private JLabel dictionaryLabel; 	// label for dictionary text field
	private JLabel documentLabel; 		// label for document text field
	
	/** Creates a new LoadPanel with empty text fields.
	 */
    public LoadPanel() {
    	dictionaryLabel = new JLabel("Dictionary:", JLabel.RIGHT);
		add(dictionaryLabel);
		dictionaryTextField = new JTextField("",12);
		add(dictionaryTextField);
		documentLabel = new JLabel("Document:", JLabel.RIGHT);
		add(documentLabel);
		documentTextField = new JTextField("",12);
		add(documentTextField);
		loadButton = new JButton("Load"); 
		add(loadButton);
	}	
    
    /** Returns the file name entered in the dictionary text field. 
     */
    public String getDictionaryName() {
    	return dictionaryTextField.getText();
    }
    
    /** Returns the file name entered in the document text field. 
     */
    public String getDocumentName() {
    	return documentTextField.getText();
    }
    
    /** Associates a given action listener with the load button. 
     */
    public void addLoadButtonListener(ActionListener lb) {
		loadButton.addActionListener(lb);
	}
    
}
package assignment3;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.util.*;

@SuppressWarnings("serial")
/** A panel for displaying the GUI components associated with correcting errors
 *  in a document. It includes:
 * 	 a combo box for displaying corrections from the dictionary 
 * 	 a labeled text field for the user to enter a correction (either by typing
 * 	 	it in or by selecting it from the combo box)
 *   a button for correcting the error using the text in the text field
 *   a button for ignoring the error (i.e., leaving it in the document and 
 *   	moving on to the next error
 */
public class CorrectionPanel extends JPanel {
	
	private JComboBox correctionComboBox;		// the candidate combobox
	private JTextField correctionTextField; 	// text field for correction
	private JButton correctButton;				// the correct button
	private JButton ignoreButton;				// the ignore button
	private JLabel correctionLabel; 	// label for correction text field
	
	/** Creates a new OutputPanel with an empty text area.
	 */
    public CorrectionPanel() {
    	this.add(initCorrectionLabel());
    	this.add(initCorrectionComboBox());
    	this.add(initCorrectionTextField());
    	this.add(initCorrectButton());
    	this.add(initIgnoreButton());
    }
    
    private JComboBox initCorrectionComboBox() {
    	correctionComboBox = new JComboBox();
    	return correctionComboBox;
    }
    
    private JTextField initCorrectionTextField() {
    	correctionTextField = new JTextField("", 12);
    	correctionTextField.setEnabled(false);
    	return correctionTextField;
    }
    
    private JButton initCorrectButton() {
    	correctButton = new JButton("Correct");
    	return correctButton;
    }
    
    private JButton initIgnoreButton() {
    	ignoreButton = new JButton("Ignore");
    	return ignoreButton;
    }
    
    private JLabel initCorrectionLabel() {
    	correctionLabel = new JLabel("Correction:", JLabel.RIGHT);
    	return correctionLabel;
    }
    
    public String getCorrectionText() {
    	return correctionTextField.getText();
    }
    
    public void setCorrectionText(String s) {
    	correctionTextField.setText(s);
    }
    
    public void setCorrectionTextEnable(Boolean enabled) {
    	correctionTextField.setEnabled(enabled);
    }
    
    public String getCorrectionComboBoxSelection() {
    	return (String)correctionComboBox.getSelectedItem();
    }
    
    public void setCorrectionComboBoxSelections(ArrayList<String> corrections) {
    	correctionComboBox.removeAllItems();
    	
    	for(String s : corrections) {
    		correctionComboBox.addItem(s);
    	}
    }
    
    /** Associates a given action listener with the correct button. 
     */
    public void addCorrectButtonListener(ActionListener cb) {
    	correctButton.addActionListener(cb);
	}
    
    /** Associates a given action listener with the ignore button. 
     */
    public void addIgnoreButtonListener(ActionListener ib) {
		ignoreButton.addActionListener(ib);
	}
    
    public void addCorrectionComboBoxListener(ActionListener ccb) {
    	correctionComboBox.addActionListener(ccb);
    }
}

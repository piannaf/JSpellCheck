package assignment3;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import assignment2.SpellChecker;

@SuppressWarnings("serial")
/** A frame for displaying the GUI components for a spell checker. It includes:
 *   a labeled text field for the user to enter the name of the dictionary file
 * 	 a labeled text field for the user to enter the name of the document file
 * 	 a button for loading the dictionary and document
 * 	 a text area for displaying output and errors using appropriate colors
 * 	 a combo box for displaying corrections from the dictionary 
 * 	 a labeled text field for the user to enter a correction (either by typing
 * 	 	it in or by selecting it from the combo box)
 *   a button for correcting the error using the text in the text field
 *   a button for ignoring the error (i.e., leaving it in the document and 
 *   	moving on to the next error
 *   a labeled text field for the user to enter the name of the file in to
 * 		which the document is to be saved
 * 	 a button for saving the document
 * 
 *  The class is intended as the View component of a Model-View-Controller 
 *  architecture. 
 */
public class SpellCheckerView extends JFrame {
	
	private SpellChecker sc;	// the spell checker
	// the panel for loading dictionary and documents
	private LoadPanel loadPanel;	
	// the panel for displaying results and user errors
	private OutputPanel outputPanel;
	// the panel for correcting errors in the document
	private CorrectionPanel correctionPanel;
	// the panel for saving a corrected document
	private SavePanel savePanel;
	
	public SpellCheckerView(SpellChecker sc) {	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(4,1));
		
		this.sc = sc;
		
		loadPanel = new LoadPanel();
		outputPanel = new OutputPanel();
		correctionPanel = new CorrectionPanel();
		savePanel = new SavePanel();
		
		content.add(loadPanel);
		content.add(outputPanel);
		content.add(correctionPanel);
		content.add(savePanel);
		c.add(content);
	}
	
    public LoadPanel getLoadPanel() {
    	return loadPanel;
    }
    
    public OutputPanel getOutputPanel() {
    	return outputPanel;
    }
    
    public CorrectionPanel getCorrectionPanel() {
    	return correctionPanel;
    }
    
    public SavePanel getSavePanel() {
    	return savePanel;
    }
}

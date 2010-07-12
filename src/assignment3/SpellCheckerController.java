package assignment3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assignment2.SpellChecker;

import java.io.*;
import assignment2.NoDictionaryException;
import assignment2.NoDocumentException;
import assignment1.*;
import java.util.*;

/** A class for responding to user input from a GUI for a spell checker. In 
 *  particular it responds to: 
 * 	 a button for loading the dictionary and document
 * 	 a combo box for displaying corrections from the dictionary 
 *   a button for correcting the error using the text in the text field
 *   a button for ignoring the error (i.e., leaving it in the document and 
 *   	moving on to the next error
 * 	 a button for saving the document
 * 
 *  The class is intended as the Controller component of a Model-View-Controller 
 *  architecture.
 */
public class SpellCheckerController {

	private SpellChecker sc;			// the spell checker
	private SpellCheckerView view;		// the view
	private Boolean hasError;	// Are there any misspelled words?
	
	public SpellCheckerController(SpellChecker model, SpellCheckerView view) {
		this.sc = model;
		this.view = view;
		this.hasError = false; 	// Assume no errors to begin with
		
		view.getLoadPanel().addLoadButtonListener(new LoadActionListener());
		view.getCorrectionPanel().addCorrectButtonListener(
				new CorrectActionListener());
		view.getCorrectionPanel().addIgnoreButtonListener(
				new IgnoreActionListener());
		view.getCorrectionPanel().addCorrectionComboBoxListener(
				new CorrectionComboBoxActionListener());
		view.getSavePanel().addSaveButtonListener(new SaveActionListener());
	}
	
	private void populateOutputs(OutputPanel out) {
		
		CorrectionPanel cp = view.getCorrectionPanel();
		
		try {
			out.setErrorText(sc.getLine() + "\nError: " + sc.getError());
			out.setMessageText("", 0);
			cp.setCorrectionTextEnable(true);
			cp.setCorrectionComboBoxSelections(sc.getCorrections());
			hasError = true;			
		} catch (NoSuchLineException ex) { //Also catches NoSuchError
			out.setMessageText("Spell check complete.", 0);
			out.setErrorText("");
			cp.setCorrectionText("");
			cp.setCorrectionTextEnable(false);
			cp.setCorrectionComboBoxSelections(new ArrayList<String>());
			hasError = false;
		}
	}
	
	private class LoadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			OutputPanel out = view.getOutputPanel();
			
			try {
				sc.loadDictionary(view.getLoadPanel().getDictionaryName());
			} catch (IOException ex) {
				out.setMessageText("Could not open dictionary file.", -1);
				return;
			}
			
			try {
				sc.loadDocument(view.getLoadPanel().getDocumentName());
				populateOutputs(out);								
			} catch (IOException ex) {
				out.setMessageText("Could not open document file.", -1);
			} catch (NoDictionaryException ex) {
				out.setMessageText("You must specify a dictionary.", -1);
			}
		}
	}	
	
	private class CorrectActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			CorrectionPanel cp = view.getCorrectionPanel();
			if(!hasError) {
				return;
			}

			sc.correctError(cp.getCorrectionText());
			populateOutputs(view.getOutputPanel());
		}
	}	
	
	private class IgnoreActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!hasError) {
				return;
			}
			
			sc.correctError(sc.getError());
			populateOutputs(view.getOutputPanel());
		}
	}
	
	private class CorrectionComboBoxActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.getCorrectionPanel().setCorrectionText(
					view.getCorrectionPanel().getCorrectionComboBoxSelection());
		}
	}
	
	private class SaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				sc.writeDocument(view.getSavePanel().getNewDocumentText());
			} catch (IOException ex) {
				view.getOutputPanel().setMessageText(
						"Could not save document.", -1);
			} catch (NoDocumentException ex) {
				view.getOutputPanel().setMessageText("You must load a " +
						"document before attempting to save.", -1);
			}
		}
	}
}

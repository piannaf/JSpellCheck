package assignment2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import assignment1.*;
import assignment1.Error;
import java.util.ArrayList;
import java.util.Iterator;

/** A SpellChecker allows a dictionary to be loaded from words in a text file
 * and then a document (also a text file) to be loaded and checked for spelling 
 * errors. A spelling error is any word in the loaded document that is not in 
 * the dictionary. Such errors may be corrected in the loaded document which 
 * may then be written back to a file. 
 */
public class SpellChecker {
	/*
	 * Invariants: if dictionary == null then document == null &&
	 * 				if document != null then dictionary != null
	 */
	Dictionary dictionary; //holds the loaded dictionary
	Document document;	//holds the loaded document
		
	/** Loads the words in a given text file into this SpellChecker's 
	 * dictionary.
	 * @require file is not null 
	 * @ensure space-separated strings in file with leading and trailing 		
	 * 		   non-letters removed are added to this SpellChecker's dictionary	
	 * @throws IOException, if file is not an accessible text file
	 */
	public void loadDictionary(String file) throws IOException {
		FileReader fr; // opens the file for reading
		try {
			fr = new FileReader(file);
		} catch (IOException err) { throw new IOException(); }
		
		dictionary = new Dictionary();
		loadWords(fr, dictionary);
		fr.close();
	}
		
	/** Loads a given text file as this SpellChecker's document recording any 
	 * spelling errors, i.e., words that are not in the dictionary.
	 * @require file is not null
	 * @ensure the lines of files are loaded into this SpellChecker's document
	 * 		   along with their associated errors
	 * @throws NoDictionaryException, if a dictionary has not been loaded
	 * @throws IOException, if file is not an accessible text file
	 */
	public void loadDocument(String file) throws IOException {
		if(dictionary == null) {
			throw new NoDictionaryException();
		}
		
		FileReader fr; // Opens the file for reading
		try {
			fr = new FileReader(file);
		} catch (IOException err) { throw new IOException(); }
		
		document = new Document();
		loadLines(fr, document);
		fr.close();
	}
		
	/** Returns the text of the first line in the loaded document 
	 * which has an error.
	 * @ensure result is the text of the first line with an error
	 * @throws NoDocumentException, if a document has not been loaded
	 * @throws NoSuchLineException, if there are no lines with errors
	 */
	public String getLine() {
		if(document == null) {
			throw new NoDocumentException();
		}
		
		/* 
		 * line holds the first line in the loaded document which contains an 
		 * error.
		 */
		Line line = getFirstLineWithError();
		if(line == null) {
			throw new NoSuchLineException();
		}
		return line.getText();
	}
		
	/** Returns the first misspelled word in the loaded document.
	 * @ensure result is the first misspelled word
	 * @throws NoDocumentException, if a document has not been loaded
	 * @throws NoSuchErrorException, if there are no errors
	 */
	public String getError() {	
		if(document == null) {
			throw new NoDocumentException();
		}
		
		/*
		 * line holds the first line in the loaded document which contains an 
		 * error.
		 */
		Line line = getFirstLineWithError();
		if(line == null) {
			throw new NoSuchErrorException();
		}
		return line.getErrors().get(0).getWord();
	}
	
	/** Returns a list of corrections for the first misspelled word in the 
	 * loaded document.
	 * @ensure result is the first misspelled word
	 * @throws NoDocumentException, if a document has not been loaded
	 * @throws NoSuchErrorException, if there are no errors
	 */
	public ArrayList<String> getCorrections() {		
		if(document == null) {
			throw new NoDocumentException();
		}

		/*
		 * line holds the first line in the loaded document which contains an 
		 * error.
		 */
		Line line = getFirstLineWithError();
		if(line == null) {
			throw new NoSuchErrorException();
		}
		return line.getErrors().get(0).getCandidates();
	}
	
	/** Returns the text of the first line with an error in the loaded
	 * document with the first misspelled word replaced by correction. The 
	 * error is removed from the loaded document.
	 * @require correction is not null
	 * @ensure result is the text of the first line with an error with its 
	 * 		   first misspelled word replaced by correction, and the first 
	 * 		   error is removed from the loaded document
	 * @throws NoDocumentException, if a document has not been loaded
	 * @throws NoSuchErrorException, if there are no errors in the loaded 
	 * 		   document
	 */
	public String correctError(String correction) {	
		if(document == null) {
			throw new NoDocumentException();
		}

		/*
		 * line holds the first line in the loaded document which contains an 
		 * error.
		 */
		Line lineWithError = getFirstLineWithError();
		if(lineWithError == null) {
			throw new NoSuchErrorException();
		}

		/*
		 * lineCorrected holds the corrected version of lineWithError.
		 */
		Line lineCorrected = lineWithError.correctError(0, correction);
		document.correctError(getLineIndex(lineWithError), 0, correction);
		return lineCorrected.getText();
	}
	
	/** Writes the content of the loaded document to a given text file. 
	 * @require file is not null
	 * @ensure file contains the lines of the loaded document
	 * @throws NoDocumentException, if a document has not been loaded
	 * @throws IOException, if file is not an allowable text file
	 */
	public void writeDocument(String file) throws IOException {
		if(document == null) {
			throw new NoDocumentException();
		}
		
		FileWriter fw; //Opens the file for writing
		try {
			fw = new FileWriter(file, false);
		} catch (IOException err) { throw new IOException(); }
		
		BufferedWriter bw = new BufferedWriter(fw); //optimize writing
		/*
		 * docIterator holds the document's iterator so that we may loop
		 * over each line in the document.
		 */
		Iterator<Line> docIterator = document.iterator();
		while(docIterator.hasNext()) {
			bw.write(docIterator.next().getText());
			bw.newLine();
		}
		bw.close();
	}
	
	/** Returns true when the class invariant holds, and false otherwise.
	 * @ensure result is true when the class invariant holds, 
	 * 		   and false otherwise
	 */
	public boolean invCheck() {
		/*
		 * The two invariants above are written for clarity but they are 
		 * logically equivalent so only one check is necessary.
		 */
		if(dictionary == null && document != null) return false;
		return true;
	}
	
	public String toString() {
		/* s is the string representation of the SpellChecker */
		String s = "Current State of SpellChecker:";
		s += "\n\tDictionary: ";
		if(dictionary == null) {
			s += "Not Loaded";
		} else {
			s += "\n\t\t" + dictionary.toString().replace("\n","\n\t\t");
		}
		
		s += "\n\tDocument: ";
		if(document == null) {
			s += "Not Loaded"; 
		} else {
			s += "\n\t\t" + document.toString().replace("\n","\n\t\t");
		}
		
		return s;
		
	}

	/** Returns the a word as defined by the specification (no leading or 
	 * trailing non-letters)
	 * @require	word is not null
	 * @ensure 	result is the word with leading and trailing non-letters
	 * 			removed.
	 */
	private String stripToSpec(String word) {
		/*
		 * The regular expression below translates into English as:
		 * 		all non-alpha characters occurring at the beginning of the line
		 * 		or all non-alpha character occurring at the end of the line.
		 * 
		 * See regex documentation for details.
		 */
		return word.replaceAll("^[^\\p{Alpha}]*|" +
				"[^\\p{Alpha}]*$", "");
	}
	
	/** Loads words from a text file into a dictionary.
	 * @require	fr is not null, dictionary is not null
	 * @ensure 	Space-separated strings in file with leading and trailing 		
	 * 		   	non-letters removed are added to this SpellChecker's 
	 * 			dictionary.
	 */
	private void loadWords(FileReader fr, Dictionary dictionary) 
			throws IOException {
		BufferedReader br = new BufferedReader(fr); //optimize reading
		
		/* line holds the text of the first line of the text file*/
		String line = br.readLine();
		while(line != null) {
			/* loop invariant: 	dictionary contains non-duplicated words from 
			 * the current line of the text file with leading and trailing 
			 * non-letters removed.
			 */
			
			/* words is the list of space-separated strings in the line */
			String[] words = line.split("\\s"); //"\s" is regex for whitespace
			for(String word : words) {
				/* loop invariant: 	dictionary contains non-duplicated words 
				 * from the current line of the text file with leading and 
				 * trailing non-letters removed which have been iterated over
				 * so far.
				 */
				
				/* 
				 * word holds the old word with leading and trailing 
				 * non-letters removed
				 */
				word = stripToSpec(word);
				
				if(word.equals("")) { //Don't add the empty string
					continue;
				}

				dictionary.add(word);
			}
			
			/* Get the next line */
			line = br.readLine();
		}
		
		br.close();
	}
	
	/** Loads a given text file into a document recording any spelling errors, 
	 * 	i.e., words that are not in this SpellChecker's dictionary.
	 * @require fr is not null, document is not null
	 * @ensure 	The lines of files are loaded into the document along with 
	 * 			their associated errors.
	 */
	private void loadLines(FileReader fr, Document document) 
			throws IOException {
		BufferedReader br = new BufferedReader(fr); //Optimize reading

		/* line holds the text of the first line of the text file*/
		String line = br.readLine();
		
		/* errors holds the list of errors for each line in the document*/
		ArrayList<Error> errors;
		while(line != null) {
			/* loop invariant: 	errors contains all errors from the current 
			 * line of the document.
			 */
			
			/* Populate the list of errors */
			errors = generateErrorList(line);
			
			/* Add the line along with errors to the document */
			document.add(new Line(line, errors));
			
			/* Get the next line */
			line = br.readLine();
		}
		
		br.close();
	}
	
	/** Returns a list of errors for the given line.
	 * @require	line is not null
	 * @ensure 	result is a list of every error as determined from this
	 * 			SpellChecker's dictionary.
	 */
	private ArrayList<Error> generateErrorList(String line) {
		/*
		 * errors holds the list of errors for this line
		 * currentIndex records the position of the current word in the text
		 * error holds each error before being added to the list of errors.
		 */
		ArrayList<Error> errors = new ArrayList<Error>();
		int currentIndex = 0;
		Error error;
		
		/* words is the list of space-separated strings in the text */
		String[] words = line.split("\\s"); //"\s" is regex for whitespace
		for(String word : words) {
			/* loop invariant: 	errors holds the list of misspelled words
			 * which have been discovered thus far.
			 * 					currentIndex holds the position in the text at
			 * which the current word begins.
			 * 					error holds information about the misspelled
			 * word discovered this iteration.
			 */

			/* 
			 * specWord holds word with leading and trailing non-letters 
			 * removed
			 */
			String specWord = stripToSpec(word);
			if(specWord.equals("")) { //Disregard the empty string
				continue;
			}
			
			if(!dictionary.lookup(specWord)) {
				/* 
				 * currentIndex is the index of the word starting after the 
				 * previously found word.
				 */
				currentIndex = line.indexOf(specWord, currentIndex);
				
				/*
				 * Generate a new error to be added to the list
				 */
				error = new Error(currentIndex, specWord, 
						dictionary.getCorrections(specWord));
				errors.add(error);
				
				/*
				 * Advance the currentIndex to the start of the next word
				 */
				currentIndex += word.length();
			}
		}
		
		return errors;
	}
	
	/** Returns the first line of the loaded document which contains an error
	 * @ensure 	Result is the first line of the loaded document which contains
	 * 			an error. If the document contains no lines with errors, result
	 * 			is null.
	 */
	private Line getFirstLineWithError() {
		/*
		 * docIterator holds the document's iterator so that we may loop
		 * over each line in the document.
		 */
		Iterator<Line> docIterator = document.iterator();
		
		/* line holds the current line in the document */
		Line line;
		while(docIterator.hasNext()) {
			line = docIterator.next();
			if(!line.getErrors().equals(new ArrayList<String>())) {
				return line;
			}
		}
		return null;
	}
	
	/** Returns the index of the line of the loaded document corresponding to l
	 * @require	l is not null
	 * @ensure 	Result is the index of the line of the loaded document 
	 * corresponding to l. If the document contains no such line, return -1.
	 */
	private int getLineIndex(Line l) {
		/*
		 * docIterator holds the document's iterator so that we may loop
		 * over each line in the document.
		 */
		Iterator<Line> docIterator = document.iterator();
		
		/* line holds the current line in the document */
		Line line;
		for(int i = 0; docIterator.hasNext(); i++) {
			line = docIterator.next();
			if(line.equals(l)) {
				return i;
			}
		}
		return -1;
	}
}

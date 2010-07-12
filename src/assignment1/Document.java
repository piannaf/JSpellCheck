package assignment1;

import java.util.Iterator;
import java.util.ArrayList;

/** A Document is a mutable representation of a text file. It stores the text
 * file's contents as a sequence of lines indexed by the numbers from 0 to n, 
 * for some n. The number reflects the order in which the line was added to the 
 * Document, i.e., the first line to be added will be indexed by 0, the second 
 * line by 1, and so on. Each line is represented by its text along with a 
 * (possibly empty) list of misspelled words and their possible corrections. 
 * 
 * @author Justin Mancinelli [42094353]
 */
public class Document implements Iterable<Line> {
	/* invariant:
	 * 		lines != null
	 * 		each element of lines != null
	 */
	private ArrayList<Line> lines; //The list of all lines in the document.
	
	/** Creates a new Document with no lines.
	 */
	public Document() {
		lines = new ArrayList<Line>();
	}
	
	/** Adds a given line to this Document
	 * @param line  the line
	 * @throws NullPointerException, if line is null
	 */
	 public void add(Line line) throws NullPointerException {
		 if(line == null) {
			 throw new NullPointerException();
		 }
		 lines.add(line);
	}
	
	/** Returns an iterator for this Document. The iterator returns each 
	 * line of the document in the order that it was added to the Document.
	 * @return an iterator for the lines in this Document
	 */
	public Iterator<Line> iterator() {
		return lines.iterator();
	}
	
	/** Corrects a given error on a given line in the Document.
	 * @param index  the index of the line
	 * @param pos  the position of the error in the list of errors in the line
	 * @param correction  the correction
	 * @throws NullPointerException, if correction is null
	 * @throws NoSuchLineException, if index does not index a line in this
	 * 		   Document
	 * @throws NoSuchErrorException, if pos is not a position in the list of
	 * 		   errors in the line indexed by index
	 */
	public void correctError(int index, int pos, String correction) 
		throws NullPointerException, NoSuchLineException, NoSuchErrorException {
		if(correction == null) {
			throw new NullPointerException();
		}
		if(index < 0 || index >= lines.size()) {
			throw new NoSuchLineException();
		}
		if(pos < 0 || pos >= lines.get(index).getErrors().size()) {
			throw new NoSuchErrorException();
		}
		
		/* corrected: The corrected line. */
		Line corrected = lines.get(index).correctError(pos, correction);
		lines.remove(index);
		lines.add(index, corrected);
	}
	
	/** Returns the text of a given line in the Document.
	 * @param index  the index of the line
	 * @throws NoSuchLineException, if index does not index a line in this 
	 * 		   Document
	 * @return the text of the given line 
	 */
	public String getText(int index) throws NoSuchLineException {
		if(index < 0 || index >= lines.size()) { //index < 0 added after submission
			throw new NoSuchLineException();
		}
		
		return lines.get(index).getText();
	}
	
	/** Returns true when the class invariant holds, and false otherwise.
	 * @return  whether the class invariant holds
	 */
	public boolean checkInv() {
		if(lines == null) return false;
		if(lines.contains(null)) return false;
		return true;
	}
	
	public String toString() {
		String doc = ""; //Stores the generated string representation
		
		/* Loop through each line, printing the specified representation */
		for(int i = 0; i < lines.size(); i++) {
			if(i != 0) doc += "\n";
			doc += i + " " + lines.get(i).getText() + "\n" +
					"Errors: " + lines.get(i).getErrors();
		}
		
		return doc;
	}
}

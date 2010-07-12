package assignment1;

import java.util.ArrayList;
import java.util.Arrays;

/** A Line is an immutable representation of a line of a document. It includes 
 * the text of the line, as well as a list of errors in the line and their 
 * possible corrections. The errors appear in the list in the same order as 
 * they occur in the line.
 * 
 * @author Justin Mancinelli [42094353]
 */
public class Line {
	/* invariant:
	 * 		text != null && 
	 * 		errors != null &&
	 * 		each element of errors != null &&
	 * 		each element of errors exists in text at the given position &&
	 * 		errors lists the errors in the order they appear in text
	 */
	private String text; //The text of the line
	private ArrayList<Error> errors; //The list of errors in the line

	/** Creates a new line with a given text and list of errors.
	 * @param text  the text of the line
	 * @param errors  the list of errors in the line
	 * @throws  NullPointerException, if text or errors is null
	 * @throws InvalidLineException, if any element of errors is null,
       *         or is not present in text at the given position, or if 
       *         errors does not list the errors in the order in which they 
	 *         appear in text 
	 */
	public Line(String text, ArrayList<Error> errors) 
		throws NullPointerException {
		if(text == null || errors == null) {
			throw new NullPointerException();
		}
		if(errors.contains(null)){
			throw new InvalidLineException();
		}
		
		/* starts stores the starting position of each error */
		ArrayList<Integer> starts = new ArrayList<Integer>();
		for(Error e : errors) {
			if(text.indexOf(e.getWord(), e.getStart()) != e.getStart()) {
				throw new InvalidLineException();
			}
			starts.add(e.getStart());
		}
		/* sortedStarts stores a copy of "starts" to be in sorted order */
		Integer[] sortedStarts = starts.toArray(new Integer[starts.size()]);
		Arrays.sort(sortedStarts);
		
		/* if sortedStarts is equal to starts then errors are listed in the 
		 * order in which they appear in the text.
		 */
		if(!Arrays.asList(sortedStarts).equals(starts)) {
			throw new InvalidLineException();
		}
		
		this.text = "" + text;
		this.errors = new ArrayList<Error>(errors);
		
	}
	
	/** Returns the text of this Line. 
	 * @return  the text
	 */
	public String getText() {	
		return "" + text;
	}
	
	/** Returns the list of errors in this Line. 
	 * @return  the list of errors
	 */
	public ArrayList<Error> getErrors() {
		return new ArrayList<Error>(errors);
	}
	
	/** Creates a new line from this line by correcting (and therefore 
	 * removing) one of its errors.
	 * @param pos  the position of the error in list of errors of this Line
	 * @param correction  the correction
	 * @throws NullPointerException, if corrections is null
	 * @throws NoSuchErrorException, if pos is not a position in the list of 
	 * 		   errors of this Line 
	 */ 
	public Line correctError(int pos, String correction) 
		throws NullPointerException, NoSuchErrorException {
		if(correction == null) {
			throw new NullPointerException();
		}
		if( pos < 0 || pos >= errors.size()) {
			throw new NoSuchErrorException();
		}
		
		/* Use StringBuilder class to generate a mutable representation of the
		 * text. This simplifies insertion and deletion of relevant words.
		 * 
		 * newText: The mutable representation of the text
		 * relevantError: The error to be corrected.
		 */
		StringBuilder newText = new StringBuilder(text);
		Error relevantError = errors.get(pos);
		newText.delete(relevantError.getStart(), relevantError.getEnd() + 1);
		newText.insert(relevantError.getStart(), correction);
		
		/* Populate the newErrors list such that if the correction alters the 
		 * position of other errors, they will be updated accordingly. After
		 * all errors have been updated, remove the corrected error from the
		 * list.
		 */
		ArrayList<Error> newErrors = new ArrayList<Error>();
		if(relevantError.getWord().length() != correction.length()) {
			/* Calculate the offset required to update errors after the
			 * correction.
			 */
			int offset = correction.length() - relevantError.getWord().length();
			for(int i = 0; i < errors.size(); i++) {
				Error e = errors.get(i);
				/* If the current error is after the correction, update it with
				 * the correct offset, else, offset not necessary.
				 */
				if(i>pos) {
					newErrors.add(new Error(e.getStart() + offset, e.getWord(),
							e.getCandidates()));
				} else {
					newErrors.add(new Error(e.getStart(), e.getWord(),
							e.getCandidates()));
				}
			}
		} else newErrors.addAll(errors);
		newErrors.remove(pos);
		
		return new Line(newText.toString(), newErrors);
	}
	
	/** Returns true when the class invariant holds, and false otherwise.
	 * @return  whether the class invariant holds
	 */
	public boolean checkInv() {
		if(text == null) return false;
		if(errors == null) return false;
		if(errors.contains(null)) return false;
		
		/* starts: A list containing the starting position of each error */
		ArrayList<Integer> starts = new ArrayList<Integer>();
		for(Error e : errors) {
			if(text.indexOf(e.getWord(), e.getStart()) != e.getStart()) {
				return false;
			}
			starts.add(e.getStart());
		}
		/* sortedStarts: Stores a copy of "starts" to be in sorted order */
		Integer[] sortedStarts = starts.toArray(new Integer[starts.size()]);
		Arrays.sort(sortedStarts);
		
		if(!Arrays.asList(sortedStarts).equals(starts)) {
			return false;
		}
		
		return true;
	}

	public String toString() {
		return getText() + "\nErrors: " + getErrors().toString();
	}

	public boolean equals(Object o) {
		if (! (o instanceof Line)) return false;
		Line n = (Line)o;
		return text.equals(n.text) && errors.equals(n.errors);
	}
	
	public int hashCode() {
		return text.hashCode() + errors.hashCode();
	}
}

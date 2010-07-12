package assignment1;

import java.util.ArrayList;

/** An Error is an immutable record of a misspelled word. It includes the 
 * misspelled word, its start and end positions in the line in which it 
 * occurs, and a list of possible (candidate) corrections. 
 * 
 * @author Justin Mancinelli [42094353]
 */
public class Error {
	/* invariant:
	 * 		word != null && word != "" &&
	 * 		candidates != null &&
	 * 		each element of candidates != null &&
	 * 		each element of candidates != "" &&
	 * 		no duplicates in candidates
	 */
	
	private int start; //The start position of the misspelled word
	private String word; //The misspelled word
	private ArrayList<String> candidates; //The possible corrections
	
	
	/** Creates a new Error.
	 * @param start  the start position of the misspelled word
	 * @param word  the misspelled word
	 * @param candidates  the possible corrections
	 * @throws NullPointerException, if word or candidates is null
	 * @throws InvalidErrorException, if word is the empty String, or 
	 *         if any element of candidates is null or the empty String, 
     *         or if there are duplicates in candidates
	 */
	public Error (int start, String word, ArrayList<String> candidates) 
		throws NullPointerException {	
		this.start = start;
		
		if(word == null || candidates == null) {
			throw new NullPointerException();
		}
		if(word == "" || candidates.contains(null) || candidates.contains("") ||
				checkDup(candidates)) {
			System.out.println("foobar: " + candidates);
			throw new InvalidErrorException();
		}
		this.word = "" + word;
		this.candidates = new ArrayList<String>(candidates);
	}
	
	/**Returns the start position of the error.
	 * @return  the start position of the error
	 */
	public int getStart() {
		return start;
	}
	
	/**Returns the end position of the error.
	 * @return  the end position of the error
	 */
	public int getEnd() {
		return start + word.length() - 1;
	}

	/** Returns the misspelled word.
	 * @return  the misspelled word
	 */
	public String getWord() {
		return word;
	}
	
	/** Returns the candidate corrections.
	 * @return  the candidate corrections
	 */
	public ArrayList<String> getCandidates() {
		return new ArrayList<String>(candidates);
	}
	
	/** Returns true when the class invariant holds, and false otherwise.
	 * @return  whether the class invariant holds
	 */
	public boolean checkInv() {
		if(word == null && word == "") return false;
		if(candidates == null) return false;
		if(candidates.contains(null) && candidates.contains("")) return false;
		if(checkDup(candidates)) return false;
		
		return true;
	}
	
	public String toString() {
		return getWord() + "(" + getStart() + "-" + getEnd() + "): " +
			getCandidates().toString();
	}
	
	/** Determines whether duplicates exist in candidates.
	 * @param candidates  the candidate list
	 * @return true if there are duplicates, false otherwise
	 */
	private boolean checkDup(ArrayList<String> candidates) {
		int count; //Records how many instances of the string are found
		
		for(String s1 : candidates) {
			count = 0;
			for(String s2 : candidates) {
				if(s1.equals(s2)) {
					count++;
				}
			}
			if(count > 1) {
				return true;
			}
		}
		return false;
	}
	
	public boolean equals(Object o) {
		if (! (o instanceof Error)) return false;
		Error n = (Error)o;
		return start == n.start && word.equals(n.word) && 
				candidates.equals(n.candidates);
	}
	
	public int hashCode() {
		return start + word.hashCode() + candidates.hashCode();
	}
	
}

package assignment2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/** A Dictionary is a mutable set of words. 
 */
public class Dictionary {
	/*
	 * Invariants: dictionary != null &&
	 * 		dictionary does not contain null
	 */
	private HashSet<String> dictionary; //words of the dictionary
		
	/** Creates a new empty Dictionary. 
	 * @ensure for any word, lookup(word) is false
	 */
	public Dictionary() {
		dictionary = new HashSet<String>();
	}
	
	/** Adds a given word to this Dictionary.
	 * @require word is not null
	 * @ensure lookup(word) is true
	 */
	public void add(String word) {
		dictionary.add(word);
	}
	
	/** Returns true if a given word, ignoring upper- or lower-case, 
	 * is in this Dictionary, else returns false.
	 * @require word is not null
	 * @ensure result is true if word is in the Dictionary, and false
	 * 		   otherwise
	 */
	public boolean lookup(String word) {
		for(String w : dictionary) {
			if(w.compareToIgnoreCase(word) == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** Returns a list of possible corrections for a given word.
	 * @require word is not null
	 * @ensure if lookup(word) is true result is [word], otherwise
	 * 		   result is a (possibly empty) non-null list whose elements 
	 * 		   do not include null or the empty String
	 */
	public ArrayList<String> getCorrections(String word) {
		/* 
		 * corrections holds all possible corrections for a word.
		 * corrections is a HashSet to disallow duplicate elements.
		 */
		HashSet<String> corrections = new HashSet<String>();
		if(lookup(word)){
			corrections.add(word);
		} else {
			corrections.addAll(insertionErrors(word));
			corrections.addAll(deletionErrors(word));
			corrections.addAll(substitutionErrors(word));
			corrections.addAll(transpositionErrors(word));
		}

		return new ArrayList<String>(corrections);
	}
		
	/** Returns true when the class invariant holds, and false otherwise.
	 * @ensure result is true when the class invariant holds, 
	 * 		   and false otherwise
	 */
	public boolean invCheck() {
		if(dictionary == null) return false;
		if(dictionary.contains(null)) return false;
		
		return true;
	}
	
	public String toString() {
		/*
		 * dictionaryArray stores the dictionary as an array so that it can be 
		 * sorted.
		 */
		String[] dictionaryArray = 
				dictionary.toArray(new String[dictionary.size()]);
		Arrays.sort(dictionaryArray);
		
		/* doc stores the generated string representation of the dictionary */
		String doc = ""; 
		
		/* Loop through each line, appending the specified representation */
		for(int i = 0; i < dictionaryArray.length; i++) {
			doc += i + " " + dictionaryArray[i] + "\n";
		}
		
		return doc;
	}
	
	/** Returns a HashSet of possible corrections for a given word assuming
	 * only insertion errors.
	 * @require word is not null, word is not in dictionary
	 * @ensure 	result is a (possibly empty) non-null HashSet whose elements
	 * 			do not include null or the empty String.
	 */
	private HashSet<String> insertionErrors(String word) {
		/* 
		 * corrections holds all possible corrections for a word.
		 * corrections is a HashSet to disallow duplicate elements.
		 */
		HashSet<String> corrections = new HashSet<String>();
		for(int i = 0; i < word.length(); i++) {
			/* loop invariant: 	corrections contains the words in dictionary 
			 * which correspond to a single character, at position i of word, 
			 * being removed from word.
			 */
			
			/*
			 * correction is a temporary variable so that different characters
			 * can be removed during each iteration of the loop without
			 * affecting the original word.
			 */
			StringBuilder correction = new StringBuilder(word);
			if(this.lookup(correction.deleteCharAt(i).toString())) {
				corrections.add(correction.toString());
			}
		}
		
		return corrections;
	}

	/** Returns a HashSet of possible corrections for a given word assuming
	 * only deletion errors.
	 * @require word is not null, word is not in dictionary
	 * @ensure 	result is a (possibly empty) non-null HashSet whose elements
	 * 			do not include null or the empty String.
	 */
	private HashSet<String> deletionErrors(String word) {	
		/* 
		 * corrections holds all possible corrections for a word.
		 * corrections is a HashSet to disallow duplicate elements.
		 */	
		HashSet<String> corrections = new HashSet<String>();
		/*
		 * Test until i <= word.length() because we can insert an extra 
		 * character at the end of the word.
		 */
		for(int i = 0; i <= word.length(); i++) {
			/* loop invariant: 	corrections contains the words in dictionary 
			 * which correspond to a single lowercase alphabetical character 
			 * being inserted into word at position i.
			 */
			
			/*
			 * correction is a temporary variable so that different characters
			 * can be inserted during each iteration of the loop without
			 * affecting the original word.
			 */
			StringBuilder correction = new StringBuilder(word);
			for(int j = 0; j < 27; j++) {
				/* loop invariant: 	correction corresponds to a single 
				 * lowercase alphabetical character being inserted into word at 
				 * position i.
				 * 					correction is unchanged after every 
				 * iteration.
				 */
				
				/* ASCII number 97 corresponds to the character "a" */
				if(this.lookup(
						correction.insert(i, (char)(j+97)).toString())) {
					corrections.add(correction.toString());
				}
				correction.deleteCharAt(i);
			}
		}
		
		return corrections;
	}

	/** Returns a HashSet of possible corrections for a given word assuming
	 * only substitution errors.
	 * @require word is not null, word is not in dictionary
	 * @ensure 	result is a (possibly empty) non-null HashSet whose elements
	 * 			do not include null or the empty String.
	 */
	private HashSet<String> substitutionErrors(String word) {
		/* 
		 * corrections holds all possible corrections for a word.
		 * corrections is a HashSet to disallow duplicate elements.
		 */	
		HashSet<String> corrections = new HashSet<String>();
		for(int i = 0; i < word.length(); i++) {	
			/* loop invariant: 	corrections contains the words in dictionary 
			 * which correspond to a single character of word at position i
			 * being replaced with a single lowercase alphabetical character.
			 */
			
			/*
			 * correction is a temporary variable so that different characters
			 * can be substituted during each iteration of the loop without
			 * affecting the original word.
			 */	
			StringBuilder correction = new StringBuilder(word);
			for(int j = 0; j < 27; j++) {
				/* loop invariant: 	correction corresponds to a single 
				 * character of word at position i being replaced by a single 
				 * lowercase alphabetical character.
				 * 					correction is unchanged after every 
				 * iteration.
				 */
				
				/*
				 * c is a temporary variable that holds a soon-to-be-deleted
				 * character so that it may be restored later.
				 */
				char c = correction.charAt(i);

				/* ASCII number 97 corresponds to the character "a" */
				if(this.lookup(correction.replace(i, i+1, 
						"" + (char)(j+97)).toString())) {
					corrections.add(correction.toString());
				}
				correction.replace(i, i+1, "" + c);
			}
		}
		
		return corrections;
	}

	/** Returns a HashSet of possible corrections for a given word assuming
	 * only transposition errors.
	 * @require word is not null, word is not in dictionary
	 * @ensure 	result is a (possibly empty) non-null HashSet whose elements
	 * 			do not include null or the empty String.
	 */
	private HashSet<String> transpositionErrors(String word) {
		/* 
		 * corrections holds all possible corrections for a word.
		 * corrections is a HashSet to disallow duplicate elements.
		 */	
		HashSet<String> corrections = new HashSet<String>();
		/* 
		 * Test until i < word.length() - 1 because we can't swap the last
		 * character with nothing.
		 */
		for(int i = 0; i < word.length() - 1; i++) {
			/* loop invariant: 	corrections contains the words in dictionary 
			 * which correspond to two adjacent characters of word, starting 
			 * at position i, being swapped.
			 */
			
			/*
			 * correction is a temporary variable so that different characters
			 * can be transposed during each iteration of the loop without
			 * affecting the original word.
			 */	
			StringBuilder correction = new StringBuilder(word);
			/*
			 * c is a temporary variable that holds a soon-to-be-deleted
			 * character so that it may be reinserted at the next position
			 */
			char c = correction.charAt(i);
			if(this.lookup(
					correction.deleteCharAt(i).insert(i+1, c).toString())) {
				corrections.add(correction.toString());
			}
		}
		
		return corrections;
	}
}

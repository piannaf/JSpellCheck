package assignment2;

@SuppressWarnings("serial")
/** Thrown when attempting to spell check a document without a dictionary.
 * @author Graeme Smith
 * @see assignment2.SpellChecker
 */
public class NoDictionaryException extends RuntimeException {
	
	/** Creates a new NoDictionaryException without any specific message.
	 */
	public NoDictionaryException(){
		super();
	}
	
	/** Creates a new NoDictionaryException with a specific message.
	 * @param s  the specific message
	 */
	public NoDictionaryException(String s){
		super(s);
	}
}

package assignment2;

@SuppressWarnings("serial")
/** Thrown when attempting to spell check a non-existent document.
 * @author Graeme Smith
 * @see assignment2.SpellChecker
 */
public class NoDocumentException extends RuntimeException {
	
	/** Creates a new NoSuchErrorException without any specific message.
	 */
	public NoDocumentException() {
		super();
	}
	
	/** Creates a new NoSuchErrorException with a specific message.
	 * @param s  the specific message
	 */
	public NoDocumentException(String s) {
		super(s);
	}
}

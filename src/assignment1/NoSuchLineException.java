package assignment1;

@SuppressWarnings("serial")
/** Thrown when access is attempted to a non-existent line of a Document.
 * @author Graeme Smith
 * @see assignment1.Document
 */
public class NoSuchLineException extends RuntimeException {

	/** Creates a new NoSuchLineException without any specific message.
	 */
	public NoSuchLineException() {
		super();
	}
	
	/** Creates a new NoSuchLineException with a specific message.
	 * @param s  the specific message
	 */
	public NoSuchLineException(String s) {
		super(s);
	}

}

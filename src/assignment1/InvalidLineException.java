package assignment1;

@SuppressWarnings("serial")
/** Thrown when an attempt is made to create an invalid Line.
 * @author Graeme Smith
 * @see assignment1.Line
 */
public class InvalidLineException extends RuntimeException {

	/** Creates a new InvalidLineException without any specific message.
	 */
	public InvalidLineException() {
		super();
	}
	
	/** Creates a new InvalidLineException with a specific message.
	 * @param s  the specific message
	 */
	public InvalidLineException(String s) {
		super(s);
	}

}

package assignment1;

@SuppressWarnings("serial")
/** Thrown when an attempt is made to create an invalid Error.
 * @author Graeme Smith
 * @see assignment1.Error
 */
public class InvalidErrorException extends RuntimeException {

	/** Creates a new InvalidErrorException without any specific message.
	 */
	public InvalidErrorException() {
		super();
	}
	
	/** Creates a new InvalidErrorException with a specific message.
	 * @param s  the specific message
	 */
	public InvalidErrorException(String s) {
		super(s);
	}

}

package assignment1;

@SuppressWarnings("serial")
/** Thrown when access is attempted to a non-existent error of a Line.
 * @author Graeme Smith
 * @see assignment1.Line, assignment1.Document
 */
public class NoSuchErrorException extends RuntimeException {
	
	/** Creates a new NoSuchErrorException without any specific message.
	 */
	public NoSuchErrorException(){
		super();
	}
	
	/** Creates a new NoSuchErrorException with a specific message.
	 * @param s  the specific message
	 */
	public NoSuchErrorException(String s){
		super(s);
	}
	
}

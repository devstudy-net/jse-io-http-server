package net.devstudy.jse.lection05_exceptions.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class InvalidFractionNumberArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = 6014664301046746514L;

	/*public InvalidFractionNumberArgument() {
		super();
	}*/

	public InvalidFractionNumberArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidFractionNumberArgumentException(String message) {
		super(message);
	}

	/*public InvalidFractionNumberArgument(Throwable cause) {
		super(cause);
	}*/
}

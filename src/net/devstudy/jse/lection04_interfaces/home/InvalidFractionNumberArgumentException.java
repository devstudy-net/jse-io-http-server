package net.devstudy.jse.lection04_interfaces.home;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class InvalidFractionNumberArgumentException extends IllegalArgumentException {
	private static final long serialVersionUID = 5952371756004211806L;

	public InvalidFractionNumberArgumentException(String s) {
		super(s);
	}

	public InvalidFractionNumberArgumentException(Throwable cause) {
		super(cause);
	}

	public InvalidFractionNumberArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}

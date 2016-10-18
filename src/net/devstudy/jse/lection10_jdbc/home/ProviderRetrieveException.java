package net.devstudy.jse.lection10_jdbc.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class ProviderRetrieveException extends RuntimeException {
	private static final long serialVersionUID = -2767048558347173056L;

	public ProviderRetrieveException(String message) {
		super(message);
	}

	public ProviderRetrieveException(Throwable cause) {
		super(cause);
	}

	public ProviderRetrieveException(String message, Throwable cause) {
		super(message, cause);
	}
}

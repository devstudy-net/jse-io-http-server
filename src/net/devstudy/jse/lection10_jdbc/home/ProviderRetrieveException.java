package net.devstudy.jse.lection10_jdbc.home;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ProviderRetrieveException extends RuntimeException {
	private static final long serialVersionUID = 446897167697833563L;

	public ProviderRetrieveException(String message) {
		super(message);
	}

	public ProviderRetrieveException(Throwable cause) {
		super(cause);
	}

	public ProviderRetrieveException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProviderRetrieveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

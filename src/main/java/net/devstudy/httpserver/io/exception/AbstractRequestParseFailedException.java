package net.devstudy.httpserver.io.exception;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public abstract class AbstractRequestParseFailedException extends HttpServerException {
	private static final long serialVersionUID = -6160401450155043320L;
	private final String startingLine;

	public AbstractRequestParseFailedException(String message, String startingLine) {
		super(message);
		this.startingLine = startingLine;
	}

	public AbstractRequestParseFailedException(String message, Throwable cause, String startingLine) {
		super(message, cause);
		this.startingLine = startingLine;
	}
	
	public String getStartingLine() {
		return startingLine;
	}

}

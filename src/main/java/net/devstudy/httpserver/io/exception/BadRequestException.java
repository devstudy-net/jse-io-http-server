package net.devstudy.httpserver.io.exception;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class BadRequestException extends AbstractRequestParseFailedException {
	private static final long serialVersionUID = 2541817450742443303L;

	public BadRequestException(String message, Throwable cause, String startingLine) {
		super(message, cause, startingLine);
		setStatusCode(400);
	}
}

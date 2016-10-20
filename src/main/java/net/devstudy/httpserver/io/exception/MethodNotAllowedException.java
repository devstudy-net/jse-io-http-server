package net.devstudy.httpserver.io.exception;

import net.devstudy.httpserver.io.Constants;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class MethodNotAllowedException extends AbstractRequestParseFailedException {
	private static final long serialVersionUID = 1226345593185916605L;

	public MethodNotAllowedException(String method, String startingLine) {
		super("Only "+Constants.ALLOWED_METHODS+" are supported. Current method is "+method, startingLine);
		setStatusCode(405);
	}
}

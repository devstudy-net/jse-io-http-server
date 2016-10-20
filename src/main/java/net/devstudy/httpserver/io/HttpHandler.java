package net.devstudy.httpserver.io;

import java.io.IOException;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface HttpHandler {

	void handle(HttpServerContext context, HttpRequest request, HttpResponse response) throws IOException;
}

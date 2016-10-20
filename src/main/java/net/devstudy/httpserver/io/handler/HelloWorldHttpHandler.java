package net.devstudy.httpserver.io.handler;

import java.io.IOException;

import net.devstudy.httpserver.io.HttpHandler;
import net.devstudy.httpserver.io.HttpRequest;
import net.devstudy.httpserver.io.HttpResponse;
import net.devstudy.httpserver.io.HttpServerContext;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class HelloWorldHttpHandler implements HttpHandler {

	@Override
	public void handle(HttpServerContext context, HttpRequest request, HttpResponse response) throws IOException {
		response.setBody("Hello world");
	}
}

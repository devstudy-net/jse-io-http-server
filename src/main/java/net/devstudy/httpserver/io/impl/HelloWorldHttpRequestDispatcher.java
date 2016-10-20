package net.devstudy.httpserver.io.impl;

import java.io.IOException;

import net.devstudy.httpserver.io.HttpRequest;
import net.devstudy.httpserver.io.HttpResponse;
import net.devstudy.httpserver.io.HttpServerContext;
import net.devstudy.httpserver.io.config.HttpRequestDispatcher;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class HelloWorldHttpRequestDispatcher implements HttpRequestDispatcher {

	@Override
	public void handle(HttpServerContext context, HttpRequest request, HttpResponse response) throws IOException {
		response.setBody("<h1>Hello world!</h1>");
	}
}

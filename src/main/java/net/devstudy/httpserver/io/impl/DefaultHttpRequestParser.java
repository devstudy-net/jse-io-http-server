package net.devstudy.httpserver.io.impl;

import java.io.IOException;
import java.io.InputStream;

import net.devstudy.httpserver.io.HttpRequest;
import net.devstudy.httpserver.io.config.HttpRequestParser;
import net.devstudy.httpserver.io.exception.HttpServerException;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
class DefaultHttpRequestParser implements HttpRequestParser {

	@Override
	public HttpRequest parseHttpRequest(InputStream inputStream, String remoteAddress) throws IOException, HttpServerException {
		// TODO Auto-generated method stub
		return new DefaultHttpRequest();
	}

}

package net.devstudy.httpserver.io.config;

import java.io.IOException;
import java.io.InputStream;

import net.devstudy.httpserver.io.HttpRequest;
import net.devstudy.httpserver.io.exception.HttpServerException;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface HttpRequestParser {

	HttpRequest parseHttpRequest(InputStream inputStream, String remoteAddress) throws IOException, HttpServerException;
}

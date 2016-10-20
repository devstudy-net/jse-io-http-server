package net.devstudy.httpserver.io.config;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface HttpResponseWriter {

	void writeHttpResponse(OutputStream out, ReadableHttpResponse response) throws IOException;
}

package net.devstudy.httpserver.io.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import net.devstudy.httpserver.io.Constants;
import net.devstudy.httpserver.io.config.HttpResponseWriter;
import net.devstudy.httpserver.io.config.HttpServerConfig;
import net.devstudy.httpserver.io.config.ReadableHttpResponse;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
class DefaultHttpResponseWriter extends AbstractHttpConfigurableComponent implements HttpResponseWriter {

	DefaultHttpResponseWriter(HttpServerConfig httpServerConfig) {
		super(httpServerConfig);
	}
	
	@Override
	public void writeHttpResponse(OutputStream out, ReadableHttpResponse response) throws IOException {
		PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8)));
		addStartingLine(writer, response);
		addHeaders(writer, response);
		writer.println();
		writer.flush();
		addMessageBody(out, response);
	}
	
	protected void addStartingLine(PrintWriter out, ReadableHttpResponse response) {
		String httpVersion = Constants.HTTP_VERSION;
		int statusCode = response.getStatus();
		String statusMessage = httpServerConfig.getStatusMessage(statusCode);
		//HTTP/1.1 200 Ok
		out.println(String.format("%s %s %s", httpVersion, statusCode, statusMessage));
	}

	protected void addHeaders(PrintWriter out, ReadableHttpResponse response) {
		for(Map.Entry<String, String> entry : response.getHeaders().entrySet()) {
			// Content-Type: text/plain
			out.println(String.format("%s: %s", entry.getKey(), entry.getValue()));
		}
	}

	protected void addMessageBody(OutputStream out, ReadableHttpResponse response) throws IOException {
		if(!response.isBodyEmpty()) {
			out.write(response.getBody());
			out.flush();
		}
	}
}

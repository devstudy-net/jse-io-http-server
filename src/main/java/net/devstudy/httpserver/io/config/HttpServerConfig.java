package net.devstudy.httpserver.io.config;

import java.net.Socket;
import java.util.concurrent.ThreadFactory;

import net.devstudy.httpserver.io.HttpServerContext;
import net.devstudy.httpserver.io.ServerInfo;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface HttpServerConfig {

	ServerInfo getServerInfo();
	
	String getStatusMessage(int statusCode);
	
	HttpRequestParser getHttpRequestParser();
	
	HttpResponseBuilder getHttpResponseBuilder();
	
	HttpResponseWriter getHttpResponseWriter();
	
	HttpServerContext getHttpServerContext();
	
	HttpRequestDispatcher getHttpRequestDispatcher();
	
	ThreadFactory getWorkerThreadFactory();
	
	HttpClientSocketHandler buildNewHttpClientSocketHandler(Socket clientSocket);
}

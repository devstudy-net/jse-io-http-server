package net.devstudy.httpserver.io.impl;

import java.util.Properties;

import net.devstudy.httpserver.io.HandlerConfig;
import net.devstudy.httpserver.io.HttpServer;
import net.devstudy.httpserver.io.config.HttpServerConfig;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class HttpServerFactory {
	protected HttpServerFactory(){
		
	}
	
	public static HttpServerFactory create() {
		return new HttpServerFactory();
	}
	
	public HttpServer createHttpServer(HandlerConfig handlerConfig, Properties overrideServerProperties) {
		HttpServerConfig httpServerConfig = new DefaultHttpServerConfig(handlerConfig, overrideServerProperties);
		return new DefaultHttpServer(httpServerConfig);
	}
}

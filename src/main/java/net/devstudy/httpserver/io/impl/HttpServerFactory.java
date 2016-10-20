package net.devstudy.httpserver.io.impl;

import java.util.Properties;

import net.devstudy.httpserver.io.HttpServer;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class HttpServerFactory {

	protected HttpServerFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static HttpServerFactory create(){
		return new HttpServerFactory();
	}

	
	public HttpServer createHttpServer(Properties ovveridesServerProperties){
		return new HttpServer(){
			@Override
			public void start() {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void stop() {
				// TODO Auto-generated method stub
				
			}
		};
	}
}

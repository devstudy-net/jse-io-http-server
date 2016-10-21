package net.devstudy.httpserver.io.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import net.devstudy.httpserver.io.HttpServer;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class HttpServerFactoryTest {

	private HttpServerFactory httpServerFactory;
	
	@Before
	public void before(){
		httpServerFactory = HttpServerFactory.create();
	}
	
	@Test
	public void testCreate(){
		assertEquals(HttpServerFactory.class, httpServerFactory.getClass());
	}
	
	@Test
	public void testCreateHttpServer(){
		HttpServer server = httpServerFactory.createHttpServer(null, null);
		assertEquals(DefaultHttpServer.class, server.getClass());
	}
}

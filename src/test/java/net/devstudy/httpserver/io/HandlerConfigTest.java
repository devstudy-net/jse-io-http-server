package net.devstudy.httpserver.io;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.devstudy.httpserver.io.HandlerConfig;
import net.devstudy.httpserver.io.HttpHandler;
import net.devstudy.httpserver.io.exception.HttpServerConfigException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class HandlerConfigTest {
	
	private HandlerConfig config;
	
	private HttpHandler httpHandlerStub;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void before(){
		config = new HandlerConfig();
		httpHandlerStub = mock(HttpHandler.class);
	}

	@Test
	public void testAddHandler() {
		config.addHandler("/test", httpHandlerStub);
		Map<String, HttpHandler> map = config.toMap();
		
		assertSame(httpHandlerStub, map.get("/test"));
	}
	
	@Test
	public void testUnmodificationMap() {
		thrown.expect(UnsupportedOperationException.class);
		
		config.addHandler("/test", httpHandlerStub);
		Map<String, HttpHandler> map = config.toMap();
		map.clear();
	}
	
	@Test
	public void testRequiredUrl() {
		thrown.expect(NullPointerException.class);
		config.addHandler(null, httpHandlerStub);
	}
	
	@Test
	public void testRequiredHandler() {
		thrown.expect(NullPointerException.class);
		config.addHandler("/test", null);
	}
	
	@Test
	public void testAddHandlerWithError() {
		thrown.expect(HttpServerConfigException.class);
		thrown.expectMessage(new IsEqual<String>("Http handler already exists for url=/test. Http handler class: "+httpHandlerStub.getClass().getName()));
		
		config.addHandler("/test", httpHandlerStub);
		config.addHandler("/test", httpHandlerStub);
	}
}

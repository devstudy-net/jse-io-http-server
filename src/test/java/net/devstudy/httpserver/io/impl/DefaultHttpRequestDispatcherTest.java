package net.devstudy.httpserver.io.impl;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsSame;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.devstudy.httpserver.io.HandlerConfig;
import net.devstudy.httpserver.io.HttpHandler;
import net.devstudy.httpserver.io.HttpRequest;
import net.devstudy.httpserver.io.HttpResponse;
import net.devstudy.httpserver.io.HttpServerContext;
import net.devstudy.httpserver.io.exception.HttpServerException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
@SuppressWarnings("unchecked")
public class DefaultHttpRequestDispatcherTest {

	private DefaultHttpRequestDispatcher defaultHttpRequestDispatcher;

	private HttpHandler defaultHttpHandler;
	
	private HttpHandler urlHttpHandler;

	private HttpServerContext context;
	
	private HttpRequest request;
	
	private HttpResponse response;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void before() {
		context = mock(HttpServerContext.class);
		request = mock(HttpRequest.class);
		response = mock(HttpResponse.class);
		defaultHttpHandler = mock(HttpHandler.class);
		urlHttpHandler = mock(HttpHandler.class);
		defaultHttpRequestDispatcher = new DefaultHttpRequestDispatcher(defaultHttpHandler, Collections.EMPTY_MAP);
	}

	@Test
	public void testInvokeDefaultHttpHandler() throws IOException {
		defaultHttpRequestDispatcher.handle(context, request, response);
		
		verify(defaultHttpHandler).handle(context, request, response);
	}

	@Test
	public void testRuntimeException() throws IOException {
		when(request.getUri()).thenReturn("/test");
		RuntimeException re = new RuntimeException("Test runtime exception");
		doThrow(re).when(defaultHttpHandler).handle(context, request, response);
		
		thrown.expect(HttpServerException.class);
		thrown.expectMessage(new IsEqual<String>("Handle request: /test failed: Test runtime exception"));
		thrown.expectCause(new IsSame<RuntimeException>(re));
		
		defaultHttpRequestDispatcher.handle(context, request, response);
	}

	@Test
	public void testHttpServerException() throws IOException {
		HttpServerException hse = new HttpServerException("Test http exception");
		doThrow(hse).when(defaultHttpHandler).handle(context, request, response);
		
		thrown.expect(new IsSame<HttpServerException>(hse));
		
		defaultHttpRequestDispatcher.handle(context, request, response);
	}
	
	@Test
	public void testInvokeUrlHttpHandler() throws IOException {
		defaultHttpRequestDispatcher = new DefaultHttpRequestDispatcher(defaultHttpHandler, new HandlerConfig().addHandler("/test", urlHttpHandler).toMap());
		
		when(request.getUri()).thenReturn("/test");
		defaultHttpRequestDispatcher.handle(context, request, response);
		verify(urlHttpHandler).handle(context, request, response);
		
		when(request.getUri()).thenReturn("/test2");
		defaultHttpRequestDispatcher.handle(context, request, response);
		verify(defaultHttpHandler).handle(context, request, response);
	}
}

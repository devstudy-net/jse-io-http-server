package net.devstudy.httpserver.io.impl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

import org.junit.Before;
import org.junit.Test;

import net.devstudy.httpserver.io.HttpRequest;
import net.devstudy.httpserver.io.HttpServerContext;
import net.devstudy.httpserver.io.config.HttpRequestDispatcher;
import net.devstudy.httpserver.io.config.HttpRequestParser;
import net.devstudy.httpserver.io.config.HttpResponseBuilder;
import net.devstudy.httpserver.io.config.HttpResponseWriter;
import net.devstudy.httpserver.io.config.HttpServerConfig;
import net.devstudy.httpserver.io.config.ReadableHttpResponse;
import net.devstudy.httpserver.io.exception.BadRequestException;
import net.devstudy.httpserver.io.exception.MethodNotAllowedException;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultHttpClientSocketHandlerTest {

	private DefaultHttpClientSocketHandler defaultHttpClientSocketHandler;
	private Socket clientSocket;
	private String remoteAddress;
	private InputStream socketInputStream;
	private OutputStream socketOutputStream;
	
	private HttpServerConfig httpServerConfig;
	private HttpRequestParser httpRequestParser;
	private HttpResponseBuilder httpResponseBuilder;
	private HttpResponseWriter httpResponseWriter;
	private HttpServerContext httpServerContext;
	private HttpRequestDispatcher httpRequestDispatcher;
	
	private HttpRequest request;
	private ReadableHttpResponse response;
	
	@Before
	public void before() throws IOException{
		clientSocket = mock(Socket.class);
		remoteAddress = "localhost:1234";
		SocketAddress socketAddress = mock(SocketAddress.class);
		when(clientSocket.getRemoteSocketAddress()).thenReturn(socketAddress);
		when(socketAddress.toString()).thenReturn(remoteAddress);
		socketInputStream = mock(InputStream.class);
		socketOutputStream = mock(OutputStream.class);
		when(clientSocket.getInputStream()).thenReturn(socketInputStream);
		when(clientSocket.getOutputStream()).thenReturn(socketOutputStream);
		
		httpServerConfig = mock(HttpServerConfig.class);
		httpRequestParser = mock(HttpRequestParser.class);
		when(httpServerConfig.getHttpRequestParser()).thenReturn(httpRequestParser);
		httpResponseBuilder = mock(HttpResponseBuilder.class);
		when(httpServerConfig.getHttpResponseBuilder()).thenReturn(httpResponseBuilder);
		httpResponseWriter = mock(HttpResponseWriter.class);
		when(httpServerConfig.getHttpResponseWriter()).thenReturn(httpResponseWriter);
		httpRequestDispatcher = mock(HttpRequestDispatcher.class);
		when(httpServerConfig.getHttpRequestDispatcher()).thenReturn(httpRequestDispatcher);
		httpServerContext = mock(HttpServerContext.class);
		when(httpServerConfig.getHttpServerContext()).thenReturn(httpServerContext);
		defaultHttpClientSocketHandler = new DefaultHttpClientSocketHandler(clientSocket, httpServerConfig);
		
		request = mock(HttpRequest.class);
		when(httpRequestParser.parseHttpRequest(socketInputStream, remoteAddress)).thenReturn(request);
		when(request.getStartingLine()).thenReturn("GET /index.html HTTP/1.1");
		response = mock(ReadableHttpResponse.class);
		when(httpResponseBuilder.buildNewHttpResponse()).thenReturn(response);
	}
	
	@Test
	public void testCloseResoucesIfError() throws IOException{
		when(httpResponseBuilder.buildNewHttpResponse()).thenThrow(new RuntimeException("Test close"));

		defaultHttpClientSocketHandler.run();
		
		verifySocketInteractions();
	}
	
	private void verifySocketInteractions() throws IOException{
		verify(clientSocket).setKeepAlive(false);
		verify(clientSocket).close();
		verify(clientSocket).getInputStream();
		verify(clientSocket).getOutputStream();
		verify(socketInputStream).close();
		verify(socketOutputStream).close();
	}
	
	private void verifyMainBusinessFlowWithoutParseErrors() throws IOException{
		verify(httpServerConfig, times(2)).getHttpResponseBuilder();
		verify(httpResponseBuilder).buildNewHttpResponse();
		verify(httpServerConfig).getHttpRequestParser();
		verify(httpRequestParser).parseHttpRequest(socketInputStream, remoteAddress);
		verify(request).getStartingLine();
		verify(httpServerConfig).getHttpServerContext();
		verify(httpRequestDispatcher).handle(httpServerContext, request, response);
		verify(httpResponseBuilder).prepareHttpResponse(response, false);
		verify(httpServerConfig).getHttpResponseWriter();
		verify(httpResponseWriter).writeHttpResponse(socketOutputStream, response);
	}
	
	@Test
	public void testSuccessfulRequestHandle() throws IOException{
		defaultHttpClientSocketHandler.run();
		
		verifySocketInteractions();
		verifyMainBusinessFlowWithoutParseErrors();
		//verify that error does not occur
		verify(response, never()).setStatus(anyInt());
	}
	
	@Test
	public void testHandleRuntimeExceptionDuringProcessRequest() throws IOException{
		doThrow(new RuntimeException("Test")).when(httpRequestDispatcher).handle(httpServerContext, request, response);
		
		defaultHttpClientSocketHandler.run();
		
		verifySocketInteractions();
		verifyMainBusinessFlowWithoutParseErrors();
		//verify error
		verify(response).setStatus(500);
	}
	
	@Test
	public void testHandleHttpServerExceptionDuringProcessRequest() throws IOException{
		doThrow(new BadRequestException("", null, "")).when(httpRequestDispatcher).handle(httpServerContext, request, response);
		
		defaultHttpClientSocketHandler.run();
		
		verifySocketInteractions();
		verifyMainBusinessFlowWithoutParseErrors();
		//verify error
		verify(response).setStatus(400);
	}
	
	@Test
	public void testMethodNotAllowedException() throws IOException{
		when(httpRequestParser.parseHttpRequest(socketInputStream, remoteAddress)).thenThrow(new MethodNotAllowedException("PUT", "PUT /index.html HTTP/1.1"));
		
		defaultHttpClientSocketHandler.run();
		
		verifySocketInteractions();
		verify(httpServerConfig, times(2)).getHttpResponseBuilder();
		verify(httpResponseBuilder).buildNewHttpResponse();
		verify(httpServerConfig).getHttpRequestParser();
		verify(httpRequestParser).parseHttpRequest(socketInputStream, remoteAddress);
		verify(request, never()).getStartingLine();
		verify(httpServerConfig, never()).getHttpServerContext();
		verify(httpRequestDispatcher, never()).handle(httpServerContext, request, response);
		verify(httpResponseBuilder).prepareHttpResponse(response, false);
		verify(httpServerConfig).getHttpResponseWriter();
		verify(httpResponseWriter).writeHttpResponse(socketOutputStream, response);
		//verify error
		verify(response).setStatus(405);
		verify(response).setHeader("Allow", "GET, POST, HEAD");
	}
	
	@Test
	public void testHandleStartingLineFromException() throws IOException{
		when(httpRequestParser.parseHttpRequest(socketInputStream, remoteAddress)).thenThrow(new MethodNotAllowedException("HEAD", "HEAD /index.html HTTP/1.1"));
		
		defaultHttpClientSocketHandler.run();
		//TODO Add comments
		verify(httpResponseBuilder).prepareHttpResponse(response, true);
	}
	
	@Test
	public void testEOFException() throws IOException{
		when(httpRequestParser.parseHttpRequest(socketInputStream, remoteAddress)).thenThrow(new EOFException("Inputsream closed"));
		
		defaultHttpClientSocketHandler.run();
		
		verifySocketInteractions();
		verify(httpServerConfig, times(1)).getHttpResponseBuilder();
		verify(httpResponseBuilder).buildNewHttpResponse();
		verify(httpServerConfig).getHttpRequestParser();
		verify(httpRequestParser).parseHttpRequest(socketInputStream, remoteAddress);
		
		verify(request, never()).getStartingLine();
		verify(httpServerConfig, never()).getHttpServerContext();
		verify(httpRequestDispatcher, never()).handle(httpServerContext, request, response);
		verify(httpResponseBuilder, never()).prepareHttpResponse(response, false);
		verify(httpServerConfig, never()).getHttpResponseWriter();
		verify(httpResponseWriter, never()).writeHttpResponse(socketOutputStream, response);
	}
}

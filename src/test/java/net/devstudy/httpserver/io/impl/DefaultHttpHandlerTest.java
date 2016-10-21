package net.devstudy.httpserver.io.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyMapOf;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import net.devstudy.httpserver.io.HtmlTemplateManager;
import net.devstudy.httpserver.io.HttpHandler;
import net.devstudy.httpserver.io.HttpRequest;
import net.devstudy.httpserver.io.HttpResponse;
import net.devstudy.httpserver.io.HttpServerContext;
import net.devstudy.httpserver.io.config.ReadableHttpResponse;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultHttpHandlerTest {

	private HttpServerContext context;
	private HttpRequest request;
	private HttpResponse response;
	
	private HttpHandler httpHandler;
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Before
	public void before(){
		context = mock(HttpServerContext.class);
		request = mock(HttpRequest.class);
		response = mock(ReadableHttpResponse.class);
		httpHandler = new DefaultHttpHandler();
	}
	
	@Test
	public void testNotFound() throws IOException{
		Path root = Paths.get(folder.newFolder("root").toURI());
		when(context.getRootPath()).thenReturn(root);
		when(request.getUri()).thenReturn("/not-found.file");
		
		httpHandler.handle(context, request, response);
		
		verify(response).setStatus(404);
		verify(response, never()).setBody(any(InputStream.class));
		verify(response, never()).setBody(any(Reader.class));
		verify(response, never()).setBody(anyString());
		verify(response, never()).setHeader(anyString(), any(Object.class));
	}
	
	@Test
	public void testFileUri() throws IOException{
		Path root = Paths.get(folder.newFolder("root").toURI());
		File file = new File(root.toFile(), "file.css");
		Files.write(Paths.get(file.toURI()), "test css".getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
		when(context.getContentType("css")).thenReturn("text/css");
		when(context.getExpiresDaysForResource("css")).thenReturn(7);
		when(context.getRootPath()).thenReturn(root);
		when(request.getUri()).thenReturn("/file.css");
		
		httpHandler.handle(context, request, response);
		
		
		verify(response).setHeader("Content-Type", "text/css");
		verify(response).setHeader("Last-Modified", Files.getLastModifiedTime(Paths.get(file.toURI()), LinkOption.NOFOLLOW_LINKS));
		verify(response).setHeader(eq("Expires"), any(Date.class));
		verify(response).setBody(any(InputStream.class));
		
		verify(response, never()).setStatus(anyInt());
		verify(response, never()).setBody(any(Reader.class));
		verify(response, never()).setBody(anyString());
	}
	
	@Test
	public void testFileWithoutExpiresUri() throws IOException{
		Path root = Paths.get(folder.newFolder("root").toURI());
		File file = new File(root.toFile(), "file.css");
		Files.write(Paths.get(file.toURI()), "test css".getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
		when(context.getContentType("css")).thenReturn("text/css");
		when(context.getExpiresDaysForResource("css")).thenReturn(null);
		when(context.getRootPath()).thenReturn(root);
		when(request.getUri()).thenReturn("/file.css");
		
		httpHandler.handle(context, request, response);
		
		
		verify(response).setHeader("Content-Type", "text/css");
		verify(response).setHeader("Last-Modified", Files.getLastModifiedTime(Paths.get(file.toURI()), LinkOption.NOFOLLOW_LINKS));
		verify(response).setBody(any(InputStream.class));
		
		verify(response, never()).setHeader(eq("Expires"), any(Date.class));
		verify(response, never()).setStatus(anyInt());
		verify(response, never()).setBody(any(Reader.class));
		verify(response, never()).setBody(anyString());
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void testDirectoryUri() throws IOException{
		HtmlTemplateManager htmlTemplateManager = mock(HtmlTemplateManager.class);
		when(context.getHtmlTemplateManager()).thenReturn(htmlTemplateManager);
		when(htmlTemplateManager.processTemplate(eq("simple.html"), anyMapOf(String.class, Object.class))).thenReturn("result");
		Path root = Paths.get(folder.newFolder("root").toURI());
		File dir = new File(root.toFile(), "dir");
		dir.mkdirs();
		Files.write(Paths.get(dir.getAbsolutePath()+"/test.css"), "test css".getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
		Files.write(Paths.get(dir.getAbsolutePath()+"/test.js"), "test js".getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
		
		when(context.getContentType("css")).thenReturn("text/css");
		when(context.getRootPath()).thenReturn(root);
		when(request.getUri()).thenReturn("/dir");
		
		httpHandler.handle(context, request, response);
		
		verify(htmlTemplateManager).processTemplate(eq("simple.html"), anyMapOf(String.class, Object.class));
		
		verify(response).setBody(anyString());
		
		verify(response, never()).setHeader(anyString(), any(Object.class));
		verify(response, never()).setStatus(anyInt());
		verify(response, never()).setBody(any(Reader.class));
		verify(response, never()).setBody(any(InputStream.class));
	}
}

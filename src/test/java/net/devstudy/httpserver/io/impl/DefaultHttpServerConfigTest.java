package net.devstudy.httpserver.io.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.io.input.ReaderInputStream;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import net.devstudy.httpserver.io.HandlerConfig;
import net.devstudy.httpserver.io.HttpHandler;
import net.devstudy.httpserver.io.ServerInfo;
import net.devstudy.httpserver.io.config.HttpClientSocketHandler;
import net.devstudy.httpserver.io.exception.HttpServerConfigException;
import net.devstudy.matcher.CauseMatcher;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultHttpServerConfigTest {

	private DefaultHttpServerConfig defaultHttpServerConfig;
	
	@Before
	public void before(){
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, null));
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void testDefaultInitState(){
		assertEquals(DefaultHttpRequestParser.class, defaultHttpServerConfig.getHttpRequestParser().getClass());
		assertEquals(DefaultHttpResponseBuilder.class, defaultHttpServerConfig.getHttpResponseBuilder().getClass());
		assertEquals(DefaultHttpResponseWriter.class, defaultHttpServerConfig.getHttpResponseWriter().getClass());
		assertEquals(DefaultHttpServerContext.class, defaultHttpServerConfig.getHttpServerContext().getClass());
		assertEquals(DefaultHttpHandler.class, defaultHttpServerConfig.getDefaultHttpHandler().getClass());
		assertEquals(DefaultHttpRequestDispatcher.class, defaultHttpServerConfig.getHttpRequestDispatcher().getClass());
		assertEquals(DefaultThreadFactory.class, defaultHttpServerConfig.getWorkerThreadFactory().getClass());
		assertEquals(DefaultHtmlTemplateManager.class, defaultHttpServerConfig.getHtmlTemplateManager().getClass());
		assertNotNull(defaultHttpServerConfig.getServerProperties());
		
	}
	
	@Test
	public void testHandlerConfig(){
		assertNotNull(defaultHttpServerConfig.getHttpHandlers());
		assertTrue(defaultHttpServerConfig.getHttpHandlers().isEmpty());
		
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(new HandlerConfig().addHandler("/url", mock(HttpHandler.class)), null));
		assertEquals(1, defaultHttpServerConfig.getHttpHandlers().size());
		assertNotNull(defaultHttpServerConfig.getHttpHandlers().get("/url"));
	}
	
	@Test
	public void testOverrideProperties(){
		ServerInfo serverInfo = defaultHttpServerConfig.getServerInfo();
		assertEquals(9090, serverInfo.getPort());
		
		Properties prop = new Properties();
		prop.setProperty("server.port", "0");
		prop.setProperty("db.datasource.enabled", "false");
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, prop));
		
		serverInfo = defaultHttpServerConfig.getServerInfo();
		assertEquals(0, serverInfo.getPort());
		assertNull(defaultHttpServerConfig.getDataSource());
	}
	
	@Test
	public void testServerInfo(){
		Properties prop = new Properties();
		prop.setProperty("server.port", "9090");
		prop.setProperty("server.name", "Devstudy HTTP server");
		prop.setProperty("server.thread.count", "0");
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, prop));
		ServerInfo serverInfo = defaultHttpServerConfig.getServerInfo();
		assertEquals(9090, serverInfo.getPort());
		assertEquals(0, serverInfo.getThreadCount());
		assertEquals("Devstudy HTTP server", serverInfo.getName());
		assertEquals("ServerInfo [name=Devstudy HTTP server, port=9090, threadCount=UNLIMITED]", serverInfo.toString());
		
		prop.setProperty("server.thread.count", "5");
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, prop));
		serverInfo = defaultHttpServerConfig.getServerInfo();
		assertEquals(5, serverInfo.getThreadCount());
		assertEquals("ServerInfo [name=Devstudy HTTP server, port=9090, threadCount=5]", serverInfo.toString());
	}
	
	@Test
	public void testServerInfoInvalidThreadCount(){
		thrown.expect(HttpServerConfigException.class);
		thrown.expectMessage("server.thread.count should be >= 0");
		
		Properties prop = new Properties();
		prop.setProperty("server.thread.count", "-4");
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, prop));
	}
	
	@Test
	public void testServerInfoInvalidPortMoreThan65535(){
		thrown.expect(HttpServerConfigException.class);
		thrown.expectMessage("server.port should be between 0 and 65535");
		
		Properties prop = new Properties();
		prop.setProperty("server.port", "70000");
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, prop));
	}
	
	@Test
	public void testServerInfoInvalidPortLessThan0(){
		thrown.expect(HttpServerConfigException.class);
		thrown.expectMessage("server.port should be between 0 and 65535");
		
		Properties prop = new Properties();
		prop.setProperty("server.port", "-1");
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, prop));
	}
	
	@Test
	public void testLoadPropertiesSuccess() throws IOException{
		InputStream in = spy(new ReaderInputStream(new StringReader("k=v\r\na=b"), StandardCharsets.UTF_8));
		ClassLoader cl = mock(ClassLoader.class);
		when(cl.getResourceAsStream("name")).thenReturn(in);
		
		defaultHttpServerConfig.loadProperties(new Properties(), cl, "name");
		
		verify(in, atLeast(1)).read(any(byte[].class));
		verify(in).close();
	}
	
	@Test
	public void testLoadNotFoundProperties() throws IOException{
		thrown.expect(HttpServerConfigException.class);
		thrown.expectMessage("Classpath resource not found");
		ClassLoader cl = mock(ClassLoader.class);
		when(cl.getResourceAsStream("name")).thenReturn(null);
		
		defaultHttpServerConfig.loadProperties(new Properties(), cl, "name");
	}
	
	@Test
	public void testLoadPropertiesIOException() throws IOException{
		thrown.expect(HttpServerConfigException.class);
		thrown.expectMessage("Can't load properties from resource");
		thrown.expectCause(new CauseMatcher(IOException.class, "IO error"));
		
		InputStream in = mock(InputStream.class);
		when(in.read(any(byte[].class))).thenThrow(new IOException("IO error"));
		ClassLoader cl = mock(ClassLoader.class);
		when(cl.getResourceAsStream("name")).thenReturn(in);
		
		defaultHttpServerConfig.loadProperties(new Properties(), cl, "name");
	}
	
	@Test
	public void testBuildNewHttpClientSocketHandler(){
		Socket clientSocket = mock(Socket.class);
		SocketAddress socketAddress = mock(SocketAddress.class);
		when(clientSocket.getRemoteSocketAddress()).thenReturn(socketAddress);
		when(socketAddress.toString()).thenReturn("localhost:1234");
		
		HttpClientSocketHandler handler1 = defaultHttpServerConfig.buildNewHttpClientSocketHandler(clientSocket);
		HttpClientSocketHandler handler2 = defaultHttpServerConfig.buildNewHttpClientSocketHandler(clientSocket);
		assertEquals(DefaultHttpClientSocketHandler.class, handler1.getClass());
		assertNotSame("buildNewHttpClientSocketHandler should create a new instance of HttpClientSocketHandler for each call",  handler1, handler2);
	}
	
	@Test
	public void testClose() throws SQLException{
		final BasicDataSource ds = mock(BasicDataSource.class);
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, null) {
			@Override
			protected BasicDataSource createBasicDataSource() {
				return ds;
			}
		});
		
		defaultHttpServerConfig.close();
		
		verify(ds).close();
	}
	
	@Test
	public void testCloseNotInvoke() throws SQLException{
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, null) {
			@Override
			protected BasicDataSource createBasicDataSource() {
				return null;
			}
		});
		
		try {
			defaultHttpServerConfig.close();
		} catch (Exception e) {
			fail("Exception should not ocurred: "+e.getMessage());
		}
	}
	
	@Test
	public void testCloseWithError() throws SQLException{
		final BasicDataSource ds = mock(BasicDataSource.class);
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, null) {
			@Override
			protected BasicDataSource createBasicDataSource() {
				return ds;
			}
		});
		doThrow(new SQLException("Close failed")).when(ds).close();
		
		defaultHttpServerConfig.close();
		
		verify(ds).close();
	}
	
	@Test
	public void testGetStatusMessage() throws IOException{
		assertEquals("OK", defaultHttpServerConfig.getStatusMessage(200));
		assertEquals("Internal Server Error", defaultHttpServerConfig.getStatusMessage(-1));
	}
	
	@Test
	public void testCreateRootPath() throws IOException{
		File file = folder.newFolder("root");
		Properties prop = new Properties();
		prop.setProperty("wepapp.static.dir.root", file.getAbsolutePath());
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, prop));
		Path root = defaultHttpServerConfig.createRootPath();
		assertEquals(file.getAbsolutePath(), root.toAbsolutePath().toString());
	}
	
	@Test
	public void testRootPathNotFound() throws IOException{
		thrown.expect(HttpServerConfigException.class);
		thrown.expectMessage("Root path not found");
		
		Properties prop = new Properties();
		prop.setProperty("wepapp.static.dir.root", "not-found-path");
		spy(new DefaultHttpServerConfig(null, prop));
	}
	
	@Test
	public void testRootPathNotDirectory() throws IOException{
		thrown.expect(HttpServerConfigException.class);
		thrown.expectMessage("Root path is not directory");
		
		File file = folder.newFile();
		Properties prop = new Properties();
		prop.setProperty("wepapp.static.dir.root", file.getAbsolutePath());
		spy(new DefaultHttpServerConfig(null, prop));
	}
	
}

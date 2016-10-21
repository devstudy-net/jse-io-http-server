package net.devstudy.httpserver.io.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.devstudy.httpserver.io.ServerInfo;
import net.devstudy.httpserver.io.config.HttpClientSocketHandler;
import net.devstudy.httpserver.io.config.HttpServerConfig;
import net.devstudy.httpserver.io.exception.HttpServerException;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultHttpServerTest {

	private DefaultHttpServer server;
	
	private HttpServerConfig httpServerConfig;
	private ServerSocket serverSocket;
	private ExecutorService executorService;
	private Thread mainServerThread;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void before(){
		httpServerConfig = mock(HttpServerConfig.class);
		serverSocket = mock(ServerSocket.class);
		executorService = mock(ExecutorService.class);
		mainServerThread = mock(Thread.class);
	}
	
	@Test
	public void testCreateMainThread(){
		server = new DefaultHttpServer(httpServerConfig) {
			@Override
			protected ExecutorService createExecutorService() {
				return executorService;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
			@Override
			protected Runnable createServerRunnable() {
				return mock(Runnable.class);
			}
		};
		Thread thread = server.createMainServerThread(mock(Runnable.class));
		assertEquals(Thread.MAX_PRIORITY, thread.getPriority());
		assertEquals("Main Server Thread", thread.getName());
		assertFalse(thread.isDaemon());
		assertFalse(thread.isAlive());
	}
	
	@Test
	public void testClientConnectionDispatcherSuccess() throws Exception{
		final Runnable [] run = new Runnable[1];
		server = new DefaultHttpServer(httpServerConfig){
			@Override
			protected ExecutorService createExecutorService() {
				return executorService;
			}
			@Override
			protected Thread createMainServerThread(Runnable r) {
				return mainServerThread;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
			@Override
			protected Runnable createServerRunnable() {
				Runnable r = super.createServerRunnable();
				run[0] = r;
				return r;
			}
		};
		when(mainServerThread.isInterrupted()).thenReturn(false).thenReturn(true);
		Socket clientSocket = mock(Socket.class);
		when(serverSocket.accept()).thenReturn(clientSocket);
		HttpClientSocketHandler httpClientSocketHandler = mock(HttpClientSocketHandler.class);
		when(httpServerConfig.buildNewHttpClientSocketHandler(clientSocket)).thenReturn(httpClientSocketHandler);
		
		run[0].run();
		
		verify(mainServerThread, times(2)).isInterrupted();
		verify(serverSocket).accept();
		verify(httpServerConfig).buildNewHttpClientSocketHandler(clientSocket);
		verify(executorService).submit(httpClientSocketHandler);
		
		verify(httpServerConfig, never()).close();
		verify(executorService, never()).shutdownNow();
	}
	
	@Test
	public void testClientConnectionDispatcherFailed() throws Exception{
		final Runnable [] run = new Runnable[1];
		server = new DefaultHttpServer(httpServerConfig){
			@Override
			protected ExecutorService createExecutorService() {
				return executorService;
			}
			@Override
			protected Thread createMainServerThread(Runnable r) {
				return mainServerThread;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
			@Override
			protected Runnable createServerRunnable() {
				Runnable r = super.createServerRunnable();
				run[0] = r;
				return r;
			}
		};
		when(mainServerThread.isInterrupted()).thenReturn(false).thenReturn(true);
		when(serverSocket.accept()).thenThrow(new IOException("Accept failed"));
		
		run[0].run();
		
		verify(mainServerThread, times(1)).isInterrupted();
		verify(serverSocket).accept();
		verify(executorService, never()).submit(any(Runnable.class));
		
		verify(httpServerConfig).close();
		verify(executorService).shutdownNow();
	}
	
	@Test
	public void testCreateCachedExecutorService() throws Exception{
		ThreadFactory threadFactory = mock(ThreadFactory.class);
		when(httpServerConfig.getWorkerThreadFactory()).thenReturn(threadFactory);
		ServerInfo serverInfo = mock(ServerInfo.class);
		when(httpServerConfig.getServerInfo()).thenReturn(serverInfo);
		when(serverInfo.getThreadCount()).thenReturn(0);
		
		server = new DefaultHttpServer(httpServerConfig){
			@Override
			protected Thread createMainServerThread(Runnable r) {
				return mainServerThread;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
			@Override
			protected Runnable createServerRunnable() {
				return mock(Runnable.class);
			}
		};
		
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) server.createExecutorService();
		assertEquals(0, executorService.getCorePoolSize());
		assertEquals(Integer.MAX_VALUE, executorService.getMaximumPoolSize());
		assertEquals(60, executorService.getKeepAliveTime(TimeUnit.SECONDS));
		assertSame(threadFactory, executorService.getThreadFactory());
		assertTrue(executorService.getQueue() instanceof SynchronousQueue);
	}
	
	@Test
	public void testCreateFixedExecutorService() throws Exception{
		ThreadFactory threadFactory = mock(ThreadFactory.class);
		when(httpServerConfig.getWorkerThreadFactory()).thenReturn(threadFactory);
		ServerInfo serverInfo = mock(ServerInfo.class);
		when(httpServerConfig.getServerInfo()).thenReturn(serverInfo);
		when(serverInfo.getThreadCount()).thenReturn(5);
		
		server = new DefaultHttpServer(httpServerConfig){
			@Override
			protected Thread createMainServerThread(Runnable r) {
				return mainServerThread;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
			@Override
			protected Runnable createServerRunnable() {
				return mock(Runnable.class);
			}
		};
		
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) server.createExecutorService();
		assertEquals(5, executorService.getCorePoolSize());
		assertEquals(5, executorService.getMaximumPoolSize());
		assertEquals(0, executorService.getKeepAliveTime(TimeUnit.SECONDS));
		assertSame(threadFactory, executorService.getThreadFactory());
		assertTrue(executorService.getQueue() instanceof LinkedBlockingQueue);
	}
	
	@Test
	public void testStartSuccess(){
		when(mainServerThread.getState()).thenReturn(Thread.State.NEW);
		server = new DefaultHttpServer(httpServerConfig){
			@Override
			protected ExecutorService createExecutorService() {
				return executorService;
			}
			@Override
			protected Thread createMainServerThread(Runnable r) {
				return mainServerThread;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
			@Override
			protected Thread getShutdownHook() {
				return new Thread();
			}
			@Override
			protected Runnable createServerRunnable() {
				return mock(Runnable.class);
			}
		};
		server.start();
		
		verify(mainServerThread).start();
		verify(mainServerThread).getState();
	}
	
	@Test
	public void testStartFailed(){
		thrown.expect(HttpServerException.class);
		thrown.expectMessage(new IsEqual<String>("Current web server already started or stopped! Please create a new http server instance"));
		
		when(mainServerThread.getState()).thenReturn(Thread.State.TERMINATED);
		server = new DefaultHttpServer(httpServerConfig){
			@Override
			protected ExecutorService createExecutorService() {
				return executorService;
			}
			@Override
			protected Thread createMainServerThread(Runnable r) {
				return mainServerThread;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
		};
		server.start();
	}
	
	@Test
	public void testStopSuccess() throws IOException{
		server = new DefaultHttpServer(httpServerConfig){
			@Override
			protected ExecutorService createExecutorService() {
				return executorService;
			}
			@Override
			protected Thread createMainServerThread(Runnable r) {
				return mainServerThread;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
		};
		server.stop();
		
		verify(mainServerThread).interrupt();
		verify(serverSocket).close();
	}
	
	@Test
	public void testStopWithIOException() throws IOException{
		doThrow(new IOException("Close")).when(serverSocket).close();
		server = new DefaultHttpServer(httpServerConfig){
			@Override
			protected ExecutorService createExecutorService() {
				return executorService;
			}
			@Override
			protected Thread createMainServerThread(Runnable r) {
				return mainServerThread;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
		};
		server.stop();
		
		verify(mainServerThread).interrupt();
		verify(serverSocket).close();
	}
	
	@Test
	public void testDestroyServerWithException() throws Exception{
		doThrow(new Exception("Close")).when(httpServerConfig).close();
		server = new DefaultHttpServer(httpServerConfig){
			@Override
			protected ExecutorService createExecutorService() {
				return executorService;
			}
			@Override
			protected Thread createMainServerThread(Runnable r) {
				return mainServerThread;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
		};
		server.destroyHttpServer();
		
		verify(httpServerConfig).close();
		verify(executorService).shutdownNow();
	}
	
	@Test
	public void testShutdownHook() throws Exception{
		server = new DefaultHttpServer(httpServerConfig){
			@Override
			protected ExecutorService createExecutorService() {
				return executorService;
			}
			@Override
			protected Thread createMainServerThread(Runnable r) {
				return mainServerThread;
			}
			@Override
			protected ServerSocket createServerSocket() {
				return serverSocket;
			}
		};
		server.getShutdownHook().run();
		
		verify(httpServerConfig).close();
		verify(executorService).shutdownNow();
	}
}

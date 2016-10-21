package net.devstudy.httpserver.io.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.devstudy.httpserver.io.exception.HttpServerConfigException;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultHttpServerContextTest {

	private DefaultHttpServerContext defaultHttpServerContext;
	private DefaultHttpServerConfig defaultHttpServerConfig;
	
	@Before
	public void before(){
		defaultHttpServerConfig = spy(new DefaultHttpServerConfig(null, null));
		defaultHttpServerContext = new DefaultHttpServerContext(defaultHttpServerConfig);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testGetDataSourceSuccess(){
		BasicDataSource basicDataSource = mock(BasicDataSource.class);
		when(defaultHttpServerConfig.getDataSource()).thenReturn(basicDataSource);
		
		DataSource ds = defaultHttpServerContext.getDataSource();
		assertSame(basicDataSource, ds);
	}
	
	@Test
	public void testGetDataSourceFailed(){
		thrown.expect(HttpServerConfigException.class);
		thrown.expectMessage(new IsEqual<String>("Datasource is not configured for this context"));
		when(defaultHttpServerConfig.getDataSource()).thenReturn(null);
		
		defaultHttpServerContext.getDataSource();
	}
	
	@Test
	public void testGetServerInfo(){
		defaultHttpServerContext.getServerInfo();
		verify(defaultHttpServerConfig).getServerInfo();
	}
	
	@Test
	public void testGetRootPath(){
		defaultHttpServerContext.getRootPath();
		verify(defaultHttpServerConfig).getRootPath();
	}
	
	@Test
	public void testGetContentType(){
		Properties contentType = new Properties();
		contentType.setProperty("css", "text/css");
		when(defaultHttpServerConfig.getMimeTypesProperties()).thenReturn(contentType);
		
		String actual = defaultHttpServerContext.getContentType("css");
		assertEquals("text/css", actual);
		actual = defaultHttpServerContext.getContentType("not-found");
		assertEquals("text/plain", actual);
	}
	
	@Test
	public void testGetHtmlTemplateManager(){
		defaultHttpServerContext.getHtmlTemplateManager();
		verify(defaultHttpServerConfig).getHtmlTemplateManager();
	}
	
	@Test
	public void testGetExpiresDaysForResource(){
		when(defaultHttpServerConfig.getStaticExpiresDays()).thenReturn(7);
		when(defaultHttpServerConfig.getStaticExpiresExtensions()).thenReturn(Collections.singletonList("css"));
		
		Integer expires = defaultHttpServerContext.getExpiresDaysForResource("css");
		assertEquals(Integer.valueOf(7), expires);
		
		expires = defaultHttpServerContext.getExpiresDaysForResource("js");
		assertNull(expires);
	}
	
	@Test
	public void testGetSupportedRequestMethods(){
		assertEquals(Arrays.asList(new String[]{"GET", "POST", "HEAD"}), defaultHttpServerContext.getSupportedRequestMethods());
	}
	
	@Test
	public void testGetSupportedResponseStatuses(){
		Properties originalProperties = new Properties();
		originalProperties.setProperty("key", "value");
		when(defaultHttpServerConfig.getStatusesProperties()).thenReturn(originalProperties);
		
		Properties actual = defaultHttpServerContext.getSupportedResponseStatuses();
		assertNotSame(originalProperties, actual);
		actual.clear();
		assertEquals("value", originalProperties.getProperty("key"));
		assertEquals(1, originalProperties.size());
	}
}

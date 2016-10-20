package net.devstudy.httpserver.io.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.devstudy.httpserver.io.utils.HttpUtils;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class HttpUtilsTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testNormilizeHeaderName(){
		assertEquals("Content-Type", HttpUtils.normilizeHeaderName("Content-Type"));
		assertEquals("Content-Type", HttpUtils.normilizeHeaderName("content-type"));
		assertEquals("Content-Type", HttpUtils.normilizeHeaderName("CONTENT-TYPE"));
		assertEquals("Content-Type", HttpUtils.normilizeHeaderName("CONTENT-type"));
		assertEquals("Content-Type", HttpUtils.normilizeHeaderName("CoNtEnT-tYpE"));
		
		assertEquals("Expires", HttpUtils.normilizeHeaderName("Expires"));
		assertEquals("Expires", HttpUtils.normilizeHeaderName("expires"));
		assertEquals("Expires", HttpUtils.normilizeHeaderName("EXPIRES"));
		
		assertEquals("If-Modified-Since", HttpUtils.normilizeHeaderName("If-Modified-Since"));
		assertEquals("If-Modified-Since", HttpUtils.normilizeHeaderName("if-modified-since"));
		assertEquals("If-Modified-Since", HttpUtils.normilizeHeaderName("IF-MODIFIED-SINCE"));
		
		assertEquals("Test-", HttpUtils.normilizeHeaderName("Test-"));
		assertEquals("Test-", HttpUtils.normilizeHeaderName("TEST-"));
		assertEquals("Test-", HttpUtils.normilizeHeaderName("test-"));
	}
	
	@Test
	public void testEOFException() throws IOException{
		thrown.expect(EOFException.class);
		thrown.expectMessage(new IsEqual<String>("InputStream closed"));
		InputStream in = mock(InputStream.class);
		when(in.read()).thenReturn(-1);
		
		HttpUtils.readStartingLineAndHeaders(in);
	}
}

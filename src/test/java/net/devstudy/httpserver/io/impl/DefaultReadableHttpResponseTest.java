package net.devstudy.httpserver.io.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsSame;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.devstudy.httpserver.io.config.ReadableHttpResponse;
import net.devstudy.httpserver.io.exception.HttpServerException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultReadableHttpResponseTest {

	private ReadableHttpResponse httpResponse;

	@Before
	public void before() {
		httpResponse = new DefaultReadableHttpResponse();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testInitState() {
		assertEquals(200, httpResponse.getStatus());
		assertEquals(LinkedHashMap.class, httpResponse.getHeaders().getClass());
		assertTrue(httpResponse.isBodyEmpty());
	}
	
	@Test
	public void testStatus() {
		assertEquals(200, httpResponse.getStatus());
		httpResponse.setStatus(404);
		assertEquals(404, httpResponse.getStatus());
	}
	
	@Test
	public void testSetBodyStringNotNull() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage(new IsEqual<String>("Content can't be null"));
		httpResponse.setBody((String)null);
	}
	
	@Test
	public void testSetBodyInputStreamNotNull() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage(new IsEqual<String>("InputStream can't be null"));
		httpResponse.setBody((InputStream)null);
	}
	
	@Test
	public void testSetBodyReaderNotNull() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage(new IsEqual<String>("Reader can't be null"));
		httpResponse.setBody((Reader)null);
	}

	@Test
	public void testEmptyContentString() {
		httpResponse.setBody("");
		assertTrue(httpResponse.isBodyEmpty());
	}
	
	@Test
	public void testEmptyGetBodyLength() {
		httpResponse.setBody("");
		assertEquals(0, httpResponse.getBodyLength());
	}
	
	@Test
	public void testNotEmptyGetBodyLength() {
		httpResponse.setBody("0123456789");
		assertEquals(10, httpResponse.getBodyLength());
	}

	@Test
	public void testEmptyContentInputStream() {
		ByteArrayInputStream in = new ByteArrayInputStream(new byte[] {});
		httpResponse.setBody(in);
		assertTrue(httpResponse.isBodyEmpty());
	}

	@Test
	public void testEmptyContentReader() {
		StringReader reader = new StringReader("");
		httpResponse.setBody(reader);
		assertTrue(httpResponse.isBodyEmpty());
	}

	@Test
	public void testNotEmptyContent() {
		httpResponse.setBody("h");
		assertFalse(httpResponse.isBodyEmpty());
	}
	
	@Test
	public void testSetHeaderString() {
		httpResponse.setHeader("Test", "value");
		assertEquals("value", httpResponse.getHeaders().get("Test"));
	}
	
	@Test
	public void testSetHeaderNormalize() {
		httpResponse.setHeader("TEST", "value");
		assertEquals("value", httpResponse.getHeaders().get("Test"));
		
		httpResponse.setHeader("TEST-header", "value");
		assertEquals("value", httpResponse.getHeaders().get("Test-Header"));
		
		httpResponse.setHeader("test-header", "value");
		assertEquals("value", httpResponse.getHeaders().get("Test-Header"));
	}
	
	@Test
	public void testSetHeaderNameNotNull() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage(new IsEqual<String>("Name can't be null"));
		httpResponse.setHeader(null, "value");
	}
	
	@Test
	public void testSetHeaderValueNotNull() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage(new IsEqual<String>("Value can't be null"));
		httpResponse.setHeader("name", null);
	}
	
	@Test
	public void testSetHeaderInt() {
		httpResponse.setHeader("Test", 1);
		assertEquals("1", httpResponse.getHeaders().get("Test"));
	}
	
	@Test
	public void testSetHeaderDouble() {
		httpResponse.setHeader("Test", 1.2);
		assertEquals("1.2", httpResponse.getHeaders().get("Test"));
	}
	
	@Test
	public void testSetHeaderBoolean() {
		httpResponse.setHeader("Test", true);
		assertEquals("true", httpResponse.getHeaders().get("Test"));
	}
	
	@Test
	public void testSetHeaderDate() {
		long time = 1474736591920L;
		httpResponse.setHeader("Test", new Date(time));
		assertEquals("Sat, 24 Sep 2016 20:03:11 +0300", httpResponse.getHeaders().get("Test"));
	}
	
	@Test
	public void testSetHeaderFileTime() {
		long time = 1474736591920L;
		httpResponse.setHeader("Test", FileTime.from(time, TimeUnit.MILLISECONDS));
		assertEquals("Sat, 24 Sep 2016 20:03:11 +0300", httpResponse.getHeaders().get("Test"));
	}
	
	@Test
	public void testSetBodyInputStreamIoException() throws IOException {
		final IOException cause = new IOException("Test");
		thrown.expect(HttpServerException.class);
		thrown.expectCause(new IsSame<Throwable>(cause));
		thrown.expectMessage(new IsEqual<String>("Can't set http response body from inputstream: Test"));
		
		InputStream in = mock(InputStream.class);
		when(in.read(any(byte[].class))).thenThrow(cause);
		httpResponse.setBody(in);
	}
	
	@Test
	public void testSetBodyReaderIoException() throws IOException {
		final IOException cause = new IOException("Test");
		thrown.expect(HttpServerException.class);
		thrown.expectCause(new IsSame<Throwable>(cause));
		thrown.expectMessage(new IsEqual<String>("Can't set http response body from reader: Test"));
		
		Reader reader = mock(Reader.class);
		when(reader.read(any(char[].class))).thenThrow(cause);
		
		httpResponse.setBody(reader);
	}
	
	@Test
	public void testGetBody() {
		httpResponse.setBody("A");
		assertArrayEquals(new byte[]{65}, httpResponse.getBody());
		assertEquals(1, httpResponse.getBody().length);
	}
	
	@Test
	public void testGetEmptyBody() {
		httpResponse.setBody("");
		assertArrayEquals(new byte[]{}, httpResponse.getBody());
		assertEquals(0, httpResponse.getBody().length);
	}
}

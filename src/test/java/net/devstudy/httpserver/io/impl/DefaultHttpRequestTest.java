package net.devstudy.httpserver.io.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultHttpRequestTest {

	private Map<String, String> map;

	@Before
	public void before() {
		map = new HashMap<>();
		map.put("name", "value");
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testUnmodifiableHeaderMap() {
		thrown.expect(UnsupportedOperationException.class);
		
		DefaultHttpRequest request = new DefaultHttpRequest(null, null, null, null, map, map);
		request.getHeaders().clear();
	}

	@Test
	public void testUnmodifiableParameterMap() {
		thrown.expect(UnsupportedOperationException.class);
		
		DefaultHttpRequest request = new DefaultHttpRequest(null, null, null, null, map, map);
		request.getParameters().clear();
	}

	@Test
	public void testGetStartingLine() {
		DefaultHttpRequest request = new DefaultHttpRequest("GET", "/index.html", "HTTP/1.1", null, map, map);
		assertEquals("GET /index.html HTTP/1.1", request.getStartingLine());
	}
}

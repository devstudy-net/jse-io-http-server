package net.devstudy.httpserver.io.utils;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DataUtilsTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testBuildMap() {
		Map<String, Object> map = DataUtils.buildMap(new Object[][] { { "2", 2 }, { "1", 1 } });
		assertEquals(2, map.size());
		assertEquals(2, map.get("2"));
		assertEquals(1, map.get("1"));
	}
	
	@Test
	public void testEmptyBuildMap() {
		Map<String, Object> map = DataUtils.buildMap(new Object[][] {});
		assertEquals(0, map.size());
	}
	
	@Test
	public void testUnmodificationMap() {
		thrown.expect(UnsupportedOperationException.class);
		Map<String, Object> map = DataUtils.buildMap(new Object[][] {});
		map.clear();
	}
}

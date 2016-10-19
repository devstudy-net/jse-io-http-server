package net.devstudy.jse.lection12_junit;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class SimpleTest2 {
	@Test
	public void testSum() {
		int actual = 2 + 2;
		int expected = 4;
		Assert.assertEquals(expected, actual);
	}
}

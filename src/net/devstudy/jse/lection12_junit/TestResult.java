package net.devstudy.jse.lection12_junit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class TestResult {
	@Test
	public void testOk() {
		Assert.assertEquals(4, 2 + 2);
	}

	@Test
	public void testFailed() {
		Assert.assertEquals(5, 2 + 2);
	}

	@Test
	public void errorDuringTest() {
		Assert.assertEquals(5, 2 / 0);
	}

	@Test
	@Ignore
	public void ignore() {
		ignore();
	}
}

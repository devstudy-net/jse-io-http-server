package net.devstudy.jse.lection12_junit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class AssertTest{
	@Test
	public void testAssertOptions() {
		assertArrayEquals(new int[] { 1 }, new int[] { 1 });
		assertEquals(5, 5);
		assertNotEquals(5, 4);
		assertFalse(3 > 4);
		assertTrue(2 > 1);
		assertSame(Integer.valueOf(2), Integer.valueOf(2));
		assertNotSame(Integer.valueOf(2), new Integer(2));
		assertNull(null);
		assertNotNull("String");
		// http://hamcrest.org/JavaHamcrest/
		assertThat(Integer.valueOf(2), new IsInstanceOf(Integer.class));
		assertThat("val",
				AnyOf.anyOf(new IsEqual<String>("Val"), new IsEqual<String>("val"), new IsEqual<String>("VAL")));
		fail("Should be unreachable code");
	}
}

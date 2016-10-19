package net.devstudy.jse.lection12_junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ExceptionsTest {
	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	private void throwException() {
		try {
			System.out.println(2 / 0);
		} catch (ArithmeticException e) {
			throw new IllegalArgumentException("Invalid operation", e);
		}
	}

	@Test
	public void test1ExpectedException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(new IsEqual<String>("Invalid operation"));
		thrown.expectCause(new CauseMatcher(ArithmeticException.class, "/ by zero"));
		throwException();
	}

	@Test
	public void test2ExpectedException() {
		try {
			throwException();
			fail("IllegalArgumentException should be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid operation", e.getMessage());
			assertEquals(ArithmeticException.class, e.getCause().getClass());
			assertEquals("/ by zero", e.getCause().getMessage());
		}
	}

	private static class CauseMatcher extends TypeSafeMatcher<Throwable> {
		private final Class<? extends Throwable> type;
		private final String expectedMessage;

		public CauseMatcher(Class<? extends Throwable> type, String expectedMessage) {
			this.type = type;
			this.expectedMessage = expectedMessage;
		}

		@Override
		protected boolean matchesSafely(Throwable item) {
			return item.getClass().isAssignableFrom(type) && item.getMessage().contains(expectedMessage);
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("expects type ").appendValue(type).appendText(" and a message ")
					.appendValue(expectedMessage);
		}
	}
}

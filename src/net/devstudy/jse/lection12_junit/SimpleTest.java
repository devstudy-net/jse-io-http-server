package net.devstudy.jse.lection12_junit;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class SimpleTest {

	public static void main(String[] args) {
		int actual = 2 + 2;
		int expected = 4;
		if (actual != expected) {
			throw new AssertionError("expected: <" + expected + "> but was: <" + actual + ">");
		}
	}
}

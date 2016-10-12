package net.devstudy.jse.lection03_classes_static;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class StaticTest {
	private static int count = 0;
	static {
		count = 9;
	}
	private int value = 0;

	public StaticTest() {
		value = 9;
	}

	{
		value = 8;
	}

	public static void main(String[] args) {
		System.out.println(count);
		count = 1;
		System.out.println(count);
		// -----------------------------------------
		StaticTest s = new StaticTest();
		System.out.println(s.value);
		s.value = 1;
		System.out.println(s.value);
	}
}

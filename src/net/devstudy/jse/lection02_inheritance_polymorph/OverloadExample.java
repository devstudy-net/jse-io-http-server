package net.devstudy.jse.lection02_inheritance_polymorph;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class OverloadExample {

	public static void main(String[] args) {
		System.out.println(sum(1, 1));
		System.out.println(sum(1.2, 1));
	}

	private static int sum(int a, int b) {
		return a + b;
	}

	private static double sum(double a, double b) {
		return a + b;
	}
}

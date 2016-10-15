package net.devstudy.jse.lection05_exceptions;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class TryCatch1Example {

	public static void main(String[] args) {
		try {
			int[] a = null;
			System.out.println(a[0]);
		} catch (ArithmeticException e) {
			e.printStackTrace(System.out);
		} finally {
			System.out.println("finnaly");
		}
		System.out.println("After");
	}

}

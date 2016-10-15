package net.devstudy.jse.lection05_exceptions;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class TryCatchExample {

	public static void main(String[] args) {
		System.out.println("Start");
		try {
			method1();
			System.out.println("After method1");
		} catch (IllegalAccessException e) {
			System.out.println("Catch exception: " + e.getMessage());
			e.printStackTrace();
		} catch (RuntimeException e) {
			System.out.println("Catch exception: " + e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		System.out.println("End");
	}

	private static void method1() throws IllegalAccessException {
		method2();
		System.out.println("After method2");
	}

	private static void method2() throws IllegalAccessException {
		method3();
		System.out.println("After method3");
	}

	private static void method3() throws IllegalAccessException {
		System.out.println("Before exception");
		/*if(System.currentTimeMillis() > 0) {
			throw new IllegalAccessException("Test exception");
		}*/
		System.out.println(2 / 0);
		System.out.println("After exception");
	}

}

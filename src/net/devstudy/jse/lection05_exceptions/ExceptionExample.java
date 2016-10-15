package net.devstudy.jse.lection05_exceptions;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ExceptionExample {

	public static void main(String[] args) {
		int a = 2;
		int b = 0;
		throw new ArithmeticException("/ by zero");
	}

}

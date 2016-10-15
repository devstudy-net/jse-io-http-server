package net.devstudy.jse.lection05_exceptions;

import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class SomeUsefulExamples {

	public static void main(String[] args) {
		ret();
		System.out.println("end");
	}

	private static void ret() {
		try {
			new FileInputStream("QQQQQQQQQQQQQQQQ");
		} catch (IOException e) {
			throw new IllegalArgumentException("Invalid argument", e);
		}
	}
}

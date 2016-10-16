package net.devstudy.jse.lection08_io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ChangeSystemOut {

	public static void main(String[] args) throws IOException {
		PrintStream consoleOut = System.out;
		System.setOut(new PrintStream(new FileOutputStream("out.txt")));
		System.out.println("Hello java!");
		System.out.println("Hello world!");
		System.out.flush();
		System.out.close();
		System.setOut(consoleOut);
		System.out.println("Hello java!!!!!!!!");
		System.out.println("Hello world!!!!!!!");
	}

}

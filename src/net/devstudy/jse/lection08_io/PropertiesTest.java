package net.devstudy.jse.lection08_io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class PropertiesTest {

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		try (InputStream in = PropertiesTest.class.getClassLoader().getResourceAsStream("server.properties")) {
			prop.load(in);
		}
		System.out.println("wepapp.static.dir.root=" + prop.getProperty("wepapp.static.dir.root"));
		System.out.println("wepapp.static.notfound=" + prop.getProperty("wepapp.static.notfound"));
	}

}

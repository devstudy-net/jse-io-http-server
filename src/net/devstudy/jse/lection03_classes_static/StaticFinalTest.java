package net.devstudy.jse.lection03_classes_static;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class StaticFinalTest {
	public static final String OK = "OK";
	public static final long CLASS_LOADED;
	static {
		long t = System.currentTimeMillis();
		for(int i=0;i<10;i++){
			t += i;
		}
		CLASS_LOADED = t;
	}
	public final String ok = "OK";
	public final long objectCreated;

	public StaticFinalTest() {
		objectCreated = System.currentTimeMillis();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new Date(CLASS_LOADED));
		TimeUnit.SECONDS.sleep(1);
		System.out.println(new Date(new StaticFinalTest().objectCreated));
	}
}

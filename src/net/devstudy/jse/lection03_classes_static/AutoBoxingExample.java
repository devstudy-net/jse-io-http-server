package net.devstudy.jse.lection03_classes_static;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class AutoBoxingExample {

	public static void main(String[] args) {
		Object o = Integer.valueOf(5);
		System.out.println(o.getClass());
		int d = ((Integer) o);
		System.out.println(d);
	}

}

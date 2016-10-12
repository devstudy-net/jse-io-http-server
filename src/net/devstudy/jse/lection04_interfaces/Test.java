package net.devstudy.jse.lection04_interfaces;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class Test {

	public static void main(String[] args) {
		Object o1 = new Object();
		Object o2 = new Object();
		System.out.println(System.identityHashCode(o1) > System.identityHashCode(o2));
	}

}

package net.devstudy.jse.lection01_classes_objects.home;

import java.util.Arrays;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DynaArrayTest {

	public static void main(String[] args) {
		DynaArray list = new DynaArray();

		for (int i = 0; i < 5; i++) {
			list.add(i);
		}

		System.out.println("size = " + list.size());
		System.out.println("list[3] = " + list.get(3));
		System.out.println(Arrays.toString(list.toArray()));

		list.remove(0);
		list.remove(3);
		System.out.println(Arrays.toString(list.toArray()));

		list.add(4);
		list.add(5);
		System.out.println(Arrays.toString(list.toArray()));
		list.remove(2);
		System.out.println(Arrays.toString(list.toArray()));
	}
}

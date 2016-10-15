package net.devstudy.jse.lection05_exceptions;

import net.devstudy.jse.lection03_classes_static.home.DynaArray;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DynaArrayTest {

	public static void main(String[] args) {
		DynaArray<Integer> array = new DynaArray<>();
		array.add(0);
		array.add(null);
		int index = 100;
		
		System.out.println(array.size());
		
		System.out.println(array.get(1));
		System.out.println(array.get(index));
	}

}

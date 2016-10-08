package net.devstudy.jse.lection01_classes_objects;

import java.util.Arrays;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DynaArrayTest {

	public static void main(String[] args) {
		DynaArray arr = new DynaArray();
		for (int i = 0; i < 23; i++) {
			arr.add(i);
		}
		System.out.println(Arrays.toString(arr.toArray()));
	}

}

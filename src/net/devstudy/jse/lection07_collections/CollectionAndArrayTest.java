package net.devstudy.jse.lection07_collections;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class CollectionAndArrayTest {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		System.out.println(list);
		Integer[] array1 = list.toArray(new Integer[] {});
		System.out.println(Arrays.toString(array1));
		Object[] array2 = list.toArray();
		System.out.println(Arrays.toString(array2));
		Set<Integer> set = new LinkedHashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }));
		System.out.println(set);
	}

}

package net.devstudy.jse.lection07_collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class IteratorTest {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		display(list.iterator());
		Set<Integer> set = new LinkedHashSet<>(Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }));
		display(set.iterator());
		Map<Integer, String> map = new HashMap<>();
		for (int i = 1; i <= 10; i++) {
			map.put(i, String.valueOf(i));
		}
		display(map.entrySet().iterator());
	}

	private static void display(Iterator<?> iterator) {
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
			//iterator.remove();
		}
		System.out.println();
	}

}

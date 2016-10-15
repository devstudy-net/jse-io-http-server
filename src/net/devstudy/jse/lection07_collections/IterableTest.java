package net.devstudy.jse.lection07_collections;

import java.util.Iterator;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class IterableTest implements Iterable<Integer> {
	private final Integer[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < array.length;
			}

			@Override
			public Integer next() {
				return array[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("remove");
			}
		};
	}

	public static void main(String[] args) {
		IterableTest object = new IterableTest();
		for (Integer val : object) {
			System.out.print(val + " ");
		}
	}
}

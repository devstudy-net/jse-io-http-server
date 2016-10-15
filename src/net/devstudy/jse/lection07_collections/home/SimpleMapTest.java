package net.devstudy.jse.lection07_collections.home;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class SimpleMapTest {

	public static void main(String[] args) {
		SimpleMap<Integer, Integer>[] maps = new SimpleMap[] { new HashSimpleMap<>(), new TreeSimpleMap<>() };
		for (SimpleMap<Integer, Integer> map : maps) {
			map.put(7, 7);
			map.put(3, 3);
			map.put(11, 11);
			map.put(1, 1);
			map.put(5, 5);
			map.put(9, 9);
			map.put(13, 13);
			map.put(0, 0);
			map.put(2, 2);
			map.put(4, 4);
			map.put(6, 6);
			map.put(8, 8);
			map.put(10, 10);
			map.put(12, 12);
			map.put(14, 14);

			System.out.println(map.get(5));
			System.out.println(map.get(10));
			System.out.println(map.get(15));
			System.out.println(map.get(20));
		}
	}
}

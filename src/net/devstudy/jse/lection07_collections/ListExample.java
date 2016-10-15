package net.devstudy.jse.lection07_collections;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ListExample {

	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		System.out.println(list);
	}

}

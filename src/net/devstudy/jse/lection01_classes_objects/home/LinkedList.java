package net.devstudy.jse.lection01_classes_objects.home;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class LinkedList {
	private Item first;
	private Item last;
	private int size;

	public void add(int element) {
		if(size == 0) {
			first = last = new Item(element);
			size++;
		} else {
			Item item = new Item(element);
			last.setNext(item);
			item.setPrevious(last);
			last = item;
			size++;
		}
	}

	public int get(int index) {
		return 0;
	}

	public int remove(int index) {
		return 0;
	}

	public int size() {
		return size;
	}

	public void clear() {
		size = 0;
		first = last = null;
	}

	public int[] toArray() {
		return null;
	}
}

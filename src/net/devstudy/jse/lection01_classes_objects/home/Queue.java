package net.devstudy.jse.lection01_classes_objects.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class Queue {
	private LinkedList list = new LinkedList();

	public void add(int element) {
		list.add(element);
	}

	public int get() {
		return list.remove(0);
	}

	public int size() {
		return list.size();
	}
}

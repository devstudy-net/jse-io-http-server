package net.devstudy.jse.lection02_inheritance_polymorph;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class LinkedList extends DataSet{
	private Item first;
	private Item last;

	@Override
	public void add(int element) {
		Item item = new Item(element);
		if (size == 0) {
			first = last = item;
		} else {
			last.setNext(item);
			item.setPrevious(last);
			last = item;
		}
		size++;
	}

	@Override
	public int get(int index) {
		Item current = findItem(index);
		return current != null ? current.getValue() : 0;
	}

	private Item findItem(int index) {
		int i = 0;
		Item current = first;
		while (i < size) {
			if (i == index) {
				return current;
			} else {
				i++;
				current = current.getNext();
			}
		}
		return null;
	}

	@Override
	public int remove(int index) {
		Item current = findItem(index);
		return current != null ? removeCurrent(current) : 0;
	}

	private int removeCurrent(Item current) {
		Item prev = current.getPrevious();
		Item next = current.getNext();
		if (next != null) {
			removeCurrentFromNext(next, prev);
		}
		if (prev != null) {
			removeCurrentFromPrevious(next, prev);
		}
		size--;
		if (size == 0) {
			first = last = null;
		}
		return current.getValue();
	}
	
	private void removeCurrentFromNext(Item next, Item prev){
		next.setPrevious(prev);
		if (prev == null) {
			first = next;
		}
	}
	
	private void removeCurrentFromPrevious(Item next, Item prev){
		prev.setNext(next);
		if (next == null) {
			last = prev;
		}
	}

	@Override
	public void clear() {
		super.clear();
		first = last = null;
	}

	@Override
	public int[] toArray() {
		int[] array = new int[size];
		int i = 0;
		Item current = first;
		while (i < size) {
			array[i++] = current.getValue();
			current = current.getNext();
		}
		return array;
	}
}

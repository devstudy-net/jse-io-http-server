package net.devstudy.jse.lection03_classes_static.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
@SuppressWarnings("unchecked")
public class LinkedList<E> extends DataSet<E>{
	private Item<E> first;
	private Item<E> last;

	@Override
	public void add(E element) {
		Item<E> item = new Item<>(element);
		if (size == 0) {
			first = last = item;
		} else {
			last.setNext(item);
			item.setPrevious(last);
			last = item;
		}
		size++;
	}
	
	protected void addLinkedList(LinkedList<E> list) {
		this.size += list.size();
		if (this.first == null) {
			this.first = list.first;
		}
		if (this.last != null) {
			this.last.setNext(list.first);
			list.first.setPrevious(this.last);
		}
		this.last = list.last;
	}

	@Override
	public E get(int index) {
		Item<E> current = findItem(index);
		return current != null ? current.getValue() : null;
	}

	private Item<E> findItem(int index) {
		int i = 0;
		Item<E> current = first;
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
	public E remove(int index) {
		Item<E> current = findItem(index);
		return current != null ? removeCurrent(current) : null;
	}

	private E removeCurrent(Item<E> current) {
		Item<E> prev = current.getPrevious();
		Item<E> next = current.getNext();
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
	
	private void removeCurrentFromNext(Item<E> next, Item<E> prev){
		next.setPrevious(prev);
		if (prev == null) {
			first = next;
		}
	}
	
	private void removeCurrentFromPrevious(Item<E> next, Item<E> prev){
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
	public Object[] toArray() {
		E[] array = (E[]) new Object[size];
		int i = 0;
		Item<E> current = first;
		while (i < size) {
			array[i++] = current.getValue();
			current = current.getNext();
		}
		return array;
	}
	
	private static class Item<I> {
		private Item<I> next;
		private I value;
		private Item<I> previous;

		Item(I value) {
			this.value = value;
		}

		Item<I> getNext() {
			return next;
		}

		void setNext(Item<I> next) {
			this.next = next;
		}

		I getValue() {
			return value;
		}

		Item<I> getPrevious() {
			return previous;
		}

		void setPrevious(Item<I> previous) {
			this.previous = previous;
		}
	}
}

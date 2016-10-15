package net.devstudy.jse.lection05_exceptions.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class LinkedList<T> extends DataSet<T> {
	private Item<T> first;
	private Item<T> last;

	public LinkedList() {
		super();
	}
	@Override
	public void add(T element) {
		Item<T> item = new Item<>(element);
		if(last == null) {
			first = last = item;
		} else {
			last.setNext(item);
			item.setPrevious(last);
			last = item;
		}
		size++;
	}
	
	protected void addLinkedList(LinkedList<T> list) {
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
	public T get(int index) {
		int i = 0;
		Item<T> current = first;
		while(i < size) {
			if(i == index) {
				return current.getValue();
			} else {
				i++;
				current = current.getNext();
			}
		}
		throw new IndexOutOfBoundsException("Index should be between 0 and "+(size() -1)+". Index to get: "+index);
	}

	@Override
	public T remove(int index) {
		int i = 0;
		Item<T> current = first;
		while(i < size) {
			if(i == index) {
				Item<T> prev = current.getPrevious();
				Item<T> next = current.getNext();
				if(next != null) {
					next.setPrevious(prev);
					if(prev == null) {
						first = next;
					}
				} 
				if(prev != null) {
					prev.setNext(next);
					if(next == null) {
						last = prev;
					}
				} 
				size--;
				if(size == 0) {
					first = last = null;
				}
				return current.getValue();
			} else {
				i++;
				current = current.getNext();
			}
		}
		throw new IndexOutOfBoundsException("Index should be between 0 and "+(size() -1)+". Index to remove: "+index);
	}

	@Override
	public void clear() {
		super.clear();
		first = last = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size];
		int i = 0;
		Item<T> current = first;
		while (i < size) {
			array[i++] = current.getValue();
			current = current.getNext();
		}
		return array;
	}
	
	/**
	 * 
	 * 
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	private static class Item<E> {
		private Item<E> next;
		private E value;
		private Item<E> previous;

		Item(E value) {
			this.value = value;
		}

		Item<E> getNext() {
			return next;
		}

		void setNext(Item<E> next) {
			this.next = next;
		}

		E getValue() {
			return value;
		}

		Item<E> getPrevious() {
			return previous;
		}

		void setPrevious(Item<E> previous) {
			this.previous = previous;
		}
	}
}

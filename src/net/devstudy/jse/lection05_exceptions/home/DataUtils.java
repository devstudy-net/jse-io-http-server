package net.devstudy.jse.lection05_exceptions.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
@SuppressWarnings("unchecked")
public class DataUtils {
	public static <T> DynaArray<T> newDynaArray() {
		return new DynaArray<T>();
	}

	public static <T> DataSet<T> newImmutableDataSet(final DataSet<T> dataSet) {
		return newImmutableDataSet(dataSet.toArray());
	}

	public static <T> DataSet<T> newImmutableDataSet(final T[] array) {
		return new DataSet<T>() {
			{
				size = array.length;
			}

			@Override
			public void add(T element) {
				throw new UnsupportedOperationException("ImmutableDataSet class does not support add method");
			}

			@Override
			public T get(int index) {
				if (index >= 0 && index < array.length) {
					return array[index];
				}
				throw new IndexOutOfBoundsException("Index should be between 0 and "+(size() -1)+". Index to get: "+index);
			}

			@Override
			public T remove(int index) {
				throw new UnsupportedOperationException("ImmutableDataSet class does not support remove method");
			}

			@Override
			public void clear() {
				throw new UnsupportedOperationException("ImmutableDataSet class does not support clear method");
			}

			@Override
			public T[] toArray() {
				return array;
			}
		};
	}

	public static <T> DataSet<T> newImmutableDataSet(final T element, final T... elements) {
		T[] array = (T[]) new Object[elements.length + 1];
		array[0] = element;
		System.arraycopy(elements, 0, array, 1, elements.length);
		return newImmutableDataSet(array);
	}

	public static <T> LinkedList<T> newLinkedListWithDuplicates(final DataSet<T>... dataSets) {
		LinkedList<T> result = new LinkedList<>();
		for (DataSet<T> dataSet : dataSets) {
			if (dataSet instanceof LinkedList) {
				result.addLinkedList((LinkedList<T>) dataSet);
			} else {
				for (int i = 0; i < dataSet.size(); i++) {
					result.add(dataSet.get(i));
				}
			}
		}
		return result;
	}

	public static <T> DataSet<T> newDataSetWithoutDuplicates(final DataSet<T>... dataSets) {
		DataSetWithoutDuplicates<T> result = new DataSetWithoutDuplicates<T>();
		for (DataSet<T> dataSet : dataSets) {
			result.addDataSet(dataSet);
		}
		return result;
	}

	/**
	 * 
	 * 
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	private static class DataSetWithoutDuplicates<T> extends DynaArray<T> {
		private void addDataSet(DataSet<T> dataSet) {
			for (int i = 0; i < dataSet.size(); i++) {
				add(dataSet.get(i));
			}
		}

		@Override
		public void add(T element) {
			boolean found = false;
			for (int i = 0; i < size(); i++) {
				if (get(i).equals(element)) {
					found = true;
					break;
				}
			}
			if (!found) {
				super.add(element);
			}
		}
	}

	public static <T> Queue<T> newQueue(final DataSet<T> dataSet) {
		if (dataSet instanceof LinkedList) {
			return new Queue<>(dataSet);
		} else {
			Queue<T> q = new Queue<>();
			for (int i = 0; i < dataSet.size(); i++) {
				q.add(dataSet.get(i));
			}
			return q;
		}
	}

	public static <T> Stack<T> newStack(final DataSet<T> dataSet) {
		if (dataSet instanceof DynaArray) {
			return new Stack<>(dataSet);
		} else {
			return new Stack<>(new DynaArray<>(dataSet.toArray()));
		}
	}

	public static <T> Queue<T> newQueue(final T[] array) {
		Queue<T> q = new Queue<>();
		for (int i = 0; i < array.length; i++) {
			q.add(array[i]);
		}
		return q;
	}

	public static <T> Stack<T> newStack(final T[] array) {
		return new Stack<>(new DynaArray<>(array));
	}

	private DataUtils() {
	}
}

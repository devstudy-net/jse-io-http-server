package net.devstudy.jse.lection03_classes_static.home;

import java.util.Arrays;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public abstract class DataSet<T> {
	protected int size;

	public DataSet() {
		size = 0;
	}

	public final int size() {
		return size;
	}

	public void clear() {
		size = 0;
	}

	public abstract void add(T element);

	public abstract T get(int index);

	public abstract T remove(int index);

	public abstract Object[] toArray();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + size;
		result = prime * result + Arrays.hashCode(toArray());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DataSet other = (DataSet) obj;
		if (size != other.size) {
			return false;
		}
		if (!Arrays.equals(toArray(), other.toArray())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}
}

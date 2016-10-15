package net.devstudy.jse.lection05_exceptions.home;

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

	public abstract T remove(int index) ;

	public abstract T[] toArray();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + size;
		result = prime * result + Arrays.hashCode(toArray());
		return result;
	}

	@SuppressWarnings("unchecked")
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
		DataSet<T> other = (DataSet<T>) obj;
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

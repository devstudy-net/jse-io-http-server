package net.devstudy.jse.lection02_inheritance_polymorph;

import java.util.Arrays;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DataSet {
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

	public void add(int element) {
	}

	public int get(int index) {
		return 0;
	}

	public int remove(int index) {
		return 0;
	}

	public int[] toArray() {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + size;
		result = prime * result + Arrays.hashCode(toArray());
		return result;
	}

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

package net.devstudy.jse.lection02_inheritance_polymorph.home;

import java.util.Arrays;

import net.devstudy.jse.lection02_inheritance_polymorph.DataSet;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DataStructure {
	protected final DataSet dataSet;

	public DataStructure(DataSet dataSet) {
		super();
		this.dataSet = dataSet;
	}

	public void add(int element) {
		dataSet.add(element);
	}

	protected int getCurrentIndex() {
		return 0;
	}

	public int get() {
		return dataSet.remove(getCurrentIndex());
	}

	public int peek() {
		return dataSet.get(getCurrentIndex());
	}

	public int size() {
		return dataSet.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	protected int[] toArray() {
		return dataSet.toArray();
	}

	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		DataStructure other = (DataStructure) obj;
		if (!Arrays.equals(toArray(), other.toArray())) {
			return false;
		}
		return true;
	}
}

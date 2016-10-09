package net.devstudy.jse.lection02_inheritance_polymorph;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public final class ImmutableDataSet extends DataSet {
	private final DataSet originalDataSet;

	public ImmutableDataSet(DataSet originalDataSet) {
		super();
		this.originalDataSet = originalDataSet;
		this.size = originalDataSet.size();
	}

	@Override
	public void add(int element) {
		// do nothing
	}

	@Override
	public int remove(int index) {
		// do nothing
		return 0;
	}

	@Override
	public void clear() {
		// do nothing
	}

	@Override
	public int get(int index) {
		return originalDataSet.get(index);
	}

	@Override
	public int[] toArray() {
		return originalDataSet.toArray();
	}

	@Override
	public String toString() {
		return originalDataSet.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return originalDataSet.equals(obj);
	}

	@Override
	public int hashCode() {
		return originalDataSet.hashCode();
	}
}

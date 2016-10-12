package net.devstudy.jse.lection02_inheritance_polymorph.home;

import net.devstudy.jse.lection02_inheritance_polymorph.DynaArray;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class Stack extends DataStructure {

	public Stack() {
		super(new DynaArray());
	}

	@Override
	protected final int getCurrentIndex() {
		return size() - 1;
	}

	@Override
	protected int[] toArray() {
		int[] array = new int[dataSet.size()];
		for (int i = 0; i < dataSet.size(); i++) {
			array[i] = dataSet.get(dataSet.size() - 1 - i);
		}
		return array;
	}
}

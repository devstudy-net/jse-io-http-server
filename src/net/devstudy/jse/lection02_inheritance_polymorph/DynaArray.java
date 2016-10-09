package net.devstudy.jse.lection02_inheritance_polymorph;

import java.util.Arrays;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DynaArray extends DataSet{
	private int[] array;

	public DynaArray() {
		array = new int[10];
	}

	@Override
	public void add(int element) {
		if (size == array.length) {
			int[] temp = array;
			array = new int[temp.length * 2];
			for (int i = 0; i < temp.length; i++) {
				array[i] = temp[i];
			}
		}
		array[size++] = element;
	}
	
	@Override
	public int remove(int index) {
		if (index < size) {
			int value = array[index];
			for (int i = index; i < size - 1; i++) {
				array[i] = array[i + 1];
			}
			array[--size] = 0;
			return value;
		}
		return 0;
	}

	@Override
	public int get(int index) {
		if(index < size) {
			return array[index];
		} else {
			return 0;
		}
	}
	
	@Override
	public void clear() {
		super.clear();
		array = new int[10];
	}
	
	@Override
	public int[] toArray() {
		return Arrays.copyOf(array, size);
	}
}

package net.devstudy.jse.lection07_collections;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class HashCodeTest {

	private int value;

	public HashCodeTest(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		HashCodeTest other = (HashCodeTest) obj;
		if (value != other.value) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Map<HashCodeTest, Object> map = new HashMap<>();
		HashCodeTest key = new HashCodeTest(5);
		map.put(key, new Object());
		System.out.println("before=" + map.containsKey(key));
		key.setValue(23);
		System.out.println("after=" + map.containsKey(key));
		System.out.println(map.size());
	}

}

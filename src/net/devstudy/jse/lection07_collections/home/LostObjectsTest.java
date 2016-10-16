package net.devstudy.jse.lection07_collections.home;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class LostObjectsTest {

	private int value;

	public LostObjectsTest(int value) {
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
		LostObjectsTest other = (LostObjectsTest) obj;
		if (value != other.value) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		SimpleMap<LostObjectsTest, Integer> map = new HashSimpleMap<>();
		LostObjectsTest key = new LostObjectsTest(5);
		map.put(key, 2);
		System.out.println(map.get(key));
		key.setValue(3);
		System.out.println(map.get(key));
	}

}

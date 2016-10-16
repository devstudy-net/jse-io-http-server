package net.devstudy.jse.lection07_collections.home;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
@SuppressWarnings("unchecked")
public class TreeSimpleMap<K, V> implements SimpleMap<K, V> {

	private Item<K, V> root;
	
	@Override
	public V put(K key, V value) {
		if(root == null) {
			root = new Item<K, V>(key, value);
			return null;
		} else {
			Item<K, V> current = root;
			while(true) {
				int compareResult = ((Comparable<K>)key).compareTo(current.key);
				if(compareResult == 0) {
					V oldValue = current.value;
					current.value = value;
					return oldValue;
				} else if (compareResult < 0) {
					if(current.left == null) {
						current.left = new Item<K, V>(key, value);
						return null;
					}
					current = current.left;
				} else {
					if(current.right == null) {
						current.right = new Item<K, V>(key, value);
						return null;
					}
					current = current.right;
				}
			}
		}
	}

	@Override
	public V get(K key) {
		Item<K, V> current = root;
		while(current != null) {
			int compareResult = ((Comparable<K>)key).compareTo(current.key);
			if(compareResult == 0) {
				return current.value;
			} else if (compareResult < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return null;
	}
	
	/**
	 *
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	private static class Item<K, V> {
		private final K key;
		private V value;
		private Item<K, V> left;
		private Item<K, V> right;
		Item(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
		@Override
		public String toString() {
			return String.format("%s=%s", key, value);
		}
	}
}

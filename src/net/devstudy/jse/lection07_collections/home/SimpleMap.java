package net.devstudy.jse.lection07_collections.home;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface SimpleMap<K, V> {

	V put(K key, V value);

	V get(K key);
}

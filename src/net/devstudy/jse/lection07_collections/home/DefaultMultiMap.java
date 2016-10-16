package net.devstudy.jse.lection07_collections.home;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
@SuppressWarnings("unchecked")
public class DefaultMultiMap<K, V> implements MultiMap<K, V> {
	private final Map<K, Collection<V>> map;
	
	private DefaultMultiMap(Map<K, Collection<V>> map) {
		super();
		this.map = map;
	}
	
	public static <K, V> DefaultMultiMap<K, V> newMultiHashMap(){
		return new DefaultMultiMap<>(new HashMap<K, Collection<V>>());
	}
	
	public static <K, V> DefaultMultiMap<K, V> newMultiLinkedHashMap(){
		return new DefaultMultiMap<>(new LinkedHashMap<K, Collection<V>>());
	}
	
	public static <K, V> DefaultMultiMap<K, V> newMultiTreeMap(){
		return new DefaultMultiMap<>(new TreeMap<K, Collection<V>>());
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(K key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(V value) {
		for(Entry<K, Collection<V>> entry : entrySet()) {
			if(entry.getValue().contains(value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public void put(K key, V value) {
		Collection<V> list = map.get(key);
		if(list == null) {
			list = new LinkedList<>();
			map.put(key, list);
		}
		list.add(value);
	}

	@Override
	public void put(Map<K, V> map) {
		for(Map.Entry<K, V> entry : map.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public int countValues(K key) {
		Collection<V> list = map.get(key);
		return list == null ? 0 : list.size();
	}

	@Override
	public Collection<V> get(K key) {
		return map.get(key);
	}

	@Override
	public Iterator<V> valuesIterator(K key) {
		Collection<V> list = map.get(key);
		return list == null ? (Iterator<V>)Collections.emptyIterator() : list.iterator();
	}

	@Override
	public Collection<V> remove(K key) {
		return map.remove(key);
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public Set<Entry<K, Collection<V>>> entrySet() {
		return map.entrySet();
	}

	@Override
	public Collection<V> values() {
		Collection<V> result = new LinkedList<>();
		for(Entry<K, Collection<V>> entry : entrySet()) {
			result.addAll(entry.getValue());
		}
		return Collections.unmodifiableCollection(result);
	}

	@Override
	public String toString() {
		return map.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
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
		DefaultMultiMap other = (DefaultMultiMap) obj;
		if (map == null) {
			if (other.map != null) {
				return false;
			}
		} else if (!map.equals(other.map)) {
			return false;
		}
		return true;
	}
}

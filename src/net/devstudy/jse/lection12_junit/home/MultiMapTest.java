package net.devstudy.jse.lection12_junit.home;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import net.devstudy.jse.lection07_collections.home.DefaultMultiMap;
import net.devstudy.jse.lection07_collections.home.MultiMap;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class MultiMapTest {
	private MultiMap<Integer, Integer> newMultiMap(int... params) {
		MultiMap<Integer, Integer> multiMap = DefaultMultiMap.newMultiLinkedHashMap();
		for (int i = 0; i < params.length; i += 2) {
			multiMap.put(params[i], params[i + 1]);
		}
		return multiMap;
	}

	@Test
	public void testSizeEmpty() {
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertEquals(0, multiMap.size());
	}

	@Test
	public void testSizeWithoutDuplicates() {
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 2, 2);
		assertEquals(2, multiMap.size());
	}

	@Test
	public void testSizeWithDuplicates() {
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 1, 1);
		assertEquals(1, multiMap.size());
	}

	@Test
	public void testIsEmpty() {
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertTrue(multiMap.isEmpty());
	}

	@Test
	public void testIsNotEmpty() {
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1);
		assertFalse(multiMap.isEmpty());
	}

	@Test
	public void testNotContainsKey() {
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertFalse(multiMap.containsKey(1));
	}

	@Test
	public void testContainsKeyWithoutDuplicates() {
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1);
		assertTrue(multiMap.containsKey(1));
	}

	@Test
	public void testContainsKeyWithDuplicates() {
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 1, 1);
		assertTrue(multiMap.containsKey(1));
	}

	@Test
	public void testNotContainsValue() {
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertFalse(multiMap.containsValue(1));
	}

	@Test
	public void testContainsValueWithoutDuplicates() {
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1);
		assertTrue(multiMap.containsValue(1));
	}

	@Test
	public void testContainsValueWithDuplicates() {
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 1, 1);
		assertTrue(multiMap.containsValue(1));
	}

	@Test
	public void testClear() {
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 2, 2);
		multiMap.clear();
		assertTrue(multiMap.isEmpty());
	}
	
	@Test
	public void testPutWithoutDuplicates() {
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		multiMap.put(1, 1);
		assertArrayEquals(new Integer[]{1}, multiMap.get(1).toArray());
	}
	
	@Test
	public void testPutWithDuplicates() {
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		multiMap.put(1, 1);
		multiMap.put(1, 3);
		assertArrayEquals(new Integer[]{1, 3}, multiMap.get(1).toArray());
	}
	
	@Test
	public void testPutMapWithoutDuplicates() {
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		multiMap.put(new HashMap<Integer, Integer>(){
			private static final long serialVersionUID = 2295999220238719720L;
			{
				put(2, 2);
			}
		});
		assertArrayEquals(new Integer[]{2}, multiMap.get(2).toArray());
	}
	
	@Test
	public void testPutMapWithDuplicates() {
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		multiMap.put(new HashMap<Integer, Integer>(){
			private static final long serialVersionUID = -8922724803102218407L;
			{
				put(2, 2);
			}
		});
		multiMap.put(new HashMap<Integer, Integer>(){
			private static final long serialVersionUID = -3438973489426388804L;
			{
				put(2, 8);
			}
		});
		assertArrayEquals(new Integer[]{2, 8}, multiMap.get(2).toArray());
	}
	
	@Test
	public void testCountValuesForNotFoundKey(){
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertEquals(0, multiMap.countValues(1));
	}
	
	@Test
	public void testCountValuesForKeyWithoutDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1);
		assertEquals(1, multiMap.countValues(1));
	}
	
	@Test
	public void testCountValuesForKeyWithDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 1, 2, 1, 3);
		assertEquals(3, multiMap.countValues(1));
	}
	
	@Test
	public void testGetForNotFoundKey(){
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertNull(multiMap.get(1));
	}
	
	@Test
	public void testGetForKeyWithoutDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1);
		assertEquals(Collections.singletonList(1), multiMap.get(1));
	}
	
	@Test
	public void testGetForKeyWithDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 1, 2, 1, 3);
		assertEquals(Arrays.asList(new Integer[]{1, 2, 3}), multiMap.get(1));
	}
	
	@Test
	public void testValuesIteratorForNotFoundKey(){
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		Iterator<Integer> it = multiMap.valuesIterator(1);
		assertNotNull(it);
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testValuesIteratorForKeyWithoutDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1);
		Iterator<Integer> it = multiMap.valuesIterator(1);
		assertEquals(1, it.next().intValue());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testValuesIteratorForKeyWithDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 1, 2, 1, 3);
		Iterator<Integer> it = multiMap.valuesIterator(1);
		assertEquals(1, it.next().intValue());
		assertEquals(2, it.next().intValue());
		assertEquals(3, it.next().intValue());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testRemoveForNotFoundKey(){
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertNull(multiMap.remove(1));
	}
	
	@Test
	public void testRemoveForKeyWithoutDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1);
		assertEquals(Collections.singletonList(1), multiMap.remove(1));
		assertNull(multiMap.get(1));
	}
	
	@Test
	public void testRemoveForKeyWithDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 1, 2, 1, 3);
		assertEquals(Arrays.asList(new Integer[]{1, 2, 3}), multiMap.remove(1));
		assertNull(multiMap.get(1));
	}
	
	@Test
	public void testKeySetEmpty(){
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertNotNull(multiMap.keySet());
		assertTrue(multiMap.keySet().isEmpty());
	}
	
	@Test
	public void testKeySetWithoutDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1);
		assertEquals(Collections.singleton(1), multiMap.keySet());
	}
	
	@Test
	public void testKeySetWithDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 1, 2, 1, 3);
		assertEquals(Collections.singleton(1), multiMap.keySet());
	}
	
	@Test
	public void testEntrySetEmpty(){
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertNotNull(multiMap.entrySet());
		assertTrue(multiMap.entrySet().isEmpty());
	}
	
	@Test
	public void testEntrySetWithoutDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1);
		Set<Map.Entry<Integer, Collection<Integer>>> entrySet = multiMap.entrySet();
		assertEquals(1, entrySet.size());
		assertEquals(1, entrySet.iterator().next().getKey().intValue());
		assertArrayEquals(new Integer[]{1}, entrySet.iterator().next().getValue().toArray());
	}
	
	@Test
	public void testEntrySetWithDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 1, 2, 1, 3);
		Set<Map.Entry<Integer, Collection<Integer>>> entrySet = multiMap.entrySet();
		assertEquals(1, entrySet.size());
		assertEquals(1, entrySet.iterator().next().getKey().intValue());
		assertArrayEquals(new Integer[]{1, 2, 3}, entrySet.iterator().next().getValue().toArray());
	}
	
	@Test
	public void testValuesEmpty(){
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertNotNull(multiMap.values());
		assertTrue(multiMap.values().isEmpty());
	}
	
	@Test
	public void testValuesWithoutDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 2, 2);
		assertArrayEquals(new Integer[]{1, 2}, multiMap.values().toArray());
	}
	
	@Test
	public void testValuesWithDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 2, 2, 1, 3, 2, 4);
		assertArrayEquals(new Integer[]{1, 3, 2, 4}, multiMap.values().toArray());
	}
	
	@Test
	public void testToStringEmpty(){
		MultiMap<Integer, Integer> multiMap = newMultiMap();
		assertEquals("{}", multiMap.toString());
	}
	
	@Test
	public void testToStringWithoutDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 2, 2);
		assertEquals("{1=[1], 2=[2]}", multiMap.toString());
	}
	
	@Test
	public void testToStringWithDuplicates(){
		MultiMap<Integer, Integer> multiMap = newMultiMap(1, 1, 2, 2, 1, 3, 2, 4);
		assertEquals("{1=[1, 3], 2=[2, 4]}", multiMap.toString());
	}
	
	@Test
	public void testEqualsAndHashCodeEmpty(){
		MultiMap<Integer, Integer> first = newMultiMap();
		MultiMap<Integer, Integer> second = newMultiMap();
		assertTrue(first.equals(second));
		assertTrue(first.hashCode() == second.hashCode());
	}
	
	@Test
	public void testEqualsAndHashCodeWithoutDuplicates(){
		MultiMap<Integer, Integer> first = newMultiMap(1, 1);
		MultiMap<Integer, Integer> second = newMultiMap(1, 1);
		assertTrue(first.equals(second));
		assertTrue(first.hashCode() == second.hashCode());
	}
	
	@Test
	public void testEqualsAndHashCodeWithDuplicates(){
		MultiMap<Integer, Integer> first = newMultiMap(1, 1, 1, 2);
		MultiMap<Integer, Integer> second = newMultiMap(1, 1, 1, 2);
		assertTrue(first.equals(second));
		assertTrue(first.hashCode() == second.hashCode());
	}
}

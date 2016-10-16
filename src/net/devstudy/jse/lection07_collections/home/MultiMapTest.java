package net.devstudy.jse.lection07_collections.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class MultiMapTest {
	public static void main(String[] args) {
		MultiMap<String, String> map = DefaultMultiMap.newMultiHashMap();
		
		map.put("hello", "hello-1");
		map.put("hello", "hello-2");
		map.put("java", "java-1");
		map.put("java", "java-2");
		map.put("java", "java-3");
		map.put("world", "world-1");
		
		System.out.println("size="+map.size());
		System.out.println("isEmpty="+map.isEmpty());
		System.out.println("countValues(java)="+map.countValues("java"));
		System.out.println("countValues(hi)="+map.countValues("hi"));
		System.out.println("toString="+map.toString());
		System.out.println("containsKey(java)="+map.containsKey("java"));
		System.out.println("containsKey(hi)="+map.containsKey("hi"));
		System.out.println("containsValue(java-1)="+map.containsValue("java-1"));
		System.out.println("containsValue(hello-100)="+map.containsValue("hello-100"));
		System.out.println("get(java)="+map.get("java"));
		System.out.println("get(hi)="+map.get("hi"));
		System.out.println("keySet()="+map.keySet());
		System.out.println("values="+map.values());
		System.out.println("valuesIterator(java)="+map.valuesIterator("java").hasNext());
		System.out.println("valuesIterator(hi)="+map.valuesIterator("hi").hasNext());
		System.out.println("entrySet()="+map.entrySet());
	}
}

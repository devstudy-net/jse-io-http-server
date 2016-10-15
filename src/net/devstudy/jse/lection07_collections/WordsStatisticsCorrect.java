package net.devstudy.jse.lection07_collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class WordsStatisticsCorrect {

	public static void main(String[] args) {
		String text = "Hello hello java Java JAVA world world";
		String[] words = text.split(" ");
		System.out.println(getStatisticMap(words));
	}

	private static Map<String, Integer> getStatisticMap(String[] words) {
		Map<String, Integer> wordsCountMap = new HashMap<>();
		for (String word : words) {
			String w = word.toLowerCase();
			Integer count = wordsCountMap.get(w);
			if (count == null) {
				count = 1;
			} else {
				count += 1;
			}
			wordsCountMap.put(w, count);
		}
		return sort(Collections.unmodifiableMap(wordsCountMap));
	}

	private static Map<String, Integer> sort(Map<String, Integer> original) {
		Set<Map.Entry<String, Integer>> set = new TreeSet<>(new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				int result = o1.getValue() - o2.getValue();
				if (result == 0) {
					return o1.getKey().compareTo(o2.getKey());
				} else {
					return result;
				}
			}
		});
		set.addAll(original.entrySet());
		Map<String, Integer> result = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> entry : set) {
			result.put(entry.getKey(), entry.getValue());
		}
		return Collections.unmodifiableMap(result);
	}

}

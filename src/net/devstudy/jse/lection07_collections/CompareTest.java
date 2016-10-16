package net.devstudy.jse.lection07_collections;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class CompareTest implements Comparable<CompareTest> {
	private int value;

	public CompareTest(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public int compareTo(CompareTest o) {
		return value - o.value; // или компаратором!!!!!!!!!!!
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}

	public static void main(String[] args) {
		Set<CompareTest> set1 = new TreeSet<>(); // сравнение выполняется по compareTo
		for (int i = 0; i < 10; i++) {
			set1.add(new CompareTest(i));
		}
		System.out.println(set1);
		set1 = new HashSet<>(); // сравнение выполняется по equals, для LinkedHashSet тоже
		for (int i = 0; i < 10; i++) {
			set1.add(new CompareTest(i));
		}
		System.out.println(set1);
	}
}

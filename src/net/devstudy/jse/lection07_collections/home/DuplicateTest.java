package net.devstudy.jse.lection07_collections.home;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DuplicateTest implements Comparable<DuplicateTest> {
	public static void main(String[] args) {
		DuplicateTest element = new DuplicateTest();
		Set<DuplicateTest> set1 = new HashSet<>();
		Set<DuplicateTest> set2 = new TreeSet<>();
		for (int i = 0; i < 1000; i++) {
			set1.add(element);
			set2.add(element);
		}
		System.out.println(set1.size());
		System.out.println(set2.size());
	}

	@Override
	public int compareTo(DuplicateTest o) {
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}

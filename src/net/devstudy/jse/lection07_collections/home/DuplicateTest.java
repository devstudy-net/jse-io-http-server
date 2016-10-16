package net.devstudy.jse.lection07_collections.home;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DuplicateTest implements Comparable<DuplicateTest>{

	public static void main(String[] args) {

		DuplicateTest element = new DuplicateTest();
		
		Set<DuplicateTest> set1 = new HashSet<>();
		Set<DuplicateTest> set2 = new TreeSet<>();
		
		for(int i=0;i<1000;i++) {
			set1.add(element);
			set2.add(element);
		}
		
		System.out.println(set1.size());
		System.out.println(set2.size());
		
		System.out.println(element.i);
	}
	
	private int i=0;

	@Override
	public int compareTo(DuplicateTest o) {
		 i++;
         return i < 55 ? 1 : 0;
	}
	
	@Override
	public int hashCode() {
		return new Random().nextInt(15);
	}
}

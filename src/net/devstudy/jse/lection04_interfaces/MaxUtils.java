package net.devstudy.jse.lection04_interfaces;

import java.util.Comparator;

import net.devstudy.jse.lection01_classes_objects.Student;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class MaxUtils {
	public static <T> T findMax(T[] array) {
		T max = null;
		if (array.length > 0) {
			max = array[0];
		}
		for (int i = 1; i < array.length; i++) {
			Comparable<T> current = (Comparable<T>) array[i];
			if (current.compareTo(max) > 0) {
				max = array[i];
			}
		}
		return max;
	}

	public static <T> T findMax(T[] array, Comparator<T> comparator) {
		T max = null;
		if (array.length > 0) {
			max = array[0];
		}
		for (int i = 1; i < array.length; i++) {
			if (comparator.compare(array[i], max) > 0) {
				max = array[i];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		String[] array1 = { "a", "b", "cc", "den", "eff", "jack" };
		System.out.println(findMax(array1));
		Integer[] array2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println(findMax(array2));
		Student[] students = { new Student("Ivan", "Ivanov", 22), new Student("Den", "Banks", 19),
				new Student("Petr", "Alexeev", 24) };
		System.out.println(findMax(students));

		String[] array3 = { "simple", "a new game", "big boss", "Java forever",
				"London is a capital of Great Britain" };
		System.out.println(findMax(array3, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		}));
		System.out.println(findMax(array3, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int len1 = o1.split(" ").length;
				int len2 = o2.split(" ").length;
				return len1 - len2;
			}
		}));
	}
}

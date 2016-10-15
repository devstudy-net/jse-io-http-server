package net.devstudy.jse.lection04_interfaces.home;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class ComparatorTest {

	public static void main(String[] args) {
		Comparator<Character> reverseOrder = new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				return o2 - o1;
			}
		};
		Character[] array1 = { 'b', 'd', 'a', 'g', 'h', 'z' };
		System.out.println("max=" + findMax(array1, null));
		System.out.println("min=" + findMin(array1, null));
		System.out.println("max=" + findMax(array1, reverseOrder));
		System.out.println("min=" + findMin(array1, reverseOrder));

		Comparator<Integer> moduleOrder = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Math.abs(o1) - Math.abs(o2);
			}
		};

		Integer[] array2 = { -1, 3, -2, 4, -9, -7, -8 };
		System.out.println("max=" + findMax(array2, null));
		System.out.println("min=" + findMin(array2, null));
		System.out.println("max=" + findMax(array2, moduleOrder));
		System.out.println("min=" + findMin(array2, moduleOrder));

		Comparator<String> characterLenthOrder = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		};

		String[] array3 = { "qwerty", "z", "23", "hello", "hi", "May" };
		System.out.println("max=" + findMax(array3, null));
		System.out.println("min=" + findMin(array3, null));
		System.out.println("max=" + findMax(array3, characterLenthOrder));
		System.out.println("min=" + findMin(array3, characterLenthOrder));

		System.out.println("------------------------------");
		System.out.println(Arrays.toString(array1));
		bubbleSort(array1, reverseOrder);
		System.out.println(Arrays.toString(array1));

		System.out.println(Arrays.toString(array2));
		bubbleSort(array2, moduleOrder);
		System.out.println(Arrays.toString(array2));

		System.out.println(Arrays.toString(array3));
		bubbleSort(array3, characterLenthOrder);
		System.out.println(Arrays.toString(array3));

		ComparatorTest[] array4 = { new ComparatorTest(), new ComparatorTest(), new ComparatorTest() };
		System.out.println("max=" + findMax(array4, null));
		System.out.println("min=" + findMin(array4, null));
		System.out.println(Arrays.toString(array4));
		bubbleSort(array4, null);
		System.out.println(Arrays.toString(array4));
	}

	public static <T> T findMax(T[] array, Comparator<T> presetComparator) {
		return find(array, presetComparator, new CompareCallback() {
			@Override
			public boolean shouldUpdateResult(int compareResult) {
				return compareResult > 0;
			}
		});
	}

	public static <T> T findMin(T[] array, Comparator<T> presetComparator) {
		return find(array, presetComparator, new CompareCallback() {
			@Override
			public boolean shouldUpdateResult(int compareResult) {
				return compareResult < 0;
			}
		});
	}
	
	private static <T> T find(T[] array, Comparator<T> presetComparator, CompareCallback compareCallback) {
		if (array == null || array.length == 0) {
			return null;
		} else if (array.length == 1) {
			return array[0];
		} else {
			T result = array[0];
			Comparator<T> comparator = getValidComparator(presetComparator, result);
			for (int i = 1; i < array.length; i++) {
				if (compareCallback.shouldUpdateResult(comparator.compare(array[i], result))) {
					result = array[i];
				}
			}
			return result;
		}
	}

	private static interface CompareCallback {
		boolean shouldUpdateResult(int compareResult);
	}

	public static <T> void bubbleSort(T[] array, Comparator<T> presetComparator) {
		if (array != null && array.length > 1) {
			Comparator<T> comparator = getValidComparator(presetComparator, array[0]);
			int firstIndex = 0;
			while (true) {
				boolean swap = false;
				for (int i = array.length - 1; i > firstIndex; i--) {
					if (comparator.compare(array[i], array[i - 1]) < 0) {
						T temp = array[i];
						array[i] = array[i - 1];
						array[i - 1] = temp;
						swap = true;
					}
				}
				if (swap) {
					firstIndex++;
				} else {
					break;
				}
			}
		}
	}

	private static <T> Comparator<T> getValidComparator(Comparator<T> presetComparator, T arrayElement) {
		if (presetComparator != null) {
			return presetComparator;
		} else {
			if (arrayElement instanceof Comparable) {
				return new Comparator<T>() {
					@SuppressWarnings("unchecked")
					@Override
					public int compare(T o1, T o2) {
						return ((Comparable<T>) o1).compareTo(o2);
					}
				};
			} else {
				return new SystemIdentityHashCodeComparator<T>();
			}
		}
	}

	private static class SystemIdentityHashCodeComparator<T> implements Comparator<T> {
		@Override
		public int compare(T o1, T o2) {
			return System.identityHashCode(o1) - System.identityHashCode(o2);
		}
	}
}

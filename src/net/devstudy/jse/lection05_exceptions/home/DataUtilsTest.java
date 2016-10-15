package net.devstudy.jse.lection05_exceptions.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DataUtilsTest {

	public static void main(String[] args) {
		LinkedList<Integer> result = DataUtils.newLinkedListWithDuplicates(testLinkedList(0), testLinkedList(5), testDynaArray(10), testLinkedList(15), testDynaArray(20));
		System.out.println(result);

		result = DataUtils.newLinkedListWithDuplicates(new LinkedList<Integer>(), testLinkedList(5), testDynaArray(10), testLinkedList(15), testDynaArray(20));
		System.out.println(result);
		
		DataSet<Integer> unique = DataUtils.newDataSetWithoutDuplicates(testLinkedList(0), testLinkedList(5), testDynaArray(0), testLinkedList(0), testDynaArray(10));
		System.out.println(unique.getClass());
		System.out.println(unique);
		unique.add(0);
		unique.add(0);
		unique.add(0);
		System.out.println(unique);
		System.out.println("----------------------immutable----------------------------");
		DataSet<Integer> immutable = DataUtils.newImmutableDataSet(result);
		try {
			immutable.clear();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		try {
			immutable.remove(0);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		try {
			immutable.add(333333);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		System.out.println(immutable);
		System.out.println(immutable.size());
		System.out.println(immutable.get(0));
		System.out.println(immutable.getClass());

		immutable = DataUtils.newImmutableDataSet(1, 2, 3);
		try {
			immutable.clear();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		try {
			immutable.remove(0);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		try {
			immutable.add(333333);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		System.out.println(immutable);
		System.out.println(immutable.size());
		System.out.println(immutable.get(0));
		System.out.println(immutable.getClass());

		immutable = DataUtils.newImmutableDataSet(new Integer[] { 1, 2, 3 });
		try {
			immutable.clear();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		try {
			immutable.remove(0);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		try {
			immutable.add(333333);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
		System.out.println(immutable);
		System.out.println(immutable.size());
		System.out.println(immutable.get(0));
		System.out.println(immutable.getClass());

		Queue<Integer> queue = DataUtils.newQueue(testLinkedList(0));
		System.out.println(queue);
		queue = DataUtils.newQueue(testDynaArray(0));
		System.out.println(queue);
		queue = DataUtils.newQueue(new Integer[] { 1, 2, 3 });
		System.out.println(queue);

		Stack<Integer> stack = DataUtils.newStack(testLinkedList(0));
		System.out.println(stack);
		stack = DataUtils.newStack(testDynaArray(0));
		System.out.println(stack);
		stack = DataUtils.newStack(new Integer[] { 1, 2, 3 });
		System.out.println(stack);
	}

	private static LinkedList<Integer> testLinkedList(int start) {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = start; i < start + 5; i++) {
			list.add(i);
		}
		return list;
	}

	private static DynaArray<Integer> testDynaArray(int start) {
		return new DynaArray<>(new Integer[] { start, start + 1, start + 2, start + 3, start + 4 });
	}
}

package net.devstudy.jse.lection05_exceptions.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DataStructureTest {

	public static void main(String[] args) {
		DataStructure<Integer>[] items = new DataStructure[]{ new Stack<Integer>(), new Queue<Integer>() };
		for (DataStructure<Integer> it : items) {
			System.out.println(it.getClass());
			test(it);
		}
	}

	private static void test(DataStructure<Integer> d) {
		for (int i = 0; i < 10; i++) {
			d.add(i);
		}
		System.out.println(d);
		System.out.println("size=" + d.size());
		System.out.println("peek=" + d.peek());
		while (!d.isEmpty()) {
			System.out.print(d.get() + " ");
		}
		System.out.println();
		
		try {
			d.get();
		} catch (RuntimeException e) {
			System.out.println("Get failed: "+e.getMessage());
			e.printStackTrace();
		}
		try {
			d.peek();
		} catch (RuntimeException e) {
			System.out.println("Peek failed: "+e.getMessage());
			e.printStackTrace();
		}
		
		Stack<Integer> s = new Stack<>();
		Queue<Integer> q = new Queue<>();
		//
		for (int i = 0; i < 3; i++) {
			d.add(i);
			s.add(i);
			q.add(i);
		}
		
		System.out.println("d.equals(s)->"+d.equals(s));
		System.out.println("d.hashCode->"+d.hashCode());
		System.out.println("s.hashCode->"+s.hashCode());
		
		System.out.println("d.equals(q)->"+d.equals(q));
		System.out.println("d.hashCode->"+d.hashCode());
		System.out.println("q.hashCode->"+q.hashCode());
	}
}

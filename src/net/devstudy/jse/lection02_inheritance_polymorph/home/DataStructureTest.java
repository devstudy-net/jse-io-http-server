package net.devstudy.jse.lection02_inheritance_polymorph.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DataStructureTest {

	public static void main(String[] args) {
		DataStructure[] items = { new Stack(), new Queue() };
		for (DataStructure it : items) {
			System.out.println(it.getClass());
			test(it);
		}
	}

	private static void test(DataStructure d) {
		for (int i = 0; i < 10; i++) {
			d.add(i);
		}
		System.out.println(d.getClass().getSimpleName()+".toString()="+d);
		System.out.println(d.getClass().getSimpleName()+".size()=" + d.size());
		System.out.println(d.getClass().getSimpleName()+".peek()=" + d.peek());
		while (!d.isEmpty()) {
			System.out.print(d.get() + " ");
		}
		System.out.println();
		
		Stack s = new Stack();
		Queue q = new Queue();
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

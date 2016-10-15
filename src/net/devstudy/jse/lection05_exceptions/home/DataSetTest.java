package net.devstudy.jse.lection05_exceptions.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DataSetTest {

	public static void main(String[] args) {
		
		DataSet sets[] = {new DynaArray<Integer>(), new LinkedList<Integer>()};
		for(DataSet<Integer> d : sets) {
			System.out.println("------------------------------------");
			fillDataSet(d, 0, 4);
			System.out.println(d);
			System.out.println("size=" + d.size());

			d.remove(0);
			d.remove(0);

			System.out.println(d);
			System.out.println("size=" + d.size());

			d.clear(); // d = new LinkedList();

			fillDataSet(d, 0, 99);

			System.out.println(d.get(99));
			try {
				System.out.println(d.get(999));
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}

			System.out.println(d.remove(99));
			try {
				System.out.println(d.remove(999));
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
	}

	private static void fillDataSet(DataSet<Integer> d, int min, int max) {
		for (int i = min; i < max + 1; i++) {
			d.add(i);
		}
	}
}

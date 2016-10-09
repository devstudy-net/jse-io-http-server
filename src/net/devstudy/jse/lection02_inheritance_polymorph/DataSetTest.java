package net.devstudy.jse.lection02_inheritance_polymorph;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DataSetTest {

	public static void main(String[] args) {
		DataSet d = new LinkedList(); // new DynaArray();
		fillDataSet(d, 0, 4);
		d = new ImmutableDataSet(d);
		System.out.println(d.toString());
		System.out.println("size=" + d.size());
		d.remove(0);
		d.remove(0);
		System.out.println(d);
		System.out.println("size=" + d.size());
		d.clear(); 
		fillDataSet(d, 0, 99);
		System.out.println(d.get(2));
		System.out.println(d.get(999));
		System.out.println(d.remove(99));
		System.out.println(d.remove(999));
	}

	private static void fillDataSet(DataSet d, int min, int max) {
		for (int i = min; i < max + 1; i++) {
			d.add(i);
		}
	}

}

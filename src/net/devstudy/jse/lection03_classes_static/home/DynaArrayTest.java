package net.devstudy.jse.lection03_classes_static.home;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DynaArrayTest {

	public static void main(String[] args) {
		DynaArray<Integer> ds = new DynaArray<>();
		ds.add(2);
		Object[] array = ds.toArray();
		System.out.println(array[0]);
	}

}

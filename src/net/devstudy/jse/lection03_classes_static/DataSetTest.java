package net.devstudy.jse.lection03_classes_static;

import net.devstudy.jse.lection03_classes_static.home.DataSet;
import net.devstudy.jse.lection03_classes_static.home.DynaArray;
import net.devstudy.jse.lection03_classes_static.home.LinkedList;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DataSetTest {

	public static void main(String[] args) {
		DataSet<Integer> ds = new LinkedList<>();
		DataSet<String> ds2 = new DynaArray<>();
		ds.add(3);
		int v = (int) ds.get(0);
		ds2.add(String.valueOf(ds.get(0)));
		String s = (String) ds2.get(0);
		
		System.out.println(ds.get(0).getClass());
	}

}

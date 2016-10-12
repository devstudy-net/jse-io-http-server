package net.devstudy.jse.lection03_classes_static.home;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class VarArgs {

	public static void main(String[] args) {
		test();
		test(1);
		test(2, 1, 4);
	}
	
	private static void test(int ...objects ){
		for(int i=0;i<objects.length;i++){
			System.out.print(objects[i]+", ");
		}
		System.out.println();
	}

}

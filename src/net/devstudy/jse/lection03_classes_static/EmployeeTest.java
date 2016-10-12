package net.devstudy.jse.lection03_classes_static;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class EmployeeTest {

	public static void main(String[] args) {
		System.out.println(Employee.counter);
		new Employee();
		new Employee(); 
		new Employee(); 
		new Employee(); 
		Employee e = new Employee();
		System.out.println(Employee.counter);
	}

}

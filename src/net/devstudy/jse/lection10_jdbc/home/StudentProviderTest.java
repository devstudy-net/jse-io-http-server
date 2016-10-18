package net.devstudy.jse.lection10_jdbc.home;

import net.devstudy.jse.lection01_classes_objects.Student;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class StudentProviderTest {

	public static void main(String[] args) {
		StudentProvider sp = new DatabaseStudentProvider();
		System.out.println(sp.findAll(0, 1));
		Student s = new Student("test", "test", 19);
		sp.create(s);
		System.out.println(s);
		System.out.println(s.getId());
	}

}

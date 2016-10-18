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
		System.out.println("findById="+sp.findById(1L));
		System.out.println("findAll="+sp.findAll(0, 10));
		System.out.println("countAll="+sp.countAll());
		System.out.println("findByAge="+sp.findByAge(22, 0, 10));
		System.out.println("countByAge="+sp.countByAge(22));
		Student s = new Student("test", "test", 19);
		sp.create(s);
		s.setAge(25);
		sp.update(s);
		sp.delete(s);
		System.out.println(s);
	}
}

package net.devstudy.jse.lection02_inheritance_polymorph;

import net.devstudy.jse.lection01_classes_objects.Student;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ForeignStudentTest {

	public static void main(String[] args) {
		Student s1 = new ForeignStudent("Jack", "Smith", 25, "english");
		ForeignStudent s2 = new ForeignStudent("Jack", "Smith", 25, "english");
		Student s3 = s2;
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s3 == s2);
		System.out.println(s3.equals(s2));
		System.out.println(s1.toString());
		System.out.println(s2);
	}
}

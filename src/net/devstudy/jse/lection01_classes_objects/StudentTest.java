package net.devstudy.jse.lection01_classes_objects;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class StudentTest {

	public static void main(String[] args) {
		Student s1 = new Student();
		s1.setFirstName("ivan");
		s1.setLastName("IVANOV");
		s1.setAge(5);
		System.out.println(s1.getFullName() + ", " + s1.getAge());
		Student s2 = new Student("PETR", "petrov", 21);
		Student s3 = new Student("Sergey", "Sergeev", 0);
		s3.setAge(19);
		System.out.println(s2.getFullName() + ", " + s2.getAge());
		System.out.println(s3.getFullName() + ", " + s3.getAge());
	}

}

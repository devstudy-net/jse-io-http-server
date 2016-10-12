package net.devstudy.jse.lection04_interfaces;

import net.devstudy.jse.lection01_classes_objects.Student;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class StudentCalculator {

	public static void main(String[] args) {
		StudentProvider studentProvider = new FromFileStudentProvider();
		Student[] students = studentProvider.getStudents();
		int ageSum = 0;
		for (Student student : students) {
			ageSum += student.getAge();
		}
		System.out.println("Result: " + ageSum / students.length);
	}

}

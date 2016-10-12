package net.devstudy.jse.lection04_interfaces;

import net.devstudy.jse.lection01_classes_objects.Student;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class TestStudentProvider implements StudentProvider {

	@Override
	public Student[] getStudents() {
		return new Student[] { 
				new Student("Ivan", "Ivanov", 22), 
				new Student("Petr", "Petrov", 20),
				new Student("Sergey", "Sergeev", 24)
		};
	}

}

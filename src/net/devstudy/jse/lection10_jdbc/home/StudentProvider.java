package net.devstudy.jse.lection10_jdbc.home;

import java.util.List;

import net.devstudy.jse.lection01_classes_objects.Student;


/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public interface StudentProvider {

	List<Student> findAll(int offset, int limit);
	
	long countAll();
	
	Student findById(Long id);
	
	List<Student> findByAge(int age, int offset, int limit);
	
	long countByAge(int age);
	
	void create(Student student);
	
	void update(Student student);
	
	void delete(Student student);
	
	void deleteById(Long id);
}

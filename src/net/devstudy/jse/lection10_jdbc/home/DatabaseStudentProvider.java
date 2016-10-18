package net.devstudy.jse.lection10_jdbc.home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.devstudy.jse.lection01_classes_objects.Student;
import net.devstudy.jse.lection10_jdbc.JDBCUtils;
import net.devstudy.jse.lection10_jdbc.ResultSetHandler;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DatabaseStudentProvider implements StudentProvider {

	protected Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/devstudy", "devstudy", "password");
	}

	private static ResultSetHandler<Student> ONE_STUDENT_HANDLER = new ResultSetHandler<Student>() {
		@Override
		public Student handle(ResultSet rs) throws SQLException {
			if (rs.next()) {
				return new Student(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getInt("age"));
			} else {
				return null;
			}
		}
	};
	private static ResultSetHandler<List<Student>> LIST_STUDENTS_HANDLER = new ResultSetHandler<List<Student>>() {
		@Override
		public List<Student> handle(ResultSet rs) throws SQLException {
			List<Student> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Student(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getInt("age")));
			}
			return list;
		}
	};

	@Override
	public List<Student> findAll(int offset, int limit) {
		try (Connection c = getConnection()) {
			return JDBCUtils.select(c, "select * from student limit ? offset ?", LIST_STUDENTS_HANDLER, limit, offset);
		} catch (SQLException e) {
			throw new ProviderRetrieveException("Can't execute SQL query: " + e.getMessage(), e);
		}
	}

	@Override
	public long countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Student findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findByAge(int age, int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countByAge(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void create(Student student) {
		try (Connection c = getConnection()) {
			Student insertedStudent = JDBCUtils.insert(c, "insert into student values(nextval('student_seq'),?,?,?)",
					ONE_STUDENT_HANDLER, student.getFirstName(), student.getLastName(), student.getAge());
			student.setId(insertedStudent.getId());
		} catch (SQLException e) {
			throw new ProviderRetrieveException("Can't execute SQL query: " + e.getMessage(), e);
		}
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

}

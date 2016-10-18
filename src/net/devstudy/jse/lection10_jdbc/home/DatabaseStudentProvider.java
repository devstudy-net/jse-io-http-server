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
				return new Student(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("age"));
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
				list.add(new Student(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("age")));
			}
			return list;
		}
	};
	private static ResultSetHandler<Long> COUNT_HANDLER = new ResultSetHandler<Long>() {
		@Override
		public Long handle(ResultSet rs) throws SQLException {
			if (rs.next()) {
				return rs.getLong(1);
			} else {
				return 0L;
			}
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
	public Student findById(Long id) {
		try (Connection c = getConnection()) {
			return JDBCUtils.select(c, "select * from student where id = ?", ONE_STUDENT_HANDLER, id);
		} catch (SQLException e) {
			throw new ProviderRetrieveException("Can't execute SQL query: " + e.getMessage(), e);
		}
	}

	@Override
	public long countAll() {
		try (Connection c = getConnection()) {
			return JDBCUtils.select(c, "select count(*) from student", COUNT_HANDLER);
		} catch (SQLException e) {
			throw new ProviderRetrieveException("Can't execute SQL query: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Student> findByAge(int age, int offset, int limit) {
		try (Connection c = getConnection()) {
			return JDBCUtils.select(c, "select * from student where age=? limit ? offset ?", LIST_STUDENTS_HANDLER, age, limit, offset);
		} catch (SQLException e) {
			throw new ProviderRetrieveException("Can't execute SQL query: " + e.getMessage(), e);
		}
	}

	@Override
	public long countByAge(int age) {
		try (Connection c = getConnection()) {
			return JDBCUtils.select(c, "select count(*) from student where age=?", COUNT_HANDLER, age);
		} catch (SQLException e) {
			throw new ProviderRetrieveException("Can't execute SQL query: " + e.getMessage(), e);
		}
	}

	@Override
	public void create(Student student) {
		try (Connection c = getConnection()) {
			Student insertedStudent = JDBCUtils.insert(c, "insert into student values(nextval('student_seq'),?,?,?)", ONE_STUDENT_HANDLER, 
					student.getFirstName(), student.getLastName(), student.getAge());
			student.setId(insertedStudent.getId());
		} catch (SQLException e) {
			throw new ProviderRetrieveException("Can't execute SQL query: " + e.getMessage(), e);
		}
	}

	@Override
	public void update(Student student) {
		try (Connection c = getConnection()) {
			int result = JDBCUtils.executeUpdate(c, "update student set first_name=?, last_name=?, age=? where id=?",
					student.getFirstName(), student.getLastName(), student.getAge(), student.getId());
			if (result == 0) {
				throw new ProviderRetrieveException("Student not found by id="+student.getId());
			}
		} catch (SQLException e) {
			throw new ProviderRetrieveException("Can't execute SQL query: " + e.getMessage(), e);
		}
	}

	@Override
	public void delete(Student student) {
		deleteById(student.getId());
	}

	@Override
	public void deleteById(final Long id) {
		try (Connection c = getConnection()) {
			int result = JDBCUtils.executeUpdate(c, "delete from student where id=?", id);
			if (result == 0) {
				throw new ProviderRetrieveException("Student not found by id="+id);
			}
		} catch (SQLException e) {
			throw new ProviderRetrieveException("Can't execute SQL query: " + e.getMessage(), e);
		}
		/*execute(new Callback<Void>(){
			@Override
			public Void execute(Connection c) throws SQLException {
				int result = JDBCUtils.executeUpdate(c, "delete from student where id=?", id);
				if (result == 0) {
					throw new ProviderRetrieveException("Student not found by id="+id);
				}
				return null;
			}
		});*/
	}
	
	protected <T> T execute(Callback<T> callback) {
		try (Connection c = getConnection()) {
			return callback.execute(c);
		} catch (SQLException e) {
			throw new ProviderRetrieveException("Can't execute SQL query: " + e.getMessage(), e);
		}
	}
	
	private interface Callback<T> {
		T execute(Connection c) throws SQLException;
	}
}

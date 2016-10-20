package net.devstudy.httpserver.io.handler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.devstudy.httpserver.io.HttpHandler;
import net.devstudy.httpserver.io.HttpRequest;
import net.devstudy.httpserver.io.HttpResponse;
import net.devstudy.httpserver.io.HttpServerContext;
import net.devstudy.httpserver.io.exception.HttpServerException;
import net.devstudy.httpserver.io.utils.DataUtils;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class TestJDBCHandler implements HttpHandler {

	@Override
	public void handle(HttpServerContext context, HttpRequest request, HttpResponse response) throws IOException {
		try (Connection c = context.getDataSource().getConnection()) {
			List<Student> list = JDBCUtils.select(c, "select * from student", STUDENTS_RESULT_SET_HANDLER);
			Map<String, Object> args = DataUtils.buildMap(new Object[][] { 
				{ "TABLE-BODY", getTableBody(context, list) } 
			});
			response.setBody(context.getHtmlTemplateManager().processTemplate("students.html", args));
		} catch (SQLException e) {
			throw new HttpServerException("Error with database: " + e.getMessage(), e);
		}
	}

	protected String getTableBody(HttpServerContext context, List<Student> list) {
		StringBuilder body = new StringBuilder();
		for (Student s : list) {
			Map<String, Object> args = DataUtils.buildMap(new Object[][] { 
				{ "ID", s.getId() },
				{ "FIRST-NAME", s.getFirstName() }, 
				{ "LAST-NAME", s.getLastName() }, 
				{ "AGE", s.getAge() } 
			});
			String fragment = context.getHtmlTemplateManager().processTemplate("student-row.html", args);
			body.append(fragment);
		}
		return body.toString();
	}

	private static ResultSetHandler<List<Student>> STUDENTS_RESULT_SET_HANDLER = new ResultSetHandler<List<Student>>() {
		@Override
		public List<Student> handle(ResultSet rs) throws SQLException {
			List<Student> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Student(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getInt("age")));
			}
			return list;
		}
	};

	/**
	 * 
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	public interface ResultSetHandler<T> {
		T handle(ResultSet rs) throws SQLException;
	}

	/**
	 * 
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	public static class JDBCUtils {
		public static <T> T select(Connection c, String sql, ResultSetHandler<T> resultSetHandler, Object... parameters)
				throws SQLException {
			try (PreparedStatement ps = c.prepareStatement(sql)) {
				populatePreparedStatement(ps, parameters);
				try (ResultSet rs = ps.executeQuery()) {
					return resultSetHandler.handle(rs);
				}
			}
		}

		private static void populatePreparedStatement(PreparedStatement ps, Object... parameters) throws SQLException {
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setObject(i + 1, parameters[i]);
				}
			}
		}
	}

	/**
	 * 
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	public static class Student implements Comparable<Student> {
		private Long id;
		private String firstName;
		private String lastName;
		private int age;

		public Student(String firstName, String lastName, int age) {
			setFirstName(firstName);
			setLastName(lastName);
			setAge(age);
		}

		public Student(Long id, String firstName, String lastName, int age) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.age = age;
		}

		public Student() {
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			if (firstName.length() > 1) {
				this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
			} else {
				this.firstName = firstName.toUpperCase();
			}
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			if (lastName.length() > 1) {
				this.lastName = Character.toUpperCase(lastName.charAt(0)) + lastName.substring(1).toLowerCase();
			} else {
				this.lastName = lastName;
			}
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age > 17 && age < 50 ? age : 18;
		}

		public String getFullName() {
			return getLastName() + " " + getFirstName();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return String.format("Student [id=%s, firstName=%s, lastName=%s, age=%s]", id, firstName, lastName, age);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Student other = (Student) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public int compareTo(Student o) {
			if (getId() != null && o != null && o.getId() != null) {
				return getId().compareTo(o.getId());
			}
			throw new IllegalArgumentException("Can't compare students: id=" + getId() + ", o=" + o);
		}
	}
}

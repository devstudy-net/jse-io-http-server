package net.devstudy.jse.lection10_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DMLTest {

	public static void main(String[] args) throws SQLException {
		try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/devstudy", "devstudy", "password")) {
			try (PreparedStatement ps = c.prepareStatement("insert into student values(?,?,?,?)")) {
				ps.setLong(1, 0);
				ps.setString(2, "Jack");
				ps.setString(3, "Sparrow");
				ps.setInt(4, 25);
				int inserted = ps.executeUpdate();
				System.out.println("Inserted " + inserted + " row");
			}
			try (PreparedStatement ps = c.prepareStatement("update student set first_name=?,last_name=?, age=? where id=?")) {
				ps.setString(1, "Bob");
				ps.setString(2, "Smith");
				ps.setInt(3, 18);
				ps.setLong(4, 0);
				int updated = ps.executeUpdate();
				System.out.println("Updated " + updated + " row");
			}
			try (PreparedStatement ps = c.prepareStatement("delete from student where id=?")) {
				ps.setLong(1, 0);
				int deleted = ps.executeUpdate();
				System.out.println("Deleted " + deleted + " row");
			}
		}
	}

}

package net.devstudy.jse.lection10_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class StatementTest {

	public static void main(String[] args) throws SQLException {
		try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/devstudy", "devstudy",
				"password")) {
			try (Statement st = c.createStatement()) {
				int result = st.executeUpdate("delete from student where id=100");
				System.out.println("deleted " + result + " row(s)");
			} // ------------------------------------------------------
			try (PreparedStatement ps = c.prepareStatement("delete from student where id=?")) {
				ps.setInt(1, 100);
				int result = ps.executeUpdate();
				System.out.println("deleted " + result + " row(s)");
			} // ------------------------------------------------------
			try (Statement st = c.createStatement()) {
				try (ResultSet rs = st.executeQuery("select * from student")) {
					// TODO ResultSet
				}
			}
		}
	}

}

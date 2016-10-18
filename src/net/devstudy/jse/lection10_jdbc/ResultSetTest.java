package net.devstudy.jse.lection10_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ResultSetTest {

	public static void main(String[] args) throws SQLException {
		try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/devstudy", "devstudy", "password")) {
			try (Statement st = c.createStatement()) {
				try (ResultSet rs = st.executeQuery("select * from student")) {
					while (rs.next()) {
						System.out.println(rs.getLong(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getInt(4));
					}
				}
				System.out.println("-------------------------------------------------");
				try (ResultSet rs = st.executeQuery("select * from student")) {
					while (rs.next()) {
						System.out.println(rs.getLong("id") + ", " + rs.getString("first_name") + ", "
								+ rs.getString("last_name") + ", " + rs.getInt("age"));
					}
				}
			}
		}
	}

}

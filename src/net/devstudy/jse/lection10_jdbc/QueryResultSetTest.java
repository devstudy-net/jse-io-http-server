package net.devstudy.jse.lection10_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class QueryResultSetTest {

	public static void main(String[] args) throws SQLException {
		try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/devstudy", "devstudy", "password")) {
			try (Statement st = c.createStatement()) {
				try (ResultSet rs = st.executeQuery("select age, count(*) as cnt from student group by age")) {
					ResultSetMetaData meta = rs.getMetaData();
					for (int i = 1; i <= meta.getColumnCount(); i++) {
						display(meta.getColumnLabel(i));
						if (i != meta.getColumnCount()) {
							System.out.print("| ");
						}
					}
					System.out.println("\n---------------------------------------");
					while (rs.next()) {
						for (int i = 1; i <= meta.getColumnCount(); i++) {
							display(String.valueOf(rs.getObject(i)));
							if (i != meta.getColumnCount()) {
								System.out.print("| ");
							}
						}
						System.out.println("\n---------------------------------------");
					}
				}
			}
		}
	}

	private static void display(String value) {
		System.out.print(value);
		for (int i = 0; i < 10 - value.length(); i++) {
			System.out.print(" ");
		}
	}

}

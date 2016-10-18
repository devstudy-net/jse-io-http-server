package net.devstudy.jse.lection10_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class BatchOperations {

	public static void main(String[] args) throws SQLException {
		try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/devstudy", "devstudy", "password")) {
			try (PreparedStatement ps = c.prepareStatement("insert into student values(?,?,?,?)")) {
				for (int i = 0; i < 20; i++) {
					ps.setLong(1, 100 + i);
					ps.setString(2, "Jack" + i);
					ps.setString(3, "Sparrow" + i);
					ps.setInt(4, new Random().nextInt(10) + 18);
					ps.addBatch();
				}
				int[] inserted = ps.executeBatch();
				System.out.println("Inserted " + Arrays.toString(inserted) + " rows");
			}
		}
	}

}

package net.devstudy.jse.lection10_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class GetConnectionTest {

	public static void main(String[] args) throws SQLException {
		try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/devstudy", "devstudy",
				"password")) {
			System.out.println(c.isValid(0));
		} // ------------------------------------------------------------------------------------------------------------------------
		DataSource ds = createPostgresqlDataSource();
		try {
			try (Connection c = ds.getConnection()) {
				System.out.println(c.isValid(0));
			}
		} finally {
			((PGPoolingDataSource) ds).close();
		}
	}

	private static DataSource createPostgresqlDataSource() {
		PGPoolingDataSource ds = new PGPoolingDataSource();
		ds.setDataSourceName("A Data Source");
		ds.setServerName("localhost");
		ds.setDatabaseName("devstudy");
		ds.setUser("devstudy");
		ds.setPassword("password");
		ds.setInitialConnections(1);
		ds.setMaxConnections(3);
		return ds;
	}

}

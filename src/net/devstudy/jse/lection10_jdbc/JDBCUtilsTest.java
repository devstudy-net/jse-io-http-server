package net.devstudy.jse.lection10_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class JDBCUtilsTest {

	public static void main(String[] args) throws SQLException {
		try (Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/devstudy", "devstudy", "password")) {
			Map<String, Object> map = JDBCUtils.select(c, "select * from student",
					new ResultSetHandler<Map<String, Object>>() {
						@Override
						public Map<String, Object> handle(ResultSet rs) throws SQLException {
							if (rs.next()) {
								Map<String, Object> map = new LinkedHashMap<>();
								for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
									map.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
								}
								return map;
							} else {
								return Collections.emptyMap();
							}

						}
					});

			System.out.println(map);
		}

	}

}

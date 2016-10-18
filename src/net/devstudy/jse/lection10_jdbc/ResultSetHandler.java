package net.devstudy.jse.lection10_jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface ResultSetHandler<T> {
	T handle(ResultSet rs) throws SQLException;
}

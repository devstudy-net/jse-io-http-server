package net.devstudy.jse.lection12_junit;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import net.devstudy.jse.lection10_jdbc.JDBCUtils;
import net.devstudy.jse.lection10_jdbc.ResultSetHandler;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class JDBCUtilsTest {
	private Connection c;
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSetHandler<Object> rsh;

	@Before
	public void before() {
		c = mock(Connection.class);
		ps = mock(PreparedStatement.class);
		rs = mock(ResultSet.class);
		rsh = mock(ResultSetHandler.class);
	}

	@Test
	public void testSelecWithoutParams() throws SQLException {
		Object expectedResult = new Object();
		when(c.prepareStatement("select (1+1)")).thenReturn(ps);
		when(ps.executeQuery()).thenReturn(rs);
		when(rsh.handle(rs)).thenReturn(expectedResult);
		Object actualResult = JDBCUtils.select(c, "select (1+1)", rsh);
		verify(c).prepareStatement("select (1+1)");
		verify(ps).executeQuery();
		verify(rsh).handle(rs);
		verify(ps, never()).setObject(anyInt(), any());
		verify(rs).close();
		verify(ps).close();
		assertSame(expectedResult, actualResult);
	}
}

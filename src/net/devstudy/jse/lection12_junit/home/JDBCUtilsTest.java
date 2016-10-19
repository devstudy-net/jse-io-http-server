package net.devstudy.jse.lection12_junit.home;

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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.devstudy.jse.lection10_jdbc.JDBCUtils;
import net.devstudy.jse.lection10_jdbc.ResultSetHandler;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
@SuppressWarnings("unchecked")
public class JDBCUtilsTest {
	private Connection c;
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSetHandler<Object> rsh;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void before() {
		c = mock(Connection.class);
		ps = mock(PreparedStatement.class);
		rs = mock(ResultSet.class);
		rsh = mock(ResultSetHandler.class);
	}

	@Test
	public void testInsertWithoutParams() throws SQLException {
		Object expectedResult = new Object();
		when(c.prepareStatement("insert into table1 values(1)", PreparedStatement.RETURN_GENERATED_KEYS)).thenReturn(ps);
		when(ps.executeUpdate()).thenReturn(1);
		when(ps.getGeneratedKeys()).thenReturn(rs);
		when(rsh.handle(rs)).thenReturn(expectedResult);
		Object actualResult = JDBCUtils.insert(c, "insert into table1 values(1)", rsh);

		verify(c).prepareStatement("insert into table1 values(1)", PreparedStatement.RETURN_GENERATED_KEYS);
		verify(ps).executeUpdate();
		verify(rsh).handle(rs);
		verify(ps, never()).setObject(anyInt(), any());
		verify(rs).close();
		verify(ps).close();
		assertSame(expectedResult, actualResult);
	}

	@Test
	public void testInsertWithParams() throws SQLException {
		Object expectedResult = new Object();
		when(c.prepareStatement("insert into table1 values(?,?)", PreparedStatement.RETURN_GENERATED_KEYS)).thenReturn(ps);
		when(ps.executeUpdate()).thenReturn(1);
		when(ps.getGeneratedKeys()).thenReturn(rs);
		when(rsh.handle(rs)).thenReturn(expectedResult);
		Object actualResult = JDBCUtils.insert(c, "insert into table1 values(?,?)", rsh, 1, 2);

		verify(c).prepareStatement("insert into table1 values(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		verify(ps).executeUpdate();
		verify(rsh).handle(rs);
		verify(ps).setObject(1, 1);
		verify(ps).setObject(2, 2);
		verify(rs).close();
		verify(ps).close();
		assertSame(expectedResult, actualResult);
	}

	@Test
	public void testInvalidResult() throws SQLException {
		thrown.expect(SQLException.class);
		thrown.expectMessage("Can't insert row to database. Result=2");
		when(c.prepareStatement("insert into table1 values(1)", PreparedStatement.RETURN_GENERATED_KEYS)).thenReturn(ps);
		when(ps.executeUpdate()).thenReturn(2);
		JDBCUtils.insert(c, "insert into table1 values(1)", rsh);
	}
	
	@Test
	public void testCloseResourcesIfSQLExceptionThrown() throws SQLException {
		when(c.prepareStatement("insert into table1 values(1)", PreparedStatement.RETURN_GENERATED_KEYS)).thenReturn(ps);
		when(ps.executeUpdate()).thenReturn(1);
		when(ps.getGeneratedKeys()).thenReturn(rs);
		when(rsh.handle(rs)).thenThrow(new SQLException());
		try {
			JDBCUtils.insert(c, "insert into table1 values(1)", rsh);
		} catch (SQLException e){}
		verify(rs).close();
		verify(ps).close();
	}
}

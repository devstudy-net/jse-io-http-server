package net.devstudy.httpserver.io.handler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.devstudy.httpserver.io.HttpHandler;
import net.devstudy.httpserver.io.HttpRequest;
import net.devstudy.httpserver.io.HttpResponse;
import net.devstudy.httpserver.io.HttpServerContext;
import net.devstudy.httpserver.io.exception.HttpServerException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class TestJDBCHandler implements HttpHandler {

	@Override
	public void handle(HttpServerContext context, HttpRequest request, HttpResponse response) throws IOException {
		try(Connection c = context.getDataSource().getConnection(); 
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from student")) {
			if(rs.next()) {
				response.setBody(rs.getString(1));
			} else {
				response.setBody("null");
			}
		} catch (SQLException e) {
			throw new HttpServerException("Error with database: "+e.getMessage(), e);
		}
	}

}

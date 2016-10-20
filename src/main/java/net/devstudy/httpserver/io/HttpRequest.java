package net.devstudy.httpserver.io;

import java.util.Map;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface HttpRequest {

	String getStartingLine();
	
	String getMethod();
	
	String getUri();
	
	String getHttpVersion();
	
	String getRemoteAddress();
	
	Map<String, String> getHeaders();
	
	Map<String, String> getParameters();
}

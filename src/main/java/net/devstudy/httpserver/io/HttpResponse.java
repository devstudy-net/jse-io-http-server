package net.devstudy.httpserver.io;

import java.io.InputStream;
import java.io.Reader;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface HttpResponse {

	void setStatus(int status);
	
	void setHeader(String name, Object value);
	
	void setBody(String content);
	
	void setBody(InputStream in);
	
	void setBody(Reader reader);
}

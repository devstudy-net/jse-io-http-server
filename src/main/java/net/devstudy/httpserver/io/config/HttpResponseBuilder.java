package net.devstudy.httpserver.io.config;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface HttpResponseBuilder {

	ReadableHttpResponse buildNewHttpResponse();
	
	void prepareHttpResponse(ReadableHttpResponse response, boolean clearBody);
}

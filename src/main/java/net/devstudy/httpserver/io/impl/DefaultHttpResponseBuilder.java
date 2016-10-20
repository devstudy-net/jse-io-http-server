package net.devstudy.httpserver.io.impl;

import net.devstudy.httpserver.io.config.HttpResponseBuilder;
import net.devstudy.httpserver.io.config.ReadableHttpResponse;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
class DefaultHttpResponseBuilder implements HttpResponseBuilder {

	@Override
	public ReadableHttpResponse buildNewHttpResponse() {
		// TODO Auto-generated method stub
		return new DefaultReadableHttpResponse();
	}

	@Override
	public void prepareHttpResponse(ReadableHttpResponse response, boolean clearBody) {
		// TODO Auto-generated method stub

	}

}

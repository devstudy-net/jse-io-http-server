package net.devstudy.httpserver.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.IOUtils;

import net.devstudy.httpserver.io.config.ReadableHttpResponse;
import net.devstudy.httpserver.io.exception.HttpServerException;
import net.devstudy.httpserver.io.utils.HttpUtils;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
class DefaultReadableHttpResponse implements ReadableHttpResponse {
	private final Map<String, String> headers;
	private byte[] body;
	private int status;
	
	protected DefaultReadableHttpResponse() {
		this.status = 200;
		this.headers = new LinkedHashMap<>();
		this.body = new byte[0];
	}

	@Override
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public void setHeader(String name, Object value) {
		Objects.requireNonNull(name, "Name can't be null");
		Objects.requireNonNull(value, "Value can't be null");
		name = HttpUtils.normilizeHeaderName(name);
		if(value instanceof Date) {
			headers.put(name, new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").format(value));
		} else if(value instanceof FileTime) {
			headers.put(name, new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").format(new Date(((FileTime) value).toMillis())));
		} else {
			headers.put(name, String.valueOf(value));
		}
	}

	@Override
	public void setBody(String content) {
		Objects.requireNonNull(content, "Content can't be null");
		this.body = content.getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public void setBody(InputStream in) {
		try {
			Objects.requireNonNull(in, "InputStream can't be null");
			this.body = IOUtils.toByteArray(in);
		} catch (IOException e) {
			throw new HttpServerException("Can't set http response body from inputstream: "+e.getMessage(), e);
		}
	}

	@Override
	public void setBody(Reader reader) {
		try {
			Objects.requireNonNull(reader, "Reader can't be null");
			this.body = IOUtils.toByteArray(reader, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new HttpServerException("Can't set http response body from reader: "+e.getMessage(), e);
		}
	}

	@Override
	public int getStatus() {
		return status;
	}
	
	@Override
	public Map<String, String> getHeaders() {
		return headers;
	}
	
	@Override
	public byte[] getBody() {
		return body;
	}
	
	@Override
	public boolean isBodyEmpty() {
		return getBodyLength() == 0;
	}
	
	@Override
	public int getBodyLength() {
		return body.length;
	}
}

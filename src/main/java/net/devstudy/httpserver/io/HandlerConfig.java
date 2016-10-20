package net.devstudy.httpserver.io;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import net.devstudy.httpserver.io.exception.HttpServerConfigException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public final class HandlerConfig {
	private final Map<String, HttpHandler> httpHandlers = new HashMap<>();

	public HandlerConfig addHandler(String url, HttpHandler httpHandler) {
		Objects.requireNonNull(url);
		Objects.requireNonNull(httpHandler);
		HttpHandler prevHttpHandler = httpHandlers.get(url);
		if (prevHttpHandler != null) {
			throw new HttpServerConfigException("Http handler already exists for url=" + url + 
					". Http handler class: " + prevHttpHandler.getClass().getName());
		}
		httpHandlers.put(url, httpHandler);
		return this;
	}

	public Map<String, HttpHandler> toMap() {
		return Collections.unmodifiableMap(httpHandlers);
	}
}

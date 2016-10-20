package net.devstudy.httpserver.io.impl;

import net.devstudy.httpserver.io.config.HttpServerConfig;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
class AbstractHttpConfigurableComponent {
	final HttpServerConfig httpServerConfig;

	AbstractHttpConfigurableComponent(HttpServerConfig httpServerConfig) {
		super();
		this.httpServerConfig = httpServerConfig;
	}
}

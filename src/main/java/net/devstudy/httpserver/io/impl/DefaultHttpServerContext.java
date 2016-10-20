package net.devstudy.httpserver.io.impl;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Properties;

import javax.sql.DataSource;

import net.devstudy.httpserver.io.Constants;
import net.devstudy.httpserver.io.HtmlTemplateManager;
import net.devstudy.httpserver.io.HttpServerContext;
import net.devstudy.httpserver.io.ServerInfo;
import net.devstudy.httpserver.io.exception.HttpServerConfigException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
class DefaultHttpServerContext extends AbstractHttpConfigurableComponent implements HttpServerContext {

	DefaultHttpServerContext(DefaultHttpServerConfig httpServerConfig) {
		super(httpServerConfig);
	}
	
	private DefaultHttpServerConfig getHttpServerConfig() {
		return (DefaultHttpServerConfig) httpServerConfig;
	}

	@Override
	public DataSource getDataSource() {
		if (getHttpServerConfig().getDataSource() != null) {
			return getHttpServerConfig().getDataSource();
		} else {
			throw new HttpServerConfigException("Datasource is not configured for this context");
		}
	}
	
	@Override
	public ServerInfo getServerInfo() {
		return getHttpServerConfig().getServerInfo();
	}

	@Override
	public Path getRootPath() {
		return getHttpServerConfig().getRootPath();
	}

	@Override
	public String getContentType(String extension) {
		String result = getHttpServerConfig().getMimeTypesProperties().getProperty(extension);
		return result != null ? result : "text/plain";
	}

	@Override
	public HtmlTemplateManager getHtmlTemplateManager() {
		return getHttpServerConfig().getHtmlTemplateManager();
	}
	
	@Override
	public Integer getExpiresDaysForResource(String extension) {
		if(getHttpServerConfig().getStaticExpiresExtensions().contains(extension)) {
			return getHttpServerConfig().getStaticExpiresDays();
		} else {
			return null;
		}
	}
	
	@Override
	public Collection<String> getSupportedRequestMethods() {
		return Constants.ALLOWED_METHODS;
	}
	
	@Override
	public Properties getSupportedResponseStatuses() {
		Properties prop = new Properties();
		prop.putAll(getHttpServerConfig().getStatusesProperties());
		return prop;
	}
}

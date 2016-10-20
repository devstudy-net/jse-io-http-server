package net.devstudy.httpserver.io;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Properties;

import javax.sql.DataSource;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface HttpServerContext {

	ServerInfo getServerInfo();
	
	Collection<String> getSupportedRequestMethods();
	
	Properties getSupportedResponseStatuses();
	
	DataSource getDataSource();
	
	Path getRootPath();
	
	String getContentType(String extension);
	
	HtmlTemplateManager getHtmlTemplateManager();
	
	Integer getExpriresDaysForResource(String extension);
}

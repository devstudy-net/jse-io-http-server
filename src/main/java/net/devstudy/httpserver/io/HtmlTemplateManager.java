package net.devstudy.httpserver.io;

import java.util.Map;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface HtmlTemplateManager {

	String processTemplate(String templateName, Map<String, Object> args);
}

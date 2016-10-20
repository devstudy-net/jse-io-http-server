package net.devstudy.httpserver.io.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import net.devstudy.httpserver.io.HtmlTemplateManager;
import net.devstudy.httpserver.io.exception.HttpServerException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
class DefaultHtmlTemplateManager implements HtmlTemplateManager {
	private final Map<String, String> templates = new HashMap<>();

	@Override
	public String processTemplate(String templateName, Map<String, Object> args) {
		String template = getTemplate(templateName);
		return populateTemplate(template, args);
	}

	protected InputStream getClasspathResource(String name) {
		return DefaultHtmlTemplateManager.class.getClassLoader().getResourceAsStream(name);
	}

	protected String getTemplate(String templateName) {
		String template = templates.get(templateName);
		if (template == null) {
			try (InputStream in = getClasspathResource("html/templates/" + templateName)) {
				if (in == null) {
					throw new HttpServerException("Classpath resource \"html/templates/" + templateName + "\" not found");
				}
				template = IOUtils.toString(in, StandardCharsets.UTF_8);
				templates.put(templateName, template);
			} catch (IOException e) {
				throw new HttpServerException("Can't load template: " + templateName, e);
			}
		}
		return template;
	}

	protected String populateTemplate(String template, Map<String, Object> args) {
		String html = template;
		for (Map.Entry<String, Object> entry : args.entrySet()) {
			html = html.replace("${" + entry.getKey() + "}", String.valueOf(entry.getValue()));
		}
		return html;
	}
}

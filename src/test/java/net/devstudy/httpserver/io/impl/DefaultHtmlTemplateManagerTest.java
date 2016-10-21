package net.devstudy.httpserver.io.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.devstudy.httpserver.io.exception.HttpServerException;
import net.devstudy.httpserver.io.utils.DataUtils;
import net.devstudy.matcher.CauseMatcher;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultHtmlTemplateManagerTest {

	private DefaultHtmlTemplateManager defaultHtmlTemplateManager;

	@Before
	public void before() {
		defaultHtmlTemplateManager = spy(DefaultHtmlTemplateManager.class);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private void setTemplate(String templateName, String templateContent) {
		when(defaultHtmlTemplateManager.getClasspathResource("html/templates/" + templateName))
				.thenReturn(new ByteArrayInputStream(templateContent.getBytes(StandardCharsets.UTF_8)));
	}

	@Test
	public void testTemplateWithoutParams() {
		setTemplate("test", "templateContent");
		String result = defaultHtmlTemplateManager.processTemplate("test", new HashMap<String, Object>());
		assertEquals("templateContent", result);
	}
	
	@Test
	public void testTemplateWithParam() {
		setTemplate("test", "templateContent ${PARAM1}!");
		String result = defaultHtmlTemplateManager.processTemplate("test", DataUtils.buildMap(new Object[][]{{"PARAM1", "Hello"}}));
		assertEquals("templateContent Hello!", result);
	}
	
	@Test
	public void testTemplateWithDuplicateParams() {
		setTemplate("test", "templateContent ${PARAM1} / ${PARAM1}!");
		String result = defaultHtmlTemplateManager.processTemplate("test", DataUtils.buildMap(new Object[][]{{"PARAM1", "Hello"}}));
		assertEquals("templateContent Hello / Hello!", result);
	}
	
	@Test
	public void testClasspathResourceNotFound() {
		thrown.expect(HttpServerException.class);
		thrown.expectMessage(new IsEqual<String>("Classpath resource \"html/templates/not-found\" not found"));
		
		defaultHtmlTemplateManager.processTemplate("not-found", new HashMap<String, Object>());
	}
	
	@Test
	public void testIOException() throws IOException {
		thrown.expect(HttpServerException.class);
		thrown.expectMessage(new IsEqual<String>("Can't load template: io-error"));
		thrown.expectCause(new CauseMatcher(IOException.class, "Underlying input stream returned zero bytes"));
		when(defaultHtmlTemplateManager.getClasspathResource("html/templates/io-error")).thenReturn(mock(InputStream.class));
		
		defaultHtmlTemplateManager.processTemplate("io-error", new HashMap<String, Object>());
	}
}

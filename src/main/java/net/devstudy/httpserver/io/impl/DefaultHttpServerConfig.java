package net.devstudy.httpserver.io.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ThreadFactory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.devstudy.httpserver.io.HandlerConfig;
import net.devstudy.httpserver.io.HtmlTemplateManager;
import net.devstudy.httpserver.io.HttpHandler;
import net.devstudy.httpserver.io.HttpServerContext;
import net.devstudy.httpserver.io.ServerInfo;
import net.devstudy.httpserver.io.config.HttpClientSocketHandler;
import net.devstudy.httpserver.io.config.HttpRequestDispatcher;
import net.devstudy.httpserver.io.config.HttpRequestParser;
import net.devstudy.httpserver.io.config.HttpResponseBuilder;
import net.devstudy.httpserver.io.config.HttpResponseWriter;
import net.devstudy.httpserver.io.config.HttpServerConfig;
import net.devstudy.httpserver.io.exception.HttpServerConfigException;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
class DefaultHttpServerConfig implements HttpServerConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHttpServerConfig.class);

	private final Properties serverProperties = new Properties();
	private final Properties statusesProperties = new Properties();
	private final Properties mimeTypesProperties = new Properties();
	private final Map<String, HttpHandler> httpHandlers;
	private final BasicDataSource dataSource;
	private final Path rootPath;
	private final HttpServerContext httpServerContext;
	private final HttpRequestParser httpRequestParser;
	private final HttpResponseWriter httpResponseWriter;
	private final HttpResponseBuilder httpResponseBuilder;
	private final HttpRequestDispatcher httpRequestDispatcher;
	private final HttpHandler defaultHttpHandler;
	private final ThreadFactory workerThreadFactory;
	private final HtmlTemplateManager htmlTemplateManager;
	private final ServerInfo serverInfo;
	private final List<String> staticExpiresExtensions;
	private final int staticExpiresDays;

	@SuppressWarnings("unchecked")
	DefaultHttpServerConfig(HandlerConfig handlerConfig, Properties overrideServerProperties) {
		super();
		loadAllProperties(overrideServerProperties);
		this.httpHandlers = handlerConfig != null ? handlerConfig.toMap() : (Map<String, HttpHandler>) Collections.EMPTY_MAP;
		this.rootPath = createRootPath();
		this.dataSource = createBasicDataSource();
		this.serverInfo = createServerInfo();
		this.staticExpiresDays = Integer.parseInt(this.serverProperties.getProperty("webapp.static.expires.days"));
		this.staticExpiresExtensions = Arrays.asList(this.serverProperties.getProperty("webapp.static.expires.extensions").split(","));

		// Create default implementations
		this.httpServerContext = new DefaultHttpServerContext(this);
		this.httpRequestParser = new DefaultHttpRequestParser();
		this.httpResponseWriter = new DefaultHttpResponseWriter(this);
		this.httpResponseBuilder = new DefaultHttpResponseBuilder(this);
		this.defaultHttpHandler = new DefaultHttpHandler();
		this.httpRequestDispatcher = new DefaultHttpRequestDispatcher(defaultHttpHandler, this.httpHandlers);
		this.workerThreadFactory = new DefaultThreadFactory();
		this.htmlTemplateManager = new DefaultHtmlTemplateManager();
	}

	protected void loadAllProperties(Properties overrideServerProperties) {
		ClassLoader classLoader = DefaultHttpServerConfig.class.getClassLoader();
		loadProperties(this.statusesProperties, classLoader, "statuses.properties");
		loadProperties(this.mimeTypesProperties, classLoader, "mime-types.properties");
		loadProperties(this.serverProperties, classLoader, "server.properties");
		if (overrideServerProperties != null) {
			LOGGER.info("Overrides default server properties");
			this.serverProperties.putAll(overrideServerProperties);
		}
		logServerProperties();
	}

	protected void logServerProperties() {
		if (LOGGER.isDebugEnabled()) {
			StringBuilder res = new StringBuilder("Current server properties is:\n");
			for (Map.Entry<Object, Object> entry : this.serverProperties.entrySet()) {
				res.append(entry.getKey()).append('=').append(entry.getValue()).append('\n');
			}
			LOGGER.debug(res.toString());
		}
	}

	protected void loadProperties(Properties properties, ClassLoader classLoader, String resource) {
		try (InputStream in = classLoader.getResourceAsStream(resource)) {
			if (in != null) {
				properties.load(in);
				LOGGER.debug("Successful loaded properties from classpath resource: {}", resource);
			} else {
				throw new HttpServerConfigException("Classpath resource not found: " + resource);
			}
		} catch (IOException e) {
			throw new HttpServerConfigException("Can't load properties from resource: " + resource, e);
		}
	}

	protected ServerInfo createServerInfo() {
		ServerInfo si = new ServerInfo(
				serverProperties.getProperty("server.name"), 
				Integer.parseInt(serverProperties.getProperty("server.port")),
				Integer.parseInt(serverProperties.getProperty("server.thread.count")));
		if (si.getThreadCount() < 0) {
			throw new HttpServerConfigException("server.thread.count should be >= 0");
		}
		if (si.getPort() < 0 || si.getPort() > 65535) {
			throw new HttpServerConfigException("server.port should be between 0 and 65535");
		}
		return si;
	}

	protected Path createRootPath() {
		Path path = Paths.get(new File(this.serverProperties.getProperty("wepapp.static.dir.root")).getAbsoluteFile().toURI());
		if (!Files.exists(path)) {
			throw new HttpServerConfigException("Root path not found: " + path.toString());
		}
		if (!Files.isDirectory(path)) {
			throw new HttpServerConfigException("Root path is not directory: " + path.toString());
		}
		LOGGER.info("Root path is {}", path.toAbsolutePath());
		return path;
	}

	protected BasicDataSource createBasicDataSource() {
		BasicDataSource ds = null;
		if (Boolean.parseBoolean(serverProperties.getProperty("db.datasource.enabled"))) {
			ds = new BasicDataSource();
			ds.setDefaultAutoCommit(false);
			ds.setRollbackOnReturn(true);
			ds.setDriverClassName(Objects.requireNonNull(serverProperties.getProperty("db.datasource.driver")));
			ds.setUrl(Objects.requireNonNull(serverProperties.getProperty("db.datasource.url")));
			ds.setUsername(Objects.requireNonNull(serverProperties.getProperty("db.datasource.username")));
			ds.setPassword(Objects.requireNonNull(serverProperties.getProperty("db.datasource.password")));
			ds.setInitialSize(Integer.parseInt(Objects.requireNonNull(serverProperties.getProperty("db.datasource.pool.initSize"))));
			ds.setMaxTotal(Integer.parseInt(Objects.requireNonNull(serverProperties.getProperty("db.datasource.pool.maxSize"))));
			LOGGER.info("Datasource is enabled. JDBC url is {}", ds.getUrl());
		} else {
			LOGGER.info("Datasource is disabled");
		}
		return ds;
	}

	@Override
	public ServerInfo getServerInfo() {
		return serverInfo;
	}

	@Override
	public String getStatusMessage(int statusCode) {
		String message = statusesProperties.getProperty(String.valueOf(statusCode));
		return message != null ? message : statusesProperties.getProperty("500");
	}

	@Override
	public HttpRequestParser getHttpRequestParser() {
		return httpRequestParser;
	}

	@Override
	public HttpResponseWriter getHttpResponseWriter() {
		return httpResponseWriter;
	}

	@Override
	public HttpResponseBuilder getHttpResponseBuilder() {
		return httpResponseBuilder;
	}

	@Override
	public HttpServerContext getHttpServerContext() {
		return httpServerContext;
	}

	@Override
	public HttpRequestDispatcher getHttpRequestDispatcher() {
		return httpRequestDispatcher;
	}

	@Override
	public ThreadFactory getWorkerThreadFactory() {
		return workerThreadFactory;
	}
	
	@Override
	public HttpClientSocketHandler buildNewHttpClientSocketHandler(Socket clientSocket) {
		return new DefaultHttpClientSocketHandler(clientSocket, this);
	}

	protected Properties getServerProperties() {
		return serverProperties;
	}

	protected Properties getStatusesProperties() {
		return statusesProperties;
	}

	protected Properties getMimeTypesProperties() {
		return mimeTypesProperties;
	}

	protected Map<String, HttpHandler> getHttpHandlers() {
		return httpHandlers;
	}

	protected BasicDataSource getDataSource() {
		return dataSource;
	}

	protected Path getRootPath() {
		return rootPath;
	}

	protected HttpHandler getDefaultHttpHandler() {
		return defaultHttpHandler;
	}

	protected HtmlTemplateManager getHtmlTemplateManager() {
		return htmlTemplateManager;
	}

	protected List<String> getStaticExpiresExtensions() {
		return staticExpiresExtensions;
	}

	protected int getStaticExpiresDays() {
		return staticExpiresDays;
	}

	@Override
	public void close() {
		if (dataSource != null) {
			try {
				dataSource.close();
			} catch (SQLException e) {
				LOGGER.error("Close datasource failed: " + e.getMessage(), e);
			}
		}
		LOGGER.info("DefaultHttpServerConfig closed");
	}
}

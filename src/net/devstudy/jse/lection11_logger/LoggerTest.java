package net.devstudy.jse.lection11_logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class LoggerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerTest.class);

	public static void main(String[] args) {
		LOGGER.trace("Hello world");
	}
}

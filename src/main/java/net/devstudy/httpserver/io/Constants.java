package net.devstudy.httpserver.io;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public final class Constants {

	public static final String GET = "GET";
	
	public static final String POST = "POST";
	
	public static final String HEAD = "HEAD";
	
	public static final List<String> ALLOWED_METHODS = Collections.unmodifiableList(Arrays.asList(new String[]{GET, POST, HEAD}));
	
	private Constants() {
	}

}

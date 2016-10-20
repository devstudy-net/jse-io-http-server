package net.devstudy.httpserver.io.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public final class DataUtils {

	public static Map<String, Object> buildMap(Object[][] data) {
		Map<String, Object> map = new HashMap<>();
		for(Object[] row : data) {
			map.put(String.valueOf(row[0]), row[1]);
		}
		return Collections.unmodifiableMap(map);
	}
	
	public static List<String> convertToLineList(String message) {
		List<String> list = new LinkedList<>();
		int start = 0;
		for (int i = 1; i < message.length(); i++) {
			if (message.charAt(i) == '\n' && message.charAt(i - 1) == '\r') {
				list.add(message.substring(start, i - 1));
				start = i + 1;
			}
		}
		if (message.length() > 0) {
			list.add(message.substring(start));
		}
		return list;
	}
	
	private DataUtils(){
	}
}

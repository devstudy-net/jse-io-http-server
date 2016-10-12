package net.devstudy.jse.lection04_interfaces;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ObjectToStringConverter implements Converter<Object, String> {
	@Override
	public String convert(Object source) {
		return String.valueOf(source);
	}
}

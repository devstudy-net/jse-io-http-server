package net.devstudy.jse.lection03_classes_static;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class StringToIntegerConverter extends Converter<String, Integer>{

	@Override
	public Integer convert(String source) {
		return Integer.parseInt(source);
	}
}

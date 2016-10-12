package net.devstudy.jse.lection04_interfaces;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public interface Converter<S, D> {
	D convert(S source);
}

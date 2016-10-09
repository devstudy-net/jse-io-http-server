package net.devstudy.jse.lection02_inheritance_polymorph;

import net.devstudy.jse.lection01_classes_objects.home.LinkedList;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class Queue extends LinkedList {
	public int get() {
		return remove(0);
	}
}

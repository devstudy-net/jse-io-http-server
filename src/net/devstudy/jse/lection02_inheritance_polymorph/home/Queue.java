package net.devstudy.jse.lection02_inheritance_polymorph.home;

import net.devstudy.jse.lection02_inheritance_polymorph.LinkedList;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class Queue extends DataStructure {

	public Queue() {
		super(new LinkedList());
	}
}

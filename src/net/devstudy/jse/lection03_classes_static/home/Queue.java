package net.devstudy.jse.lection03_classes_static.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class Queue<T> extends DataStructure<T> {

	public Queue() {
		super(new LinkedList<T>());
	}
	
	public Queue(LinkedList<T> dataSet) {
		super(dataSet);
	}
	
	@Override
	protected int getCurrentIndex() {
		return 0;
	}
}

package net.devstudy.jse.lection05_exceptions.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class Queue<T> extends DataStructure<T> {

	public Queue() {
		super(new LinkedList<T>());
	}
	
	protected Queue(DataSet<T> dataSet) {
		super(dataSet);
	}
	
	@Override
	protected int getCurrentIndex() {
		return 0;
	}
	
	@Override
	protected RuntimeException createEmptyExceptionInstance() {
		return new QueueIsEmptyException();
	}
	
	public static class QueueIsEmptyException extends RuntimeException {
		private static final long serialVersionUID = 5611538616921447536L;
		private QueueIsEmptyException(){
			super("Current queue is empty");
		}
	}
}

package net.devstudy.jse.lection09_threads.home;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public interface Task {

	void start();
	
	void interrupt();
	
	int getPercentProgress();
}

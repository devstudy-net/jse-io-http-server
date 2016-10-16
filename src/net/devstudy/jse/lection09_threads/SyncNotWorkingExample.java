package net.devstudy.jse.lection09_threads;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class SyncNotWorkingExample {

	public static void main(String[] args) {
		final Object monitor = new Object();
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized(monitor) {
						for (int i = 0; i < 5; i++) {
							System.out.println(Thread.currentThread().getName() + "->" + i);
							try {
								TimeUnit.MILLISECONDS.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}, "Thread-" + i).start();
		}
	}

}

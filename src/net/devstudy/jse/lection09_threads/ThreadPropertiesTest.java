package net.devstudy.jse.lection09_threads;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ThreadPropertiesTest {
	public static void main(String[] args) {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				throw new RuntimeException();
			}
		});
		th.setDaemon(false);
		th.setName("Test thread");
		th.setPriority(Thread.MAX_PRIORITY);
		th.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.err.println("Exception in the thread: " + t);
				e.printStackTrace();
			}
		});
		th.start();
		System.out.println("Started");
	}
}

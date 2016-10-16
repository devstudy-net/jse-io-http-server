package net.devstudy.jse.lection09_threads;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ThreadMethods {
	public static void main(String[] args) throws InterruptedException {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(4);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Thread.dumpStack();
			}
		}, "runnable-thread");
		System.out.println("ThreadState=" + th.getState());
		th.start();
		System.out.println("ThreadState=" + th.getState());
		Thread.sleep(1000);
		System.out.println("ThreadId=" + th.getId());
		System.out.println("ThreadState=" + th.getState());
		System.out.println("ThreadState=" + Arrays.toString(th.getStackTrace()));
		Thread.dumpStack(); // Static method!!!!!!!
		System.out.println("isAlive=" + th.isAlive());
		th.join();
		System.out.println("Completed");
	}
}

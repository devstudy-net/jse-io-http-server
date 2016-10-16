package net.devstudy.jse.lection09_threads;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ThreadTest extends Thread {
	public ThreadTest() {
		super("ThreadTest");
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(i);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("ThreadTest finished");
	}

	public static void main(String[] args) {
		Thread th = new ThreadTest();
		th.start(); // th.run();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main finished");
	}
}

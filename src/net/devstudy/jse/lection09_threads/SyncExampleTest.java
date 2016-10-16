package net.devstudy.jse.lection09_threads;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class SyncExampleTest {

	public static void main(String[] args) throws InterruptedException {
		final SyncExample syncExample = new SyncExample();
		Thread producer = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					syncExample.addString(String.valueOf(i));
				}
			}
		}, "Producer");
		Thread printer = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					String s = syncExample.getString();
					if (s != null) {
						System.out.println(s);
					} else {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							break;
						}
					}
				}
			}
		}, "Printer");
		producer.start();
		printer.start();
		System.out.println("Threads started");
		TimeUnit.SECONDS.sleep(500);
		printer.interrupt();
		System.out.println("Threads stopped");
	}

}

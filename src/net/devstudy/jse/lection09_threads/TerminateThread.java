package net.devstudy.jse.lection09_threads;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class TerminateThread implements Runnable {
	private Thread thread;

	public TerminateThread() {
		thread = new Thread(this, "TerminateThread");
	}

	public void start() {
		thread.start();
	}

	public void stop() {
		// thread.stop();
		// thread.destroy();
		// thread.suspend();
		thread.interrupt();
	}

	@Override
	public void run() {
		while (!thread.isInterrupted()) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;//thread.interrupt();
			}
			System.out.println(new Date());
		}
	}

	public static void main(String[] args) {
		TerminateThread t = new TerminateThread();
		t.start();
		while (true) {
			String cmd = new Scanner(System.in).nextLine();
			if ("q".equalsIgnoreCase(cmd)) {
				t.stop();
				System.out.println("Finished");
				break;
			}
		}
	}
}

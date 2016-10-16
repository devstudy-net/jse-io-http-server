package net.devstudy.jse.lection09_threads;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ThreadLocalTest {
	static final ThreadLocal<String> name = new ThreadLocal<>();
	static int var;
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					name.set(Thread.currentThread().getName());
					function1();
					try {
						TimeUnit.MILLISECONDS.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(name.get());
					function2();
				}
			}).start();
		}
	}
	
	static void function1(){
		name.set(Thread.currentThread().getName());
		var = 0;
	}
	
	static void function2(){
		System.out.println(name.get());
		System.out.println(var);
	}

	static class SimpleVar<T> {
		T value;

		public synchronized T get() {
			return value;
		}

		public synchronized void set(T value) {
			this.value = value;
		}
	}

}

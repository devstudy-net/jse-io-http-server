package net.devstudy.jse.lection11_logger;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class Test {

	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<20;i++) {
			//System.out.println(i);
			TimeUnit.SECONDS.sleep(1);
		}
		
	}

}

package net.devstudy.jse.lection12_junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class TestEnvironment {
	@BeforeClass // один раз перед запуском класса
	public static void beforeClass() {
		System.out.println("beforeClass");
	}

	@Before // Перед КАЖДЫМ тестом
	public void before() {
		System.out.println("before");
	}

	@Test // Тест1
	public void test1() {
		System.out.println("test1");
	}

	@Test // Тест2
	public void test2() {
		System.out.println("test2");
	}

	@After // После КАЖДОГО тестом
	public void after() {
		System.out.println("after");
	}

	@AfterClass // один раз после запуска класса
	public static void afterClass() {
		System.out.println("afterClass");
	}
}

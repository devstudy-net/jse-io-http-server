package net.devstudy.jse.lection04_interfaces;

import java.util.Arrays;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class CallbackExample {

	interface Callback<T> {
		void updateArrayElement(T[] array, int index);
	}

	public static void main(String[] args) {
		Integer[] array = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 };
		System.out.println(Arrays.toString(array));
		executeUpdate(array, new Callback<Integer>() {
			@Override
			public void updateArrayElement(Integer[] array, int index) {
				array[index] = (int) (Math.log(array[index]) / Math.log(2));
			}
		});
		System.out.println(Arrays.toString(array));
		executeUpdate(array, new Callback<Integer>() {
			@Override
			public void updateArrayElement(Integer[] array, int index) {
				array[index] *= 2;
			}
		});
		System.out.println(Arrays.toString(array));
	}

	private static <T> void executeUpdate(T[] array, Callback<T> function) {
		for (int i = 0; i < array.length; i++) {
			function.updateArrayElement(array, i);
		}
	}

}

package net.devstudy.jse.lection03_classes_static.home;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DataUtils {
	public static <T> DynaArray<T> newDynaArray() {
		return new DynaArray<T>();
	}

	public static <T> DataSet<T> newImmutableDataSet(final DataSet<T> dataSet) {
		return null; // Использовать анонимный внутренний класс
	}

	public static <T> DataSet<T> newImmutableDataSet(final T[] array) {
		return null; // Использовать анонимный внутренний класс
	}

	public static <T> DataSet<T> newImmutableDataSet(final T element, final T... elements) {
		return null; // Использовать анонимный внутренний класс
	}

	public static <T> LinkedList<T> newLinkedListWithDuplicates(final DataSet<T>... dataSets) {
		return null; // Использовать отпимальный алгоритм для объединения
	}

	public static <T> DataSet<T> newDataSetWithoutDuplicates(final DataSet<T>... dataSets) {
		return null; // Использовать внутренний класс для реализации логики по
						// удалению дубликатов
	}

	/*public static <T> Queue<T> newQueue(final DataSet<T> dataSet) {
		return null; // Учитывать эффективность массива и связного списка при
						// создании очереди
	}

	public static <T> Stack<T> newStack(final DataSet<T> dataSet) {
		return null; // Учитывать эффективность массива и связного списка при
						// создании стэка
	}

	public static <T> Queue<T> newQueue(final T[] array) {
		return null; // Учитывать эффективность массива и связного списка при
						// создании очереди
	}

	public static <T> Stack<T> newStack(final T[] array) {
		return null; // Учитывать эффективность массива и связного списка при
						// создании стэка
	}*/

	private DataUtils() {
	}
}

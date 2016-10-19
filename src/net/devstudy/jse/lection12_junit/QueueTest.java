package net.devstudy.jse.lection12_junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import net.devstudy.jse.lection05_exceptions.home.DataSet;
import net.devstudy.jse.lection05_exceptions.home.Queue;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class QueueTest {
	private DataSet<Integer> dataSet;

	private Queue<Integer> createQueue() {
		return new Queue<Integer>(dataSet) {
		};
	}

	@Before
	@SuppressWarnings("unchecked")
	public void before() {
		dataSet = mock(DataSet.class);
	}

	@Test
	public void testAdd() {
		Queue<Integer> queue = createQueue();
		queue.add(2);
		verify(dataSet).add(2);
	}

	@Test
	public void testToArray() {
		Queue<Integer> queue = createQueue();
		when(dataSet.toArray()).thenReturn(new Integer[] { 1, 2, 3 });
		String s = queue.toString();
		verify(dataSet).toArray();
		assertEquals("[1, 2, 3]", s);
	}
}

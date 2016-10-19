package net.devstudy.jse.lection12_junit;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class MockitoTest {
	@Test
	public void test1() {
		List list = mock(List.class);
		Iterator it = mock(Iterator.class);
		when(list.size()).thenReturn(3);
		when(list.iterator()).thenReturn(it);
		when(it.next()).thenReturn(1, 2, 3);
		when(list.get(anyInt())).thenAnswer(new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				int index = invocation.getArgumentAt(0, Integer.TYPE);
				if (index >= 0 && index <= 2) {
					return index + 1;
				}
				throw new IndexOutOfBoundsException("invalid index");
			}
		});
		when(list.get(0)).thenReturn(1);
		when(list.get(1)).thenReturn(2);
		when(list.get(2)).thenReturn(3);
		when(it.hasNext()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
		when(list.remove(anyInt())).thenThrow(new IndexOutOfBoundsException("invalid index"));
		doThrow(new UnsupportedOperationException("Clear is not supported")).when(list).clear();
		// code here
		verify(it, times(3)).next();
		verify(it, never()).remove();
		verify(list).size();
	}
}

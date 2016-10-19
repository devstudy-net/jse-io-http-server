package net.devstudy.jse.lection12_junit;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import net.devstudy.jse.lection08_io.home.DefaultIOUtils;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class DefaultIOUtilsTestMOckExample {
	private Reader r;
	private Writer wr;

	@Before
	public void before() {
		r = mock(Reader.class);
		wr = mock(Writer.class);
	}

	@Test
	public void testSuccessfulReplace() throws IOException {
		DefaultIOUtils io = new DefaultIOUtils();
		String inChars = "37";
		String outChars = "40";
		final char[] testData = { '1', '3', '4', '7', '0' };
		when(r.read(any(char[].class))).thenAnswer(new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				char[] buffer = (char[]) args[0];
				for (int i = 0; i < testData.length; i++) {
					buffer[i] = testData[i];
				}
				return testData.length;
			}
		}).thenReturn(-1);
		io.replaceChars(r, wr, inChars, outChars);
		char[] buffer = new char[8192];
		buffer[0] = '1';
		buffer[1] = '4';
		buffer[2] = '4';
		buffer[3] = '0';
		buffer[4] = '0';
		verify(wr).write(buffer, 0, 5);
		verify(wr).flush();
		verify(wr).close();
		verify(r).close();
	}
}

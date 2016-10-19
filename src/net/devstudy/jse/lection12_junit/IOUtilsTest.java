package net.devstudy.jse.lection12_junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.devstudy.jse.lection08_io.home.DefaultIOUtils;
import net.devstudy.jse.lection08_io.home.IOUtils;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class IOUtilsTest {
	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	private IOUtils ioUtils;

	@Before
	public void before() {
		ioUtils = new DefaultIOUtils();
	}

	@Test
	public void testFlushAndClose() throws IOException {
		Reader r = mock(Reader.class);
		Writer wr = mock(Writer.class);
		when(r.read(any(char[].class))).thenReturn(-1);
		ioUtils.replaceChars(r, wr, "", "");
		verify(r).close();
		verify(wr).flush();
		verify(wr).close();
	}

	@Test
	public void testCloseIfIOExceptionThrown() throws IOException {
		Reader r = mock(Reader.class);
		Writer wr = mock(Writer.class);
		when(r.read(any(char[].class))).thenThrow(new IOException("test"));
		try {
			ioUtils.replaceChars(r, wr, "", "");
		} catch (IOException e) {
		}
		verify(r).close();
		verify(wr, never()).flush();
		verify(wr).close();
	}

	@Test
	public void testReplace() throws IOException {
		StringReader r = new StringReader("1234567890");
		String inChars = "357";
		String outChars = "480";
		StringWriter wr = new StringWriter();
		ioUtils.replaceChars(r, wr, inChars, outChars);
		assertEquals("1244860890", wr.toString());
	}

	@Test
	public void testNullPointerExceptionWhenInIsNull() throws IOException {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage(new NotBlankStringMatcher());
		ioUtils.replaceChars(null, mock(Writer.class), "", "");
	}

	@Test
	public void testNullPointerExceptionWhenOutIsNull() throws IOException {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage(new NotBlankStringMatcher());
		ioUtils.replaceChars(mock(Reader.class), null, "", "");
	}

	@Test
	public void testIllegalArgumentException() throws IOException {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(new NotBlankStringMatcher());
		ioUtils.replaceChars(mock(Reader.class), mock(Writer.class), "1", "12");
	}

	@Test
	public void testIOException() throws IOException {
		thrown.expect(IOException.class);
		thrown.expectMessage("IO Error");
		Reader r = mock(Reader.class);
		Writer wr = mock(Writer.class);
		when(r.read(any(char[].class))).thenThrow(new IOException("IO Error"));
		ioUtils.replaceChars(r, wr, "", "");
	}

	@Test
	public void testNullBothStrings() throws IOException {
		StringWriter wr = new StringWriter();
		ioUtils.replaceChars(new StringReader("1234567890"), wr, null, null);
		assertEquals("1234567890", wr.toString());
	}

	@Test
	public void testNullOutCharsString() throws IOException {
		StringWriter wr = new StringWriter();
		ioUtils.replaceChars(new StringReader("1234567890"), wr, "", null);
		assertEquals("1234567890", wr.toString());
	}

	@Test
	public void testNullInCharsString() throws IOException {
		StringWriter wr = new StringWriter();
		ioUtils.replaceChars(new StringReader("1234567890"), wr, null, "");
		assertEquals("1234567890", wr.toString());
	}

	@Test
	public void testEmptyBothStrings() throws IOException {
		StringWriter wr = new StringWriter();
		ioUtils.replaceChars(new StringReader("1234567890"), wr, "", "");
		assertEquals("1234567890", wr.toString());
	}

	private static class NotBlankStringMatcher extends TypeSafeMatcher<String> {
		@Override
		public void describeTo(Description description) {
			description.appendText("Expected not blank exception message");
		}

		@Override
		protected boolean matchesSafely(String item) {
			return item != null && !"".equals(item.trim());
		}
	}
}

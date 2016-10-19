package net.devstudy.jse.lection12_junit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ExternalResourcesTest {
	@Rule
	public final TemporaryFolder folder = new TemporaryFolder(new File("test"));
	// @Rule
	public final ExternalResource resource = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			// server.connect();
		}

		@Override
		protected void after() {
			// server.disconnect();
		}
	};

	private void writeToFile(File file) throws IOException {
		try (Writer wr = new FileWriter(file)) {
			wr.write("test");
			wr.flush();
		}
	}

	@Test
	public void testTempFile() throws IOException {
		File file = folder.newFile();
		writeToFile(file);
		List<String> list = Files.readAllLines(Paths.get(file.toURI()));
		assertEquals(1, list.size());
		assertEquals("test", list.get(0));
	}
}

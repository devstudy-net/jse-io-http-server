package net.devstudy.jse.lection08_io.home;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public interface IOUtils {

	void replaceChars(Reader in, Writer out, String inChars, String outChars) throws NullPointerException, IllegalArgumentException, IOException;
	
	List<Path> findText(Path rootDir, String textToFind, boolean ignoreCase) throws NullPointerException, IllegalArgumentException, IOException;
}

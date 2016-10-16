package net.devstudy.jse.lection08_io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class FileTest {

	public static void main(String[] args) {
		File file = new File("src/net/devstudy/jse/lection08_io");
		System.out.println("exists=" + file.exists());
		System.out.println("isFile=" + file.isFile());
		System.out.println("isDirectory=" + file.isDirectory());
		System.out.println("isHidden=" + file.isHidden());
		System.out.println("isAbsolute=" + file.isAbsolute());
		System.out.println("getName=" + file.getName());
		System.out.println("path=" + file.getPath());
		System.out.println("absolute=" + file.getAbsolutePath());
		System.out.println("length=" + file.length());
		System.out.println("files=" + Arrays.toString(file.list()));
		System.out.println("*.java=" + Arrays.toString(file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		})));
		System.out.println("files=" + Arrays.toString(file.listFiles()));
		System.out.println("lastModified=" + new Date(file.lastModified()));
		System.out.println("canExecute=" + file.canExecute());
		System.out.println("canRead=" + file.canRead());
		System.out.println("canWrite=" + file.canWrite());
		System.out.println("getParent=" + file.getParent());
	}

}

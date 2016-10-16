package net.devstudy.jse.lection08_io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class PathTest {

	public static void main(String[] args) throws IOException {
		Path file = Paths.get("src/net/devstudy/jse/lection08_io");
		System.out.println("exists=" + Files.exists(file));
		System.out.println("isFile=" + Files.isRegularFile(file));
		System.out.println("isDirectory=" + Files.isDirectory(file));
		System.out.println("isHidden=" + Files.isHidden(file));
		System.out.println("isAbsolute=" + file.isAbsolute());
		System.out.println("getName=" + file.getFileName());
		System.out.println("path=" + file);
		System.out.println("absolute=" + file.toAbsolutePath());
		System.out.println("length=" + Files.size(file));
		List<String> fileNames = new ArrayList<>();
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(file)) {
			for (Path path : directoryStream) {
				fileNames.add(path.toString());
			}
		}
		System.out.println("files=" + fileNames);
		System.out.println("lastModified=" + Files.getLastModifiedTime(file));
		System.out.println("canExecute=" + Files.isExecutable(file));
		System.out.println("canRead=" + Files.isReadable(file));
		System.out.println("canWrite=" + Files.isWritable(file));
		System.out.println("getParent=" + file.getParent());
	}

}

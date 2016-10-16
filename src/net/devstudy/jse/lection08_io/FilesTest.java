package net.devstudy.jse.lection08_io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class FilesTest {

	public static void main(String[] args) throws IOException {
		Files.deleteIfExists(Paths.get("text-file-copy.txt"));
		Files.copy(Paths.get("text-file.txt"), Paths.get("text-file-copy.txt"));
		List<String> list = Files.readAllLines(Paths.get("text-file-copy.txt"));
		System.out.println(list);
		Files.delete(Paths.get("text-file-copy.txt"));
		Files.write(Paths.get("text-file-copy.txt"), list);
		final List<Path> foundPaths = new ArrayList<>();
		Files.walkFileTree(Paths.get(""), new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				if (file.getFileName().toString().endsWith(".java")) {
					foundPaths.add(file);
				}
				return FileVisitResult.CONTINUE;
			}
		});
		for (Path file : foundPaths) {
			System.out.println(file);
		}
	}

}

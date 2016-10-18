package net.devstudy.jse.lection09_threads.home;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public class CopyFileTaskExecutorService implements Task, Runnable {
	private final long sourceSize;
	private final Path sourcePath;
	private final Path destinationPath;
	private final ExecutorService executorService;
	private volatile long copied;
	
	public CopyFileTaskExecutorService(Path sourcePath, Path destinationPath) throws IOException {
		super();
		this.sourcePath = sourcePath;
		this.destinationPath = destinationPath;
		this.sourceSize = Files.size(sourcePath);
		this.copied = 0;
		this.executorService = Executors.newSingleThreadExecutor();
	}

	@Override
	public void start() {
		if(executorService.isShutdown()){
			throw new IllegalArgumentException("CopyFileTaskExecutorService already executed");
		}
		executorService.submit(this);
	}

	@Override
	public void interrupt() {
		this.executorService.shutdownNow();
	}

	@Override
	public int getPercentProgress() {
		return (int) (this.copied * 100 / sourceSize);
	}

	@Override
	public void run() {
		byte[] buffer = new byte[8192];
		try(InputStream in = new FileInputStream(sourcePath.toFile()); 
			OutputStream out = new FileOutputStream(destinationPath.toFile())) {
			while(!Thread.interrupted()){
				int read = in.read(buffer);
				if(read == -1) {
					break;
				}
				out.write(buffer, 0, read);
				copied += read;
			}
			out.flush();
		} catch (IOException e) {
			System.err.println("Copy failed: "+e.getMessage());
			e.printStackTrace();
		}
		this.executorService.shutdown();
	}
}

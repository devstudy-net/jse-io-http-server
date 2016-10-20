package net.devstudy.httpserver.io;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ServerInfo {
	private final String name;
	private final int port;
	private final int threadCount;
	public ServerInfo(String name, int port, int threadCount) {
		super();
		this.name = name;
		this.port = port;
		this.threadCount = threadCount;
	}
	
	public String getName() {
		return name;
	}

	public int getPort() {
		return port;
	}

	public int getThreadCount() {
		return threadCount;
	}

	@Override
	public String toString() {
		return String.format("ServerInfo [name=%s, port=%s, threadCount=%s]", name, port, threadCount == 0 ? "UNLIMITED" : threadCount);
	}
}

package net.devstudy.httpserver.io;
/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ServerInfo {

	private String name;
	private int port;
	private int threadCount;
	public ServerInfo(String name, int port, int threadCount) {
		super();
		this.name = name;
		this.port = port;
		this.threadCount = threadCount;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	@Override
	public String toString() {
		return String.format("ServerInfo [name=%s, port=%s, threadCount=%s]", name, port, threadCount);
	}
}

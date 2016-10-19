package net.devstudy.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ServerSocketTest {

	public static void main(String[] args) throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(9090);
				Socket s = serverSocket.accept();
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true)) {
			String message = "It is simple http server";
			pw.println("HTTP/1.1 200 OK");
			pw.println("Content-Type: text/html");
			pw.println("Content-Length: " + message.length());
			pw.println("Connection: close");
			pw.println();
			pw.println(message);
		}
		System.out.println("Completed");
	}

}

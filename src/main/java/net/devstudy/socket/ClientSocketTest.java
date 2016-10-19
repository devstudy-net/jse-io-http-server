package net.devstudy.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ClientSocketTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		try (Socket s = new Socket("stackoverflow.com", 80); // stackoverflow.com
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
				InputStream in = s.getInputStream()) {
			pw.println("GET / HTTP/1.1");
			pw.println("Host: stackoverflow.com"); // stackoverflow.com
			pw.println("Connection: keep-alive");
			pw.println();
			StringBuilder html = new StringBuilder();
			byte[] bytes = new byte[1024];
			while (true) {
				int read = in.read(bytes);
				if (read == -1) {
					break;
				}
				if (read > 0) {
					html.append(new String(bytes, 0, read, StandardCharsets.UTF_8));
				}
			}
			System.out.println(html);
		}
	}
}

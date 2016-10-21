package net.devstudy.httpserver.io.utils;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 
 * @author devstudy
 * @see http://devstudy.net
 */
public final class HttpUtils {

	public static String normilizeHeaderName(String name) {
		StringBuilder headerName = new StringBuilder(name.trim());
		for (int i = 0; i < headerName.length(); i++) {
			char ch = headerName.charAt(i);
			if (i == 0) {
				toUpper(ch, i, headerName);
			} else if (ch == '-' && i < headerName.length() - 1) {
				toUpper(headerName.charAt(i + 1), i + 1, headerName);
				i++;
			} else {
				if (Character.isUpperCase(ch)) {
					headerName.setCharAt(i, Character.toLowerCase(ch));
				}
			}
		}
		return headerName.toString();
	}

	private static void toUpper(char ch, int index, StringBuilder headerName) {
		if (Character.isLowerCase(ch)) {
			headerName.setCharAt(index, Character.toUpperCase(ch));
		}
	}

	public static String readStartingLineAndHeaders(InputStream inputStream) throws IOException {
		ByteArray byteArray = new ByteArray();
		while (true) {
			int read = inputStream.read();
			if (read == -1) {
				throw new EOFException("InputStream closed");
			}
			byteArray.add((byte) read);
			if (byteArray.isEmptyLine()) {
				break;
			}
		}
		return new String(byteArray.toArray(), StandardCharsets.UTF_8);
	}

	public static String readMessageBody(InputStream inputStream, int contentLength) throws IOException {
		StringBuilder messageBody = new StringBuilder();
		while (contentLength > 0) {
			byte[] messageBytes = new byte[contentLength];
			int read = inputStream.read(messageBytes);
			messageBody.append(new String(messageBytes, 0, read, StandardCharsets.UTF_8));
			contentLength -= read;
		}
		return messageBody.toString();
	}

	public static int getContentLengthIndex(String startingLineAndHeaders) {
		return startingLineAndHeaders.toLowerCase().indexOf(CONTENT_LENGTH);
	}

	public static int getContentLengthValue(String startingLineAndHeaders, int contentLengthIndex) {
		int startCutIndex = contentLengthIndex + CONTENT_LENGTH.length();
		int endCutIndex = startingLineAndHeaders.indexOf("\r\n", startCutIndex);
		if(endCutIndex == -1) {
			endCutIndex = startingLineAndHeaders.length();
		}
		return Integer.parseInt(startingLineAndHeaders.substring(startCutIndex, endCutIndex).trim());
	}

	private static final String CONTENT_LENGTH = "content-length: ";

	/**
	 * 
	 * 
	 * @author devstudy
	 * @see http://devstudy.net
	 */
	private static class ByteArray {
		private byte[] array = new byte[1024];
		private int size;

		private void add(byte value) {
			if (size == array.length) {
				byte[] temp = array;
				array = new byte[array.length * 2];
				System.arraycopy(temp, 0, array, 0, size);
			}
			array[size++] = value;
		}

		private byte[] toArray() {
			return Arrays.copyOf(array, size - 4);
		}

		private boolean isEmptyLine() {
			return array[size - 1] == '\n' && array[size - 2] == '\r' && array[size - 3] == '\n' && array[size - 4] == '\r';
		}
	}

	private HttpUtils() {
	}
}

package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpUtil {
	private static Pattern urlSplitterPattern = Pattern.compile("^(https?:\\/\\/)?([^\\/]+)(\\/.+)?$");

	/**
	 * @param method
	 * @param url
	 * @throws IOException
	 */
	public static HttpResponse request(String method, String url) throws Exception {
		Matcher urlMatcher = HttpUtil.urlSplitterPattern.matcher(url);

		String host;
		String path;

		if (urlMatcher.find()) {
			host = urlMatcher.group(2);
			path = urlMatcher.group(3);
			if (path == null) {
				path = "/";
			}
		} else {
			throw new Exception("Invalid url");
		}

		Socket requestSocket = new Socket(InetAddress.getByName(host), 80);
		PrintWriter tcpBody = new PrintWriter(requestSocket.getOutputStream());

		// Write http request line
		tcpBody.println(method.toUpperCase() + " " + path + " HTTP/1.1");

		// Write http request headers
		tcpBody.println("Host: " + host);

		// Terminate http header by writing empty line
		tcpBody.println();
		tcpBody.flush();

		System.out.println("Sending request");

		// Send request, and prepare response buffered reader
		BufferedReader response = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));

		System.out.println("Response received");

		String currentLine;

		String statusLine = response.readLine();
		String responseHeaders = "";

		// Grab lines for response headers until we get to header/body separator
		while ((currentLine = response.readLine()) != null && !currentLine.equals("")) {
			responseHeaders += currentLine + "\r\n";
		}

		response.close();

		return new HttpResponse(statusLine, responseHeaders);
	}
}

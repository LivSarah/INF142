package util;

public class HttpResponse {
	private String header;
	private String statusLine;
	private String responseHeaders;

	HttpResponse(String statusLine, String responseHeaders) {
		this.header = statusLine + "\r\n" + responseHeaders;
		this.statusLine = statusLine;
		this.responseHeaders = responseHeaders;
	}

	public String getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}

	public String getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(String responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}

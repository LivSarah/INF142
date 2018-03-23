package server;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		WPS server = new WPS(4545, "localhost");

		System.out.println("Server starts at port " + server.getPort() + " and ip " + server.getAddress());

		Thread t = new Thread(server);
		t.start();
	}
}

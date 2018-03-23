package server;

import util.HttpResponse;
import util.HttpUtil;
import util.UdpUtil;

import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
import java.net.*;


public class Server implements Runnable {

	private int port;
	private InetAddress address;
	private DatagramSocket server;
	// Socket socket;

	public Server(int port, String address) throws IOException {
		this.port = port;

		try {
			this.address = InetAddress.getByName(address);

			server = new DatagramSocket(null);

			InetSocketAddress socketAddress = new InetSocketAddress(address, port);
			server.bind(socketAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public InetAddress getAddress() {
		return address;
	}

	@Override
	public void run() {
		DatagramPacket packet = UdpUtil.getPacket(server);

		String url = UdpUtil.getMessage(packet);

		System.out.println("Received message: " + url);

		String returnMessage;
		try {
			HttpResponse response = HttpUtil.request("get", url);

			returnMessage = response.getHeader();
		} catch (Exception e) {
			returnMessage = e.getMessage();
		}

		System.out.println("Returning headers of request");


		UdpUtil.sendMessage(returnMessage, packet.getAddress(), packet.getPort(), server);

		server.close();
	}
}

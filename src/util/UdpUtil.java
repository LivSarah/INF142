package util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpUtil {
	public static void sendMessage(String msg, InetAddress adresse, int port, DatagramSocket socket) {
		byte[] tmp = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(tmp, tmp.length, adresse, port);


		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getMessage(DatagramPacket pk) {
		int length = pk.getLength();

		return new String(pk.getData()).substring(0, length);
	}

	public static DatagramPacket getPacket(DatagramSocket socket) {
		byte[] tmp = new byte[1024];
		DatagramPacket packet = new DatagramPacket(tmp, tmp.length);

		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return packet;
	}
}

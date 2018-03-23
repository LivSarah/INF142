package client;

import java.net.*;
import java.util.Scanner;

import static util.UdpUtil.getMessage;
import static util.UdpUtil.getPacket;
import static util.UdpUtil.sendMessage;


public class Client implements Runnable {
	//Feltvariabler
	private int port;
	private InetAddress adresse;
	private DatagramSocket client;

	//Konstruktør
	public Client(int port, String address) {
		this.port = port;

		try {
			this.adresse = InetAddress.getByName(address);
			client = new DatagramSocket();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	//Get port
	public int getPort() {
		return port;
	}

	//Get IP-adresse
	public InetAddress getAdresse() {
		return adresse;
	}

	//Run-metode, kjøres fra main
	@Override
	public void run() {
		Scanner reader = new Scanner(System.in);

		System.out.println("Skriv url: ");
		String msg = reader.next();
		sendMessage(msg, adresse, port, client);

		DatagramPacket packet = getPacket(client);
		msg = getMessage(packet);
		System.out.println(msg);

		client.close();
	}
}

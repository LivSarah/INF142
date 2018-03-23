package client;

public class Main {
	public static void main(String[] args) {
		Client client = new Client(4545, "localhost");

		System.out.println("Client starts at port " + client.getPort() + " and ip " + client.getAdresse());

		Thread t = new Thread(client);
		t.start();
	}
}

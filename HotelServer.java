import java.io.*;
import java.net.*;

public class HotelServer {
	public static void main(String[] args) {
		final int PORT = 420;
		try (ServerSocket server = new ServerSocket(PORT)) {
			System.out.println("Waiting for client to connect...");
			while (true) {
				try (Socket s = server.accept()) { // wait for next client
					DataInputStream in = new DataInputStream(s.getInputStream());
					DataOutputStream out = new DataOutputStream(s.getOutputStream());
					out.writeUTF("You have connected to the server");
					out.flush();
					boolean done = false;
					
					while (!done) {
						String text = in.readUTF();
						done = text.equalsIgnoreCase("quit");
						out.writeUTF("echo: " + text);
						out.flush();
					}
				} catch (IOException e ) {
					e.printStackTrace();
					System.out.println("Client could not connect.");
				}
			}
		} catch (IOException e ) {
			e.printStackTrace();
			System.out.println("IOException @ main serversocket");
		}
	}
}

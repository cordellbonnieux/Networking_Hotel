import java.io.*;
import java.net.*;

public class HotelServer {
	public static void main(String[] args) {
		
		final int PORT = 1181;
		String user;
		DataInputStream in;
		DataOutputStream out;
		
		/*
		 * RUN THE SERVER
		 */
		try (ServerSocket server = new ServerSocket(PORT)) {
			System.out.println("Waiting for client to connect...");
			while (true) {
				/*
				 * WAIT FOR A CLIENT TO CONNECT
				 */
				try (Socket s = server.accept()) {
					in = new DataInputStream(s.getInputStream());
					out = new DataOutputStream(s.getOutputStream());
					out.writeUTF("You have connected to the server");
					out.flush();
					boolean done = false;
					String text;
					
					while (!done) {
						/*
						 *  USER LOGIN
						 */
						text = in.readUTF().toUpperCase();
						if (text.contentEquals("USER")) {
							System.out.print("USER selected");
							text = in.readUTF();
							user = text;
							out.writeUTF("Hello, " + text);
							out.writeUTF("Enter RESERVE to reserve dates...");
							out.flush();
							/*
							 * RESERVE DATES
							 */
							text = in.readUTF().toUpperCase();
							if (text.contentEquals("RESERVE")) {
								System.out.println("RESERVE selected"); 
								// check date 1
								int start = in.readInt();
								if (start > 30 || start < 1) {
									out.writeUTF("You've entered an invalid date.");
								}
								// check date 2
								int end = in.readInt();
								if (end < start || end == start || end > 31) {
									out.writeUTF("You've entered an invalid date.");
								}
								
							} else {
								/*
								 * NO RESERVE
								 */
								out.writeUTF("Did not recognize input, please enter RESERVE, followed by two valid dates.");
							}

						} else {
							/*
							 * NO USER
							 */
							out.writeUTF("Did not recognize input, please enter USER followed by a username");
						}
					}
				} catch (IOException e ) {
					/*
					 * CLIENT COULD NOT CONNECT
					 */
					e.printStackTrace();
					System.out.println("Client could not connect.");
				}	
			}
		} catch (IOException e ) {
			/*
			 * SERVER FAILED TO START
			 */
			e.printStackTrace();
			System.out.println("IOException @ main serversocket");
		}
	}
}

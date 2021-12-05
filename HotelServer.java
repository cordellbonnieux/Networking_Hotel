import java.io.*;
import java.net.*;

/**
 * This class is used as a server for a one-room hotel. The hotel can receive bookings via a remote client,
 * in this case the, HotelTestClient class.
 * @author Cordell Bonnieux
 *
 */
public class HotelServer {
	public static void main(String[] args) {
		
		final int PORT = 1181;
		String user;
		DataInputStream in;
		DataOutputStream out;
		Hotel hotel = new Hotel();
		
		/*
		 * RUN THE SERVER
		 */
		try (ServerSocket server = new ServerSocket(PORT)) {
			/*
			 * WAIT FOR A CLIENT TO CONNECT
			 */
			System.out.println("Waiting for client to connect...");
			while (true) {
				try (Socket s = server.accept()) {
					/*
					 * CLIENT CONNECTED, READ DATA
					 */
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
							
							while (!done) {
								out.writeUTF("Hello, " + user + " please enter your selection:\nRESERVE\nCANCEL\nAVAIL\nQUIT");
								out.flush();
								text = in.readUTF().toUpperCase();
								/*
								 * USER SELECTION
								 */
								if (text.contentEquals("RESERVE")) {
									System.out.println("RESERVE selected"); 
									// validate start date
									int start = in.readInt();
									if (start < 31 && start > 0) {
										// validate end date
										int end = in.readInt();
										if (end > start && end <= 31) {
											if (hotel.requestReservation(user, start, end)) {
												// reservation successful
												out.writeUTF("Thank you " + user + ", your reservation for December " + start + " to " + end + " has been booked.");
											} else {
												// reservation failed
												out.writeUTF("There was a problem booking your reservation, please try again.");
											}
											out.flush();
											
										} else {
											// invalid end date
											out.writeUTF("Did not recognize input, please enter a valid end date.");
											out.flush();
										}
										
									} else {
										// invalid start date
										out.writeUTF("Did not recognize input, please enter a valid start date.");
										out.flush();
									}
								} else if (text.contentEquals("CANCEL")) {
									
								} else if (text.contentEquals("AVAIL")) {
									
								} else if (text.contentEquals("QUIT")) {
									
								} else {
									/*
									 * BAD INPUT
									 */
									out.writeUTF("Invalid Command: Closing Connection");
									out.flush();
								}
							}
							
							
							
						} else {
							/*
							 * NO USER
							 */
							out.writeUTF("Did not recognize input, please enter USER followed by a username");
							out.flush();
						}
					}
				} catch (IOException e ) {
					/*
					 * CLIENT COULD NOT CONNECT
					 */
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

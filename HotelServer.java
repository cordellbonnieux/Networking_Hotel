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
							text = in.readUTF();
							user = text;
							System.out.println("USER " + user + " selected");
							
							while (!done) {
								out.writeUTF("Hello, " + user + " please enter your selection:\nRESERVE\nCANCEL\nAVAIL\nQUIT");
								out.flush();
								text = in.readUTF().toUpperCase();
								out.writeUTF("you selected: " + text);
								out.flush();
								
								if (text.contentEquals("USER")) {
									/* 
									 * CHANGE USER
									 */
									text = in.readUTF();
									user = text;
									System.out.println("USER " + user + " selected");
					
								} else if (text.contentEquals("RESERVE")) {
									/*
									 * RESERVE DATES
									 */
									System.out.println(user + " selected RESERVE"); 
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
												out.writeUTF("There was a problem booking your reservation for December " + start + " to " + end + ", please try again.");
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
									System.out.println(user + " selected CANCEL"); 
									/*
									 * CANCEL RESERVATIONS
									 */
									if (hotel.cancelReservation(user)) {
										out.writeUTF("Thank you " + user + ", your reservations have been cancelled.");
									} else {
										out.writeUTF("You have no reservations to cancel.");
									}
									out.flush();
									
								} else if (text.contentEquals("AVAIL")) {
									System.out.println(user + " selected AVAIL"); 
									/*
									 * DISPLAY AVAILABILITY
									 */
									out.writeUTF(hotel.reservationInformation());
									out.flush();
									
								} else if (text.contentEquals("QUIT")) {
									System.out.println(user + " selected QUIT"); 
									/*
									 * QUIT
									 */
									out.writeUTF("Closing Connection");
									out.flush();
									done = true;
									
								} else {
									System.out.println(user + " selected BAD INPUT "); 
									/*
									 * BAD INPUT
									 */
									out.writeUTF("Invalid Command: Closing Connection");
									out.flush();
									done = true;
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
					 * CLIENT DISCONNECTED
					 */
					System.out.println("Client disconnected.");
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

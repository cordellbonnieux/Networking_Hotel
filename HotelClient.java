import java.io.*;
import java.net.Socket;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A client with a GUI that the user can used to connect to the Hotel Server
 * @author Cordell Bonnieux
 *
 */
public class HotelClient {
	// Client Connection
	private final int PORT = 1181;
	private final String HOST = "localhost";
	private InputStream input;
	private DataInputStream in;
	private OutputStream output;
	private DataOutputStream out;
	private String inputText;
	private String outputText;

	public static void main(String[] args) {
		//makeGUI("Waiting to connect...", "START");

		
//		// connect to server
//		try (Socket s = new Socket(HOST,PORT)) {
//			// set up stream
//			input = s.getInputStream();
//			output = s.getOutputStream();
//			in = new DataInputStream(input);
//			out = new DataOutputStream(output);
//		
//			inputText = in.readUTF();
//			outputText = "USER";
//			
//			/*
//			 * MAIN LOOP
//			 */
//			boolean connected = true;
//			while (connected) {
//
//				makeGUI(inputText, outputText);
//				
//				
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("Connection to server failed.");
//		}

	}
}

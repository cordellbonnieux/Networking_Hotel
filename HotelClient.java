import java.io.*;
import java.net.Socket;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A client with a GUI that the user can used to connect to the Hotel Server
 * @author Cordell Bonnieux
 *
 */
public class HotelClient extends Application {
	// Client Connection
	private final int PORT = 1181;
	private final String HOST = "localhost";
	private InputStream input;
	private DataInputStream in;
	private OutputStream output;
	private DataOutputStream out;
	
	// UI Structural Elements
	private BorderPane root;
	private Scene scene;
	private Pane center;
	private VBox right;
	
	@Override
	public void start(Stage stage) throws Exception {
		// set up GUI
		root = new BorderPane();
		// makeGUI(root)
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		// add events
		
		// connect to server
		try (Socket s = new Socket(HOST,PORT)) {
			boolean connected = true;
			while (connected) {
				//main loop
			}
		} catch (IOException e) {
			
		}
		
	}
	
	public void makeGUI(BorderPane r) {
		//r.setTop(value);
		r.setCenter(makeCenter());
		r.setRight(makeRight());
	}
	public Pane makeCenter() {
		TextArea output = new TextArea();
		center = new Pane();
		return center;
	}
	public VBox makeRight() {
		// something something
		return right;
	}
}

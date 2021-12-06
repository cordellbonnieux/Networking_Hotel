import javafx.application.Application;
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

public class HotelApp extends Application {
	
	// UI Structural Elements
	// UI sizes...
	private BorderPane root;
	private Scene scene;
	private Pane center;
	private VBox right;
	private HBox top;
	
	//Top section
	private Text title;
	
	//MAIN menu
	private Button main_user;
	private Button main_reserve;
	private Button main_cancel;
	
	//USER menu
	private Text user_heading;
	private TextField user_field;
	private Button user_button;
	
	//RESERVE menu
	private int reserve_start;
	private int reserve_end;
	private TextField reserve_startField;
	private TextField reserve_endField;
	private Text reserve_heading;
	private Text reserve_prompt;
	private Button reserve_submit;
	
	//AVAIL menu
	private Button ok;

	@Override
	public void start(Stage stage) throws Exception {
		// set up FX
		root = new BorderPane();
		scene = new Scene(root);
		
		makeGUI("No connection", "USER");
		
		// show FX
		stage.setScene(scene);
		stage.show();
		
	}
	
	/**
	 * Make GUI
	 * @param n
	 * @param o
	 */
	public void makeGUI(String display, String choice) {
		root.setTop(makeTop());
		root.setCenter(makeCenter(display));
		root.setRight(makeRight(choice));
	}
	
	/**
	 * Make Center
	 * @param n
	 * @return
	 */
	public Pane makeCenter(String display) {
		TextArea output = new TextArea(display);
		center = new Pane(output);
		return center;
	}
	
	/**
	 * Make Right
	 * @param choice
	 * @return
	 */
	public VBox makeRight(String choice) {
		switch (choice) {
			case "START":
				// maybe remove?
				break;
			case "USER":
				user_heading = new Text("Enter Your Username");
				user_field = new TextField("username");
				user_button = new Button("Select User");
				//user_button.setOnAction(event -> {});
				HBox containerU = new HBox(user_field, user_button);
				right = new VBox(user_heading, containerU);
				break;
			case "RESERVE":
				reserve_heading = new Text("Please select your start date");
				reserve_prompt = new Text("December");
				reserve_startField = new TextField("start");
				reserve_endField = new TextField("end");
				HBox containerR = new HBox(reserve_startField, reserve_endField);
				reserve_submit = new Button("reserve dates");
				right = new VBox(reserve_heading, reserve_prompt, containerR, reserve_submit);
				break;
			case "CANCEL":
				// wait then use makeGUI() again
				break;
			case "AVAIL":
				ok = new Button("back to menu");
				right = new VBox(ok);
				break;
		}
		return right;
	}
	
	/**
	 * Make Top
	 * @return
	 */
	public HBox makeTop() {
		title = new Text("Hotel Client");
		top = new HBox(title);
		return top;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}

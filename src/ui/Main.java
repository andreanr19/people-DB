package ui;

import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.DBDriver;

public class Main extends Application {

	private PeopleController pc;

	private DBDriver db;

	public Main() {

		db = new DBDriver();
		pc = new PeopleController(db);

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmll = new FXMLLoader(getClass().getResource("peopleDBFXML.fxml"));
		fxmll.setController(pc);
		Parent root = fxmll.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("People Database V1.0");
		primaryStage.show();

		
	}

	

}
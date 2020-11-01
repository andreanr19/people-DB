package model.data_structures_trie;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private FXMLDocumentController dc;

	public Main() {

		dc = new FXMLDocumentController();

	}

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader fxmll = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
		fxmll.setController(dc);
		Parent root = fxmll.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pruebas");
		primaryStage.show();

	}

}

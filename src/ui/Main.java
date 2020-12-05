package ui;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
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

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {

				try {

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Confirmar.");
					alert.setHeaderText("Guardar y salir.");
					alert.setContentText("¿Desea guardar el estado actual?");

					ButtonType buttonTypeOne = new ButtonType("Confirmar");
					ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

					alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == buttonTypeOne) {

						db.saveState();

						Alert bye = new Alert(AlertType.INFORMATION);
						bye.setTitle("Adios");
						bye.setHeaderText("Gracias por usar People Database V1.0");
						bye.setContentText("Desarrolladores:\n\n -Dannasofiagarcia\n -andreanr19\n -kamneklogs");

						bye.showAndWait();

					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		primaryStage.show();

	}

}
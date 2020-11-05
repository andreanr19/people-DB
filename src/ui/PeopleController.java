package ui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import model.DBDriver;
import model.Generator;
import model.Person;
import thread.ProgressBarThread;

public class PeopleController {

	@FXML
	private DatePicker birthDateTF;

	@FXML
	private AnchorPane generatePeopleAP;

	@FXML
	private TextField amountGeneratorTF;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private TextField idCreateTF;

	@FXML
	private TextField nameCreateTF;

	@FXML
	private TextField lastNameTF;

	@FXML
	private RadioButton femaleRB;

	@FXML
	private ToggleGroup genderSel;

	@FXML
	private RadioButton manRB;

	@FXML
	private TextField heightCreateTF;

	@FXML
	private TextField idRemoveTF;

	@FXML
	private TextField nameRemoveTF;

	@FXML
	private TextField lastNRemoveTF;

	@FXML
	private TextField heightRemoveTF;

	@FXML
	private TextField genderRemoveTF;

	@FXML
	private TextField birthDateRemoveTF;

	@FXML
	private TextField idSearchTF;

	@FXML
	private TextField nameSearchTF;

	@FXML
	private TextField lastNSearchTF;

	@FXML
	private TextField heightSearchTF;

	@FXML
	private TextField genderSearchTF;

	@FXML
	private RadioButton nameModifyRB;

	@FXML
	private ToggleGroup modifyOptions;

	@FXML
	private RadioButton lastNModifyRB;

	@FXML
	private RadioButton idModifyRB;

	@FXML
	private RadioButton heightModifyRB;

	@FXML
	private RadioButton genderModifyRB;

	@FXML
	private TextField idModifyTF;

	@FXML
	private Button checkModifyBT;

	@FXML
	private Button addPersonBT;

	@FXML
	private Pane modifyPane;

	@FXML
	private Pane modifyNameFXML;

	@FXML
	private TextField newNameTF;

	@FXML
	private Pane modifyLastNameFXML;

	@FXML
	private TextField newLastNameTF;

	@FXML
	private Pane modifyHeightFXML;

	@FXML
	private TextField newHeightTF;

	@FXML
	private Pane modifyGenderFXML;

	@FXML
	private RadioButton femaleNewRB;

	@FXML
	private ToggleGroup newGender;

	@FXML
	private RadioButton maleNewRB;

	private DBDriver db;
	private Generator g;
	private ProgressBarThread pbThread;

	public PeopleController(DBDriver db) {
		this.db = db;
		g = new Generator();
	}

	@FXML
	void changeGenderBT(ActionEvent event) {

	}

	@FXML
	void changeHeightBT(ActionEvent event) {

	}

	@FXML
	void changeLastNameBT(ActionEvent event) {

	}

	@FXML
	void changeNameBT(ActionEvent event) {

	}


	@FXML
	void generateBT(ActionEvent event) {

		try {
			int q = Integer.parseInt(amountGeneratorTF.getText());
			pbThread = new ProgressBarThread(this, g, q);
			pbThread.run();
//			new Thread() {
//				@Override
//				public void run() {
//					try {
//						int count = 0;
//						g.setQ(q);
//						g.loadDataToGenerate(q);
//						PrintWriter pw = new PrintWriter(new FileWriter(g.PATHTOWRITE));
//						pw.write("id,name,lastname,gender,age\n");
//						pbThread.start();
//						for (int i = 0; i < q && count < g.MAX_CAPACITY; i++) {
//
//							pw.write(g.getRandomPerson());
//							count++;
//							g.setCount(count);
//							double x = count / q;
//
//						}
//
//						pw.close();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}.start();


			//g.generateData(Integer.parseInt(amountGeneratorTF.getText()));
			//pbThread = new ProgressBarThread(this);
			//pbThread.start();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Data generada.");
			alert.setHeaderText("Generacion de datos finalizada");
			alert.setContentText(
					"Se generaron aleatoriamente " + g.count + " personas\n¿Desea cargar a la base de datos?");

			ButtonType buttonTypeOne = new ButtonType("Confirmar");
			ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {
				// ... user chose "One"
				db.loadGeneratedData();

				g.cleanTemporalFiles();

				Alert warning = new Alert(AlertType.CONFIRMATION);
				warning.setTitle("Data cargada");
				warning.initStyle(StageStyle.DECORATED);
				warning.setContentText("Los  datos generados fueron cargados exitosamente.");
				warning.show();

				db.getDb().preOrden(db.getDb().root);

			} else {
				g.cleanTemporalFiles();
			}

		} catch (

		NumberFormatException e) {

			Alert warning = new Alert(AlertType.ERROR);
			warning.setTitle("ERROR DE FORMATO");
			warning.initStyle(StageStyle.DECORATED);
			warning.setContentText("Ingresó caracteres no numéricos en el campo de texto");
			warning.show();

		} catch (IOException i) {

			Alert warning = new Alert(AlertType.ERROR);
			warning.setTitle("ARCHIVOS NO ENCONTRADOS");
			warning.initStyle(StageStyle.DECORATED);
			warning.setContentText(i.getMessage());
			i.printStackTrace();
			warning.showAndWait();

		}

	}

	@FXML
	void modifyNamePane(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyNameFXML.fxml"));
		fxmlLoader.setController(this);
		Parent nameFXML = fxmlLoader.load();
		modifyPane.getChildren().clear();
		modifyPane.getChildren().add(nameFXML);
	}

	@FXML
	void generateMaxBT(ActionEvent event) {

		amountGeneratorTF.setText(Generator.MAX_CAPACITY + "");

	}

	@FXML
	void checkAddBT(ActionEvent event) {
		if (db.verifyID(idCreateTF.getText()) == null) {

			nameCreateTF.setDisable(false);
			lastNameTF.setDisable(false);
			birthDateTF.setDisable(false);
			heightCreateTF.setDisable(false);
			femaleRB.setDisable(false);
			manRB.setDisable(false);
			addPersonBT.setDisable(false);

		} else {
			Alert warning = new Alert(AlertType.WARNING);
			warning.setTitle("Persona existente");
			warning.initStyle(StageStyle.DECORATED);
			warning.setContentText("El ID ingresado ya se encuentra registrado en la base de datos, intente con ID.");
			warning.show();
		}

	}

	@FXML
	void addPersonBT(ActionEvent event) {

		try {
			db.addPerson(new Person(idCreateTF.getText(), nameCreateTF.getText(), lastNameTF.getText(),
					(LocalDate.now().getYear() - birthDateTF.getValue().getYear()) + "",
					femaleRB.isSelected() ? 'm' : 'f', Double.parseDouble(heightCreateTF.getText())));

			Alert warning = new Alert(AlertType.CONFIRMATION);
			warning.setTitle("Persona registrada");
			warning.initStyle(StageStyle.DECORATED);
			warning.setContentText("La persona con ID " + idCreateTF.getText() + " fue registrado exitosamente.");
			warning.show();

		} catch (Exception e) {
		}

	}

	@FXML
	void modifyLastNamePane(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyLastNameFXML.fxml"));
		fxmlLoader.setController(this);
		Parent lastNameFXML = fxmlLoader.load();
		modifyPane.getChildren().clear();
		modifyPane.getChildren().add(lastNameFXML);
	}

	@FXML
	void modifyIdPane(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyIdFXML.fxml"));
		fxmlLoader.setController(this);
		Parent idFXML = fxmlLoader.load();
		modifyPane.getChildren().clear();
		modifyPane.getChildren().add(idFXML);
	}

	@FXML
	void modifyHeightPane(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyHeightFXML.fxml"));
		fxmlLoader.setController(this);
		Parent heightFXML = fxmlLoader.load();
		modifyPane.getChildren().clear();
		modifyPane.getChildren().add(heightFXML);
	}

	@FXML
	void modifyGenderPane(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyGenderFXML.fxml"));
		fxmlLoader.setController(this);
		Parent genderFXML = fxmlLoader.load();
		modifyPane.getChildren().clear();
		modifyPane.getChildren().add(genderFXML);
	}

	public void progressBar(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setProgress(((double)g.count/g.q));
			}
		});
		//q is the amount user ask

	}

}

package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import model.DBDriver;
import model.Generator;

public class PeopleController {

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
	private TextField birthDateTF;

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

	@FXML
	void changeGenderBT(ActionEvent event) {

	}

	@FXML
	void changeHeightBT(ActionEvent event) {

	}

	private DBDriver db;

	public PeopleController(DBDriver db) {
		this.db = db;
	}

	@FXML
	void changeLastNameBT(ActionEvent event) {

	}

	@FXML
	void changeNameBT(ActionEvent event) {

	}

	@FXML
	void generateBT(ActionEvent event) {

		Generator g = new Generator();
		try {
			g.generateData(Integer.parseInt(amountGeneratorTF.getText()));
			Alert warning = new Alert(AlertType.CONFIRMATION);
			warning.setTitle("CARGA EXITOSA DE DATOS");
			warning.setContentText("La cantidad "+ amountGeneratorTF.getText() + " de datos aleatorios se generaron exitosamente.");
			warning.initStyle(StageStyle.DECORATED);
			warning.showAndWait();
		}catch(NumberFormatException e) {
			Alert warning = new Alert(AlertType.ERROR);
			warning.setTitle("ERROR DE FORMATO");
			warning.initStyle(StageStyle.DECORATED);
			warning.setContentText("Ingresó caracteres no numéricos en el campo de texto");
			warning.show();
		}catch(IOException i) {
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

}

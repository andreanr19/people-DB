package ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import org.controlsfx.control.textfield.TextFields;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DBDriver;
import model.Generator;
import model.Person;
import thread.ProgressBarThread;

public class PeopleController implements Initializable {

	@FXML
	private Pane paneResults;
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
	private TextField idSearchTF;

	@FXML
	private TextField found;

	@FXML
	private TextField idModifyTF;

	@FXML
	private Button checkModifyBT;

	@FXML
	private Button addPersonBT;

	@FXML
	private Button editButton;

	@FXML
	private Button confirmButton;

	@FXML
	private Button removeButton;

	@FXML
	private Pane modifyPane;

	@FXML
	private TextField newNameTF;

	@FXML
	private TextField newLastNameTF;

	@FXML
	private TextField newHeightTF;
	@FXML
	private TextField nationalityCreateTF;

	@FXML
	private RadioButton femaleNewRB;

	@FXML
	private ToggleGroup newGender;

	@FXML
	private RadioButton maleNewRB;

	@FXML
	private TableView<Person> foundPeople;
	@FXML
	private TableColumn<Person, String> idCL;
	@FXML
	private TableColumn<Person, String> nameCL;
	@FXML
	private TableColumn<Person, String> lastNameCL;
	@FXML
	private TableColumn<Person, String> ageCL;
	@FXML
	private TableColumn<Person, String> genderCL;
	@FXML
	private TableColumn<Person, String> heightCL;
	@FXML
	private TableColumn<Person, String> nationalityCL;

	private ObservableList<Person> people;

	@FXML
	private TextField idTF;
	@FXML
	private TextField nombreTF;

	@FXML
	private TextField apellidoTF;

	@FXML
	private TextField edadTF;

	@FXML
	private TextField generoTF;

	@FXML
	private TextField nacionalidadTF;

	@FXML
	private TextField alturaTF;

	@FXML
	private Label timeLabel;

	private DBDriver db;
	private Generator g;
	private ProgressBarThread pbThread;

	public PeopleController(DBDriver db) {
		this.db = db;
		g = new Generator();
		foundList = new ArrayList<Person>();
	}

	@FXML
	private RadioButton autocompleteByName;
	@FXML
	private RadioButton autocompleteByLastName;
	@FXML
	private RadioButton autocompleteByNameAndLN;
	@FXML
	private RadioButton autocompleteByID;

	@FXML
	void txtKeyReleased(KeyEvent event) {
		String search = idSearchTF.getText().trim();
		if (!search.equals("")) {
			if (autocompleteByName.isSelected()) {
				System.out.println(search);
				TextFields.bindAutoCompletion(idSearchTF, db.getByName().predictCompletions(search, 100));
			} else if (autocompleteByNameAndLN.isSelected()) {
				TextFields.bindAutoCompletion(idSearchTF, db.getByNameAndLastName().predictCompletions(search, 100));
			} else if (autocompleteByID.isSelected()) {
				TextFields.bindAutoCompletion(idSearchTF, db.getByID().predictCompletions(search, 100));

			} else if (autocompleteByLastName.isSelected()) {
				TextFields.bindAutoCompletion(idSearchTF, db.getByLastName().predictCompletions(search, 100));

			}
		}
	}

	@FXML
	void saveCurrentState(ActionEvent event) {
		try {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Confirmar.");
			alert.setHeaderText("Guardar y salir.");
			alert.setContentText("¿Desea guardar el estado actual y cerrar\nla aplicacion?");

			ButtonType buttonTypeOne = new ButtonType("Confirmar");
			ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {

				db.saveState();

				Node source = (Node) event.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.close();

				Alert bye = new Alert(AlertType.INFORMATION);
				bye.setTitle("Adios");
				bye.setHeaderText("Gracias por usar People Database V1.0");
				bye.setContentText("Desarrolladores:\n\n -Dannasofiagarcia\n -andreanr19\n -kamneklogs");

				bye.showAndWait();

				// db.getDb().preOrden(db.getDb().root);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void loadPreviousState(ActionEvent event) {
		try {
			db.loadState();

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Confirmar.");
			alert.setHeaderText("Cargar estado anterior.");
			alert.setContentText("Se sobreescribirá la informacion no guardada.\n ¿Desea confirmar el proceso?");

			ButtonType buttonTypeOne = new ButtonType("Confirmar");
			ButtonType buttonTypeCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {

				db.saveState();

				Alert bye = new Alert(AlertType.INFORMATION);
				bye.setTitle("Realizado");
				bye.setHeaderText("Carga completada");
				bye.setContentText("La carga de los datos ha sido completada\nen la base de datos");

				bye.showAndWait();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error.");
			alert.setHeaderText("Error en la carga.");
			alert.setContentText("Ha ocurrido un problema con los archivos de carga, por favor verifique.");
			e.printStackTrace();
		}
	}

	@FXML
	void searchBtn(ActionEvent event) {
		String search = idSearchTF.getText();
		if (autocompleteByName.isSelected()) {
			found.setText(db.searchByName(search).size() + "");
			foundList.clear();
			foundList = db.searchByName(search);
		} else if (autocompleteByNameAndLN.isSelected()) {
			found.setText(db.searchByNameAndLastName(search).size() + "");
			foundList.clear();
			foundList = db.searchByNameAndLastName(search);
		} else if (autocompleteByID.isSelected()) {
			if (db.searchByID(search) != null) {
				found.setText("1");
				foundList.clear();
				foundList.add(db.searchByID(search));
			} else {
				found.setText("0");
			}
		} else if (autocompleteByLastName.isSelected()) {

			found.setText(db.searchByLastName(search).size() + "");
			foundList.clear();
			foundList = db.searchByLastName(search);
		}
		initialize(null, null);
	}

	List<Person> foundList;

	@FXML
	void generateBT(ActionEvent event) {

		long actualT = System.currentTimeMillis();
		progressBar.setProgress(0);

		try {
			int q = Integer.parseInt(amountGeneratorTF.getText());
			pbThread = new ProgressBarThread(this, g, q);
			pbThread.run();
			long terminado = System.currentTimeMillis();
			long total = terminado - actualT;

			timeLabel.setText(total + "");
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

				Alert warning = new Alert(AlertType.INFORMATION);
				warning.setTitle("Data cargada");
				warning.initStyle(StageStyle.DECORATED);
				warning.setContentText("Los  datos generados fueron cargados\n exitosamente.");
				warning.show();

				// db.getDb().preOrden(db.getDb().root);

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
	void removePersonBtn(ActionEvent event) {
		try {
			db.getDb().delete(idTF.getText());
			for (int i = 0; i < foundList.size(); i++) {
				if (foundList.get(i).getId().equalsIgnoreCase(idTF.getText())) {
					foundList.remove(i);
					i = foundList.size();
					found.setText((Integer.parseInt(found.getText()) - 1) + "");
				}

			}
			Alert warning = new Alert(AlertType.CONFIRMATION);
			warning.setTitle("Persona registrada");
			warning.initStyle(StageStyle.DECORATED);
			warning.setContentText("La persona con ID " + idTF.getText() + " fue eliminado exitosamente.");
			warning.show();
			initialize(null, null);

			idTF.setEditable(false);
			nombreTF.setEditable(false);
			generoTF.setEditable(false);
			apellidoTF.setEditable(false);
			edadTF.setEditable(false);
			alturaTF.setEditable(false);
			nacionalidadTF.setEditable(false);

			idTF.clear();
			nombreTF.clear();
			generoTF.clear();
			apellidoTF.clear();
			edadTF.clear();
			alturaTF.clear();
			nacionalidadTF.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void editBtn(ActionEvent event) {
		idTF.setEditable(false);
		nombreTF.setEditable(true);
		generoTF.setEditable(true);
		apellidoTF.setEditable(true);
		edadTF.setEditable(true);
		alturaTF.setEditable(true);
		nacionalidadTF.setEditable(true);
		editButton.setDisable(true);

	}

	@FXML
	void confirmChangesBtn(ActionEvent event) {
		db.getDb().delete(idTF.getText());
		try {
			db.addPerson(new Person(idTF.getText(), nombreTF.getText(), apellidoTF.getText(), edadTF.getText(),
					generoTF.getText().charAt(0), Double.parseDouble(alturaTF.getText()), nacionalidadTF.getText()));

			Alert warning = new Alert(AlertType.CONFIRMATION);
			warning.setTitle("Persona registrada");
			warning.initStyle(StageStyle.DECORATED);
			warning.setContentText("La persona con ID " + idTF.getText() + " fue modificado exitosamente.");
			warning.show();
			initialize(null, null);
			editButton.setDisable(false);

			idTF.setEditable(false);
			nombreTF.setEditable(false);
			generoTF.setEditable(false);
			apellidoTF.setEditable(false);
			edadTF.setEditable(false);
			alturaTF.setEditable(false);
			nacionalidadTF.setEditable(false);

			searchBtn(event);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void addPersonBT(ActionEvent event) {

		try {
			db.addPerson(new Person(idCreateTF.getText(), nameCreateTF.getText(), lastNameTF.getText(),
					(LocalDate.now().getYear() - birthDateTF.getValue().getYear()) + "",
					femaleRB.isSelected() ? 'm' : 'f', Double.parseDouble(heightCreateTF.getText()),
					nationalityCreateTF.getText()));

			Alert warning = new Alert(AlertType.CONFIRMATION);
			warning.setTitle("Persona registrada");
			warning.initStyle(StageStyle.DECORATED);
			warning.setContentText("La persona con ID " + idCreateTF.getText() + " fue registrado exitosamente.");
			warning.show();

		} catch (Exception e) {
		}

	}

	public void progressBar() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setProgress(((double) g.count / g.q));
			}
		});
		// q is the amount user ask

	}

	private void inicializePeopleTable() {
		idCL.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
		nameCL.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		lastNameCL.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		ageCL.setCellValueFactory(new PropertyValueFactory<Person, String>("birthdate"));
		genderCL.setCellValueFactory(new PropertyValueFactory<Person, String>("gender"));
		heightCL.setCellValueFactory(new PropertyValueFactory<Person, String>("height"));
		nationalityCL.setCellValueFactory(new PropertyValueFactory<Person, String>("nationality"));

		people = FXCollections.observableArrayList();
		foundPeople.setItems(people);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// Inicializamos la tabla
		this.inicializePeopleTable();

		// Ponemos estos dos botones para que no se puedan seleccionar
		editButton.setDisable(true);
		removeButton.setDisable(true);

		// Seleccionar las tuplas de la tabla de las personas
		final ObservableList<Person> selectedPeopleTable = foundPeople.getSelectionModel().getSelectedItems();
		selectedPeopleTable.addListener(selectorPeopleTable);

		// Inicializamos la tabla con algunos datos aleatorios

		for (int i = 0; i < foundList.size(); i++) {

			people.add(foundList.get(i));
		}

	}

	private final ListChangeListener<Person> selectorPeopleTable = new ListChangeListener<Person>() {
		@Override
		public void onChanged(ListChangeListener.Change<? extends Person> c) {
			putSelectedPerson();
		}
	};

	/**
	 * Método para poner en los textFields la tupla que selccionemos
	 */

	int posicionPersonaEnTabla;

	private void putSelectedPerson() {
		final Person persona = getTablaPersonasSeleccionada();
		posicionPersonaEnTabla = people.indexOf(persona);

		if (persona != null) {

			// Pongo los textFields con los datos correspondientes
			idTF.setText(persona.getId());
			nombreTF.setText(persona.getName());
			apellidoTF.setText(persona.getLastName());
			edadTF.setText(persona.getBirthdate());
			generoTF.setText(persona.getGender() + "");
			alturaTF.setText(persona.getHeight() + "");
			nacionalidadTF.setText(persona.getNationality());

			// Pongo los botones en su estado correspondiente
			editButton.setDisable(false);
			removeButton.setDisable(false);

		}
	}

	public Person getTablaPersonasSeleccionada() {
		if (foundPeople != null) {
			List<Person> table = foundPeople.getSelectionModel().getSelectedItems();
			if (table.size() == 1) {
				final Person competicionSeleccionada = table.get(0);
				return competicionSeleccionada;
			}
		}
		return null;
	}

}

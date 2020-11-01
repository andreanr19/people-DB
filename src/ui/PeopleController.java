package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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
		void modifyNamePane(ActionEvent event) throws IOException {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deleteClientAccount.fxml"));
			fxmlLoader.setController(this);
			Parent clientAccount = fxmlLoader.load();
			modifyPane.getChildren().clear();
			modifyPane.getChildren().add(clientAccount);
		}
	    
	    @FXML
		void modifyLastNamePane(ActionEvent event) throws IOException {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deleteClientAccount.fxml"));
			fxmlLoader.setController(this);
			Parent clientAccount = fxmlLoader.load();
			modifyPane.getChildren().clear();
			modifyPane.getChildren().add(clientAccount);
		}


}

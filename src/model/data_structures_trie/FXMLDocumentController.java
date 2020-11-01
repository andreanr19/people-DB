package model.data_structures_trie;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable{

	@FXML
	private AnchorPane root;
	
	@FXML
	private TextField input;
	
	public AutocompleteTrie at;
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		at=new AutocompleteTrie();
		at.addWord("Andrea");
		at.addWord("And");
		at.addWord("Andromeda");
		at.addWord("Animal");
		
		TextFields.bindAutoCompletion(input, at.predictCompletions(input.getText(), 3));
	}
}

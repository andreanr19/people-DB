package model.data_structures_trie;

import java.net.URL;
import java.util.ResourceBundle;


import org.controlsfx.control.textfield.TextFields;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class FXMLDocumentController implements Initializable{

	@FXML
	private AnchorPane root;
	
	@FXML
	private TextField input;
	
	public AutocompleteTrie at;
	

    @FXML
    private TextField txt;
	

	
    @FXML
    void txtKeyReleased(KeyEvent event) {
    	String search= txt.getText().trim();
    	if(!search.equals("")) {
    		System.out.println(search);
    		TextFields.bindAutoCompletion(txt, at.predictCompletions(search, 10));
    	}
    }
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		at=new AutocompleteTrie();
		at.addWord("Andrea");
		at.addWord("And");
		at.addWord("Andromeda");
		at.addWord("Animal");
		at.addWord("Hola");
		at.addWord("Banano");
//		final ContextMenu contextMenu = new ContextMenu();
//		MenuItem cut = new MenuItem("Cut");
//		MenuItem copy = new MenuItem("Copy");
//		MenuItem paste = new MenuItem("Paste");
//		contextMenu.getItems().addAll(cut, copy, paste);
//		txt.setContextMenu(contextMenu);
//		
//		
//		
//		cut.setOnAction(new EventHandler<ActionEvent>() {
//		       @Override
//		       public void handle(ActionEvent event) {
//		           System.out.println("Cut...");
//		       }
//		});
//		txt.setContextMenu(contextMenu);
//		
//		for(String item: at.predictCompletions(txt.getText(), 10)) {
//			
//		}
		TextFields.bindAutoCompletion(input, at.predictCompletions(input.getText(), 3));
	}
}

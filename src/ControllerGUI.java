
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerGUI {

    @FXML
    private TextField intText;

    @FXML
    private Label arreglodislay;
    @FXML
    private Label resultado;
    @FXML
    private Label tiempo;

    private ArrayList<Integer> elArrayList;

    @FXML
    void agregarnumero(ActionEvent event) {
        elArrayList.add(Integer.parseInt(intText.getText()));
        if (arreglodislay.getText().equals("Arreglo")) {
            arreglodislay.setText("");
        }

        arreglodislay.setText(arreglodislay.getText() + " | " + elArrayList.get(elArrayList.size() - 1) + " | ");

        intText.clear();
        intText.setPromptText("Agregue numero entero");
    }

    @FXML
    void ordenar(ActionEvent event) {

        int[] arreglo = new int[elArrayList.size()];

        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = elArrayList.get(i);
        }

        long start = System.currentTimeMillis();
        Arrays.sort(arreglo);

        tiempo.setText(Long.toString((long) ((System.currentTimeMillis() - start) / 1000)) + " segundos");

        for (int i : arreglo) {
            resultado.setText(resultado.getText() + " | " + i + " | ");
        }

    }

    public ControllerGUI() {
        elArrayList = new ArrayList<Integer>();
    }

}

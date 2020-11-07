package ui;

import java.io.IOException;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import model.DBDriver;
import model.Generator;
import model.Person;
import model.data_structures.abb.BinarySearchTree;
import model.data_structures.abb.BinarySearchTreeInterface;
import model.data_structures.avl.AVLTree;

public class Main2 {

	public static void main(String[] args) throws IOException {

		Generator g = new Generator();

		g.generateData(100);

	}

}

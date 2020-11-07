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

//		/*
//		 * Generator g = new Generator();
//		 * 
//		 * g.generateData(10);
//		 * 
//		 * DBDriver db = new DBDriver();
//		 * 
//		 * db.loadGeneratedData();
//		 * 
//		 * db.addWordtoTheTrieByName();
//		 * 
//		 * db.addPerson(new Person("1010", "Andrea", "nnn", "nnn", 'f', 9));
//		 */
		

		AVLTree<String, Person> t = new AVLTree<>();
/* 
		t.put("1", new Person("1", "Camilo", "a", "birthdate", 'm', 12));

		t.put("2", new Person("2", "Camilo", "b", "birthdate", 'm', 12));

		t.put("3", new Person("3", "Camilo", "c", "birthdate", 'm', 12));

		t.put("4", new Person("4", "Camilo", "d", "birthdate", 'm', 12));

		t.put("5", new Person("5", "Camilo", "e", "birthdate", 'm', 12)); */

		List<Person> theList = t.searchByName("Camilo");

		for (Person person : theList) {
			System.out.println(person.getName() + "    " + person.getLastName());
		}

		Generator g = new Generator();
		DBDriver db = new DBDriver();
		g.generateData(1000);
		db.loadGeneratedData();
		System.out.println(db.getByName().predictCompletions("al", 1000).toString());
		
//		TextFields.bindAutoCompletion(, db.getByName().predictCompletions(prefix, numCompletions))
		
	}

}

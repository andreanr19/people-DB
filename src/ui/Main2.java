package ui;

import java.io.IOException;

import model.DBDriver;
import model.Generator;
import model.Person;

public class Main2 {

	public static void main(String[] args) throws IOException {

		Generator g = new Generator();

		g.generateData(10);

		DBDriver db = new DBDriver();

		db.loadGeneratedData();
		
		db.addWordtoTheTrieByName();
		
		db.addPerson(new Person("1010", "Andrea", "nnn", "nnn", 'f', 9));
	

	}

}

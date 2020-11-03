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
		System.out.println(db.getDb().searchNode2("10109"));
		System.out.println(db.getAt().predictCompletions("w", 3).toString());

		db.addWordtoTheTrieByLastName();
		System.out.println(db.getAt().predictCompletions("m", 4).toString());
		
		db.addWordtoTheTrieByNameAndLastName();
		System.out.println(db.getAt().predictCompletions("w", 4).toString());

	}

}

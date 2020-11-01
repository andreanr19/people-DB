package ui;

import java.io.IOException;

import model.DBDriver;
import model.Generator;

public class Main2 {

	public static void main(String[] args) throws IOException {

		Generator g = new Generator();

		g.generateData(10);

		DBDriver db = new DBDriver();

		db.loadGeneratedData();
		
		db.addWordtoTheTrieByName();
		System.out.println(db.getAt().predictCompletions("w", 3).toString());

		db.addWordtoTheTrieByLastName();
		System.out.println(db.getAt().predictCompletions("m", 4).toString());
		
		db.addWordtoTheTrieByNameAndLastName();
		System.out.println(db.getAt().predictCompletions("w", 4).toString());

	}

}

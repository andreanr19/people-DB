package ui;

import java.io.IOException;

import model.DBDriver;
import model.Generator;

public class Main {

	public static void main(String[] args) throws IOException {

		Generator g = new Generator();

		g.generateData(10);

		DBDriver db = new DBDriver();

		db.loadGeneratedData();

	}

}

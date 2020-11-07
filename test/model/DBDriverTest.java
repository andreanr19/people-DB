package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class DBDriverTest {

	private DBDriver db;
	private Generator g;

	void setup2() {
		db = new DBDriver();
		g = new Generator();

		try {
			g.generateData(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void setup3() {
		db = new DBDriver();
		g = new Generator();
	}

	@Test
	void loadGenerateDataTest() {
		setup2();
		try {
			db.loadGeneratedData();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(1000, db.getDb().size());
	}

	@Test
	void verifyIdTest() {
		setup2();

		assertEquals(null, db.verifyID("111111111"));
	}

	@Test
	void searchByNameTest() {
		setup3();

		assertEquals(null, db.searchByName("Hola"));
	}

	@Test
	void searchByIdTest() {
		setup3();

		assertEquals(null, db.searchByName("129217132"));
		assertNotNull(db.searchByID("0000000001"));
	}

//	@Test
//	void searchByIdTest() {
//		setup3();
//
//		assertEquals(null, db.searchByName("129217132"));
//	}

}

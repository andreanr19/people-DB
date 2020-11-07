package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class GeneratorTest {

	private Generator g;

	void setup1() {
		g = new Generator();

	}

	@Test
	void generateDataTest() throws FileNotFoundException {
		setup1();

		try {
			g.loadDataToGenerate(1000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		FileReader file = new FileReader(g.PATHTOWRITE);

		assertNotNull(file);
	}

	@Test
	void getRandomPerson() throws IOException {
		setup1();
		assertNotEquals("", g.getRandomPerson());
	}

	@Test
	void loadDataToGenerate() throws IOException {
		setup1();
		g.loadDataToGenerate(6000);
		assertNotEquals(0, g.getCountries().size());
		assertNotEquals(0, g.getNames().size());
		assertNotEquals(0, g.getLastNames().size());
	}

	@Test
	void loadDataToGenerate2() throws IOException {
		setup1();
		g.cleanTemporalFiles();
		assertEquals(0, g.getCountries().size());
		assertEquals(0, g.getNames().size());
		assertEquals(0, g.getLastNames().size());
	}

}

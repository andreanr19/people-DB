package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import model.data_structures.avl.AVLTree;
import model.data_structures_trie.AutocompleteTrie;

public class DBDriver {

	private AVLTree<String, Person> db;

	private AutocompleteTrie byID, byName, byLastName, byNameAndLastName;

	public void loadGeneratedData() throws IOException {

		BufferedReader brPeople = new BufferedReader(new FileReader(new File(Generator.PATHTOWRITE)));

		brPeople.readLine();
		String temporal = brPeople.readLine();
		while (!(temporal == null)) {

			String[] t = temporal.split(",");

			Person p = new Person(t[0], t[1], t[2], t[4], (t[3].equals("m")) ? 'm' : 'f', Double.parseDouble(t[5]),
					t[6]);

			db.put(p.getId(), p);
			temporal = brPeople.readLine();

		}
		brPeople.close();

		addWordtoTheTrieByID();
		addWordtoTheTrieByName();
		addWordtoTheTrieByLastName();
		addWordtoTheTrieByNameAndLastName();

	}

	public void addWordtoTheTrieByID() throws IOException {
		if (byID == null)
			byID = new AutocompleteTrie();

		BufferedReader brName = new BufferedReader(new FileReader(new File(Generator.PATHTOWRITE)));

		brName.readLine();
		String temporal = brName.readLine();
		while (!(temporal == null)) {

			String[] t = temporal.split(",");

			byID.addWord(t[1]);
			temporal = brName.readLine();
		}
		brName.close();
	}

	public void addWordtoTheTrieByName() throws IOException {
		if (byName == null)
			byName = new AutocompleteTrie();

		BufferedReader brName = new BufferedReader(new FileReader(new File(Generator.PATHTOWRITE)));

		brName.readLine();
		String temporal = brName.readLine();
		while (!(temporal == null)) {

			String[] t = temporal.split(",");

			byName.addWord(t[1]);
			temporal = brName.readLine();
		}
		brName.close();
	}

	public void addWordtoTheTrieByLastName() throws IOException {
		if (byLastName == null)
			byLastName = new AutocompleteTrie();

		BufferedReader brLastName = new BufferedReader(new FileReader(new File(Generator.PATHTOWRITE)));

		brLastName.readLine();
		String temporal = brLastName.readLine();
		while (!(temporal == null)) {

			String[] t = temporal.split(",");

			byLastName.addWord(t[2]);
			temporal = brLastName.readLine();
		}
		brLastName.close();
	}

	public void addWordtoTheTrieByNameAndLastName() throws IOException {
		if (byNameAndLastName == null)
			byNameAndLastName = new AutocompleteTrie();

		BufferedReader brNameAndLastName = new BufferedReader(new FileReader(new File(Generator.PATHTOWRITE)));

		brNameAndLastName.readLine();
		String temporal = brNameAndLastName.readLine();
		while (!(temporal == null)) {

			String[] t = temporal.split(",");

			byNameAndLastName.addWord(t[1] + " " + t[2]);
			temporal = brNameAndLastName.readLine();
		}
		brNameAndLastName.close();
	}

	public void addPerson(Person p) {

		db.put(p.getId(), p);

		byID.addWord(p.getId());
		byName.addWord(p.getName());

		byLastName.addWord(p.getLastName());
		byNameAndLastName.addWord(p.getName() + " " + p.getLastName());
	}

	public Person verifyID(String id) {
		if (db.searchNode2(id) == null) {
			return null;
		}

		return db.searchNode2(id);
	}

	public void loadDataSearchingByName(String name) {
		List<Person> p = db.searchByName(name);

	}

	public DBDriver() {

		db = new AVLTree<String, Person>();
		// at = new AutocompleteTrie();
	}

	public AVLTree<String, Person> getDb() {
		return db;
	}

	public void setDb(AVLTree<String, Person> db) {
		this.db = db;
	}

	public AutocompleteTrie getByID() {
		return byID;
	}

	public void setByID(AutocompleteTrie byID) {
		this.byID = byID;
	}

	public AutocompleteTrie getByName() {
		return byName;
	}

	public void setByName(AutocompleteTrie byName) {
		this.byName = byName;
	}

	public AutocompleteTrie getByLastName() {
		return byLastName;
	}

	public void setByLastName(AutocompleteTrie byLastName) {
		this.byLastName = byLastName;
	}

	public AutocompleteTrie getByNameAndLastName() {
		return byNameAndLastName;
	}

	public void setByNameAndLastName(AutocompleteTrie byNameAndLastName) {
		this.byNameAndLastName = byNameAndLastName;
	}

	public List<Person> searchByName(String name) {
		return db.searchByName(name);

	}

	public List<Person> searchByLastName(String lastname) {
		return db.searchByNameAndLastName(lastname);

	}

	public Person searchByID(String id) {
		return db.searchNode2(id);

	}

	public List<Person> searchByNameAndLastName(String nameandlastname) {
		return db.searchByNameAndLastName(nameandlastname);
	}
}
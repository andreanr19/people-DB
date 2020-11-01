package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.data_structures.avl.AVLTree;
import model.data_structures_trie.AutocompleteTrie;

public class DBDriver {

    private AVLTree<String, Person> db;
    
    private AutocompleteTrie at;

    public void loadGeneratedData() throws IOException {

        BufferedReader brPeople = new BufferedReader(new FileReader(new File(Generator.PATHTOWRITE)));

        brPeople.readLine();
        String temporal = brPeople.readLine();
        while (!(temporal == null)) {

            String[] t = temporal.split(",");

            Person p = new Person(t[0], t[1], t[2], t[3], (t[4].equals("m")) ? 'm' : 'f', Double.parseDouble(t[5]));

            
            db.put(p.getId(), p);
            temporal = brPeople.readLine();
        }
        brPeople.close();

    }

    public void addWordtoTheTrieByName() throws IOException {
        at = new AutocompleteTrie();

    	BufferedReader brName = new BufferedReader(new FileReader(new File(Generator.PATHTOWRITE)));
    	
    	brName.readLine();
    	String temporal = brName.readLine();
    	while(!(temporal==null)) {
    		
    		String[] t= temporal.split(",");
    		
    		at.addWord(t[1]);
    		temporal= brName.readLine();
    	}
    	brName.close();
    }
    
    public void addWordtoTheTrieByLastName() throws IOException {
        at = new AutocompleteTrie();

    	BufferedReader brLastName = new BufferedReader(new FileReader(new File(Generator.PATHTOWRITE)));
    	
    	brLastName.readLine();
    	String temporal = brLastName.readLine();
    	while(!(temporal==null)) {
    		
    		String[] t= temporal.split(",");
    		
    		at.addWord(t[2]);
    		temporal= brLastName.readLine();
    	}
    	brLastName.close();
    }
    
    public void addWordtoTheTrieByNameAndLastName() throws IOException {
        at = new AutocompleteTrie();

    	BufferedReader brNameAndLastName = new BufferedReader(new FileReader(new File(Generator.PATHTOWRITE)));
    	
    	brNameAndLastName.readLine();
    	String temporal = brNameAndLastName.readLine();
    	while(!(temporal==null)) {
    		
    		String[] t= temporal.split(",");
    		
    		at.addWord(t[1] + t[2]);
    		temporal= brNameAndLastName.readLine();
    	}
    	brNameAndLastName.close();
    }
    
    
    public DBDriver() {

        db = new AVLTree<String, Person>();
//        at = new AutocompleteTrie();
    }

    public AVLTree<String, Person> getDb() {
        return db;
    }

    public void setDb(AVLTree<String, Person> db) {
        this.db = db;
    }
    
    public AutocompleteTrie getAt() {
    	return at;
    }
    public void setAt(AutocompleteTrie at) {
    	this.at=at;
    }
    

}
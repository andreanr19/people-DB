package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.data_structures.avl.AVLTree;

public class DBDriver {

    private AVLTree<String, Person> db;

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

    }

    public DBDriver() {

        db = new AVLTree<String, Person>();
    }

    public AVLTree<String, Person> getDb() {
        return db;
    }

    public void setDb(AVLTree<String, Person> db) {
        this.db = db;
    }

}
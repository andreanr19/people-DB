package model.data_structures.avl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Person;

class AVLTreeTest {

	AVLTree myTree;

	public void setUpEmptyTree() {
		myTree = new AVLTree<String, Person>();

	}

	public void setUpFullTree() {
		myTree = new AVLTree<String, Person>();

		Person p1 = new Person("1", "Andrea", "Nunez", "19/12/2000", 'f', 1.64, "Colombia");
		Person p2 = new Person("2", "Danna", "Garcia", "19/12/2000", 'f', 1.64, "Colombia");
		Person p3 = new Person("3", "Camilo", "Cordoba", "19/12/2000", 'f', 1.64, "Colombia");

		myTree.put(p1.getId(), p1);
		myTree.put(p2.getId(), p2);
		myTree.put(p3.getId(), p3);
	}

	@Test
	public void putTest() {
		setUpEmptyTree();
		Person p = new Person("1010", "Isabel", "Nunez", "19/12/2000", 'f', 1.64, "Colombia");
		myTree.put(p.getId(), p);
		assertEquals(myTree.root.getValue(), p);

		setUpFullTree();
		myTree.put(p.getId(), p);
		assertEquals(p, myTree.find(p.getId(), myTree.root).getValue());
	}

	@Test
	public void findTest() {
		setUpFullTree();
		Person p = new Person("1010", "Cristian", "Garcia", "19/12/2000", 'f', 1.64, "Colombia");

		myTree.put(p.getId(), p);
		assertEquals(p, myTree.find(p.getId(), myTree.root).getValue());
	}

	@Test
	public void deleteTest() {
		setUpFullTree();

		Person p = new Person("1010", "Cristian", "Garcia", "19/12/2000", 'f', 1.64, "Colombia");

		myTree.put(p.getId(), p);

		myTree.delete(p.getId());
		assertEquals(null, myTree.find(p.getId(), myTree.root));
	}
}

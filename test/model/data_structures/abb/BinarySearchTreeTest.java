package model.data_structures.abb;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Person;
import model.data_structures.avl.AVLTree;

class BinarySearchTreeTest {

	private BinarySearchTree<String, Person> myTree;

	private void setUp1() {
		myTree = new BinarySearchTree<String, Person>();

	}

	public void setUp2() {
		myTree = new BinarySearchTree<String, Person>();

		Person p1 = new Person("2", "Andrea", "Nunez", "19/12/2000", 'f', 1.64, "Colombia");
 

		myTree.add(p1.getId(), p1, 0, 0);
		
	}

	@Test
	public void addTest1() {
		setUp1();
		Person p1 = new Person("2", "Andrea", "Nunez", "19/12/2000", 'f', 1.64, "Colombia");
		myTree.add(p1.getId(), p1, 0, 0);

		assertEquals(p1, myTree.root.value);

	}

	@Test

	public void addTest2() {
		setUp2();
		Person p2 = new Person("1", "Danna", "Garcia", "19/12/2000", 'f', 1.64, "Colombia");
		Person p3 = new Person("3", "Camilo", "Cordoba", "19/12/2000", 'f', 1.64, "Colombia");
		myTree.add(p2.getId(), p2, 0, 0);
		myTree.add(p3.getId(), p3, 0, 0);
		
		assertEquals(p2, myTree.root.left.value);
		assertEquals(p3, myTree.root.right.value);
		
	}
	
	@Test
	public void removeTest1() {
		setUp1();
		assertFalse(myTree.remove("1"));
	}
	
	@Test
	public void removeTest2() {
		setUp2();
		assertTrue(myTree.remove("2"));
	}
	
	@Test
	public void searchTest1() {
		setUp1();
		assertNull(myTree.search("1"));
	}
	
	@Test
	public void searchTest2() {
		setUp2();
		Person p3 = new Person("3", "Camilo", "Cordoba", "19/12/2000", 'f', 1.64, "Colombia");
		myTree.add(p3.getId(), p3, 0, 0);
		
		assertEquals(p3, myTree.search("3"));
	}
}

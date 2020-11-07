package model.data_structures.abb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Person;

public class BinarySearchTree<K extends Comparable<K>, T extends Person> implements BinarySearchTreeInterface<K, T>, Serializable {

	// --------------------------------------------------------------------------------

	public Node<K, T> root;

	protected int weight;

	// --------------------------------------------------------------------------------

	public BinarySearchTree() {

		weight = 0;

	}

	// --------------------------------------------------------------------------------

	// Adds equal elements to the right, always adds the element
	public boolean add(K key, T value, int height, int size) {
		return addBase(key, value, height, size) != null;
	}

	protected Node<K, T> addBase(K key, T value, int height, int size) {

		Node<K, T> newNode = new Node<K, T>(key, value, height, size);

		if (root != null) {
			return addRecursive(key, value, root, newNode);
		} else {
			root = newNode;
			weight++;
			return root;
		}
	}

	// --------------------------------------------------------------------------------

	private Node<K, T> addRecursive(K key, T value, Node<K, T> currentNode, Node<K, T> newNode) {

		if (key.compareTo(currentNode.getKey()) > 0) {

			Node<K, T> right = currentNode.getRight();

			if (right != null) {
				Node<K, T> addedNode = addRecursive(key, value, right, newNode);

				if (addedNode != null) {
					right.update();
				}

				return addedNode;
			}

			else {
				currentNode.setRight(newNode);
				newNode.setParent(currentNode);
				weight++;
				return newNode;
			}
		} else if (key.compareTo(currentNode.getKey()) < 0) {
			Node<K, T> left = currentNode.getLeft();

			if (left != null) {
				Node<K, T> addedNode = addRecursive(key, value, left, newNode);

				if (addedNode != null) {
					left.update();
				}

				return addedNode;
			}

			else {
				currentNode.setLeft(newNode);
				newNode.setParent(currentNode);
				weight++;
				return newNode;
			}
		} else {
			return null;
		}
	}

	// --------------------------------------------------------------------------------

	@Override
	public boolean update(K key, T value) {

		Node<K, T> nodeToUpdate = searchNode(key);

		if (nodeToUpdate != null) {
			nodeToUpdate.setValue(value);

			return true;

		}

		else {

			return false;

		}

	}

	// --------------------------------------------------------------------------------

	@Override
	public boolean remove(K key) {
		if (root != null) {
			return removeRecursive(key, root, null);
		} else {
			return false;
		}
	}

	// --------------------------------------------------------------------------------

	private boolean removeRecursive(K key, Node<K, T> currentNode, Node<K, T> parent) {
		if (currentNode != null) {
			if (key.compareTo(currentNode.getKey()) < 0) {
				return removeRecursive(key, currentNode.getLeft(), currentNode);
			} else if (key.compareTo(currentNode.getKey()) > 0) {
				return removeRecursive(key, currentNode.getRight(), currentNode);
			} else {
				if (currentNode.getLeft() == null && currentNode.getRight() == null) {

					if (currentNode == root) {
						root = null;
					} else {
						if (parent.getRight() != null && parent.getRight() == currentNode) {
							parent.setRight(null);
						} else {
							parent.setLeft(null);
						}
					}

				} else if (currentNode.getRight() == null) {

					if (currentNode == root) {
						root.getLeft().setParent(null);
						root = root.getLeft();
					} else {
						currentNode.getLeft().setParent(parent);

						if (parent.getRight() != null && parent.getRight() == currentNode)
							parent.setRight(currentNode.getLeft());

						else
							parent.setLeft(currentNode.getLeft());
					}

				} else if (currentNode.getLeft() == null) {

					if (currentNode == root) {
						root.getRight().setParent(null);
						root = root.getRight();
					} else {
						currentNode.getRight().setParent(parent);

						if (parent.getRight() != null && parent.getRight() == currentNode)
							parent.setRight(currentNode.getRight());
						else
							parent.setLeft(currentNode.getRight());
					}

				} else {
					Node<K, T> rightMin = getMin(currentNode.getRight());

					currentNode.setKey(rightMin.getKey());
					currentNode.setValue(rightMin.getValue());

					removeRecursive(rightMin.getKey(), rightMin, rightMin.getParent());

				}
				weight--;
				return true;
			}
		} else {
			return false;
		}
	}

	// --------------------------------------------------------------------------------

	private Node<K, T> getMin(Node<K, T> node) {

		while (node.getLeft() != null) {

			node = node.getLeft();

		}

		return node;

	}

	// --------------------------------------------------------------------------------

	@Override
	public T search(K key) {

		if (root != null) {

			return searchRecursive(key, root);

		}

		else {

			return null;

		}

	}

	// --------------------------------------------------------------------------------

	private T searchRecursive(K key, Node<K, T> currentNode) {

		if (key.compareTo(currentNode.getKey()) == 0) {

			return currentNode.getValue();

		}

		else if (key.compareTo(currentNode.getKey()) > 0) {

			return searchRecursive(key, currentNode.getRight());

		}

		else {

			return searchRecursive(key, currentNode.getLeft());

		}

	}

	public List<Person> searchByName(String name) {

		List<Person> theListByName = new ArrayList<Person>();

		if (root == null) {
			return null;
		}

		return searchByName(root, theListByName, name);
	}

	private List<Person> searchByName(Node<K, T> current, List<Person> theListByName, String name) {

		if (current == null) {
			return theListByName;
		}

		if (current.getValue().getName().equalsIgnoreCase(name)) {
			theListByName.add(current.getValue());
		}

		searchByName(current.getLeft(), theListByName, name);
		searchByName(current.getRight(), theListByName, name);

		return theListByName;
	}

	public List<Person> searchByLastName(String name) {

		List<Person> theListByName = new ArrayList<Person>();

		if (root == null) {
			return null;
		}

		return searchByLastName(root, theListByName, name);
	}

	private List<Person> searchByLastName(Node<K, T> current, List<Person> theListByName, String name) {

		if (current == null) {
			return theListByName;
		}

		if (current.getValue().getLastName().equalsIgnoreCase(name)) {
			theListByName.add(current.getValue());
		}

		searchByLastName(current.getLeft(), theListByName, name);
		searchByLastName(current.getRight(), theListByName, name);

		return theListByName;
	}

	public List<Person> searchByNameAndLastName(String name) {

		List<Person> theListByName = new ArrayList<Person>();

		if (root == null) {
			return null;
		}

		return searchByNameAndLastName(root, theListByName, name);
	}

	private List<Person> searchByNameAndLastName(Node<K, T> current, List<Person> theListByName, String name) {

		if (current == null) {
			return theListByName;
		}

		if ((current.getValue().getName() + " " + current.getValue().getLastName()).equalsIgnoreCase(name)) {
			theListByName.add(current.getValue());
		}

		searchByNameAndLastName(current.getLeft(), theListByName, name);
		searchByNameAndLastName(current.getRight(), theListByName, name);

		return theListByName;
	}

	private Node<K, T> searchNode(K key) {
		if (root != null) {

			return searchNodeRecursive(key, root);

		}

		else {

			return null;

		}
	}

	private Node<K, T> searchNodeRecursive(K key, Node<K, T> currentNode) {

		if (key.compareTo(currentNode.getKey()) == 0) {

			return currentNode;

		}

		else if (key.compareTo(currentNode.getKey()) > 0) {

			return searchNodeRecursive(key, currentNode.getRight());

		}

		else {

			return searchNodeRecursive(key, currentNode.getLeft());

		}

	}
	// --------------------------------------------------------------------------------

	public int getWeight() {
		return weight;
	}

	// --------------------------------------------------------------------------------

	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Node<K, T> root) {

		if (root == null) {

			return 0;

		} else {

			return 1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight()));

		}

	}

	public boolean isEmpty() {
		return root == null;
	}

	// For testing purposes
	protected Node<K, T> getRoot() {
		return root;
	}

	// --------------------------------------------------------------------------------

	public void preOrden(Node<K, T> node) {
		if (node != null) {
			System.out.print(node.getKey() + ", ");
			preOrden(node.getLeft());
			preOrden(node.getRight());
		}
	}

	public void inOrden(Node<K, T> n) {
		if (n != null) {
			inOrden(n.left);
			System.out.print(n.value + ", ");
			inOrden(n.right);
		}
	}

	public void postOrden(Node<K, T> n) {
		if (n != null) {
			postOrden(n.left);
			postOrden(n.right);
			System.out.print(n.value + ", ");
		}
	}

	public T searchNode2(K dato) {
		return root == null ? null : root.search(dato);
	}

}

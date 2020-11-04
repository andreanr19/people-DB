package model.data_structures.avl;

import model.Person;
import model.data_structures.abb.BinarySearchTree;
import model.data_structures.abb.Node;

public class AVLTree<K extends Comparable<K>, T extends Person> extends BinarySearchTree<K, T> {

	public int height() {
		return height(root);
	}

	private int height(Node<K, T> node) {
		if (node == null)
			return -1;
		return node.getHeight();

	}

	public int size() {
		return size(root);
	}

	private int size(Node<K, T> node) {
		if (node == null)
			return 0;
		return node.getSize();
	}

	public void put(K key, T data) {
		root = put(root, data, key);
	}

	public Node<K, T> find(K key, Node<K, T> node) {
		if (node == null) {
			return null;
		}
		if (node.getKey().compareTo(key) == 0) {
			return node;
		} else if (node.compareTo(key) > 0) {
			return find(key, node.getLeft());
		} else {
			return find(key, node.getRight());
		}
	}

	private Node<K, T> put(Node<K, T> node, T data, K key) {
		if (node == null)
			return new Node<K, T>(key, data, 0, 1);

		if (node.compareTo(key) > 0) {
			node.setLeft(put(node.getLeft(), data, key));
		} else if (node.compareTo(key) < 0) {
			node.setRight(put(node.getRight(), data, key));
		} else {
			node.setValue(data);
			node.setKey(key);
			return node;
		}
		node.setSize(1 + size(node.getLeft()) + size(node.getRight()));
		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
		return balance(node);
	}

	private Node<K, T> balance(Node<K, T> node) {
		if (balanceFactor(node) > 1) {
			if (balanceFactor(node.getRight()) < 0) {
				node.setRight(rotateRight(node.getRight()));
			}
			node = rotateLeft(node);
		} else if (balanceFactor(node) < -1) {
			if (balanceFactor(node.getLeft()) > 0) {
				node.setLeft(rotateLeft(node.getLeft()));
			}
			node = rotateRight(node);
		}
		return node;
	}

	private int balanceFactor(Node<K, T> node) {
		return height(node.getRight()) - height(node.getLeft());
	}

	private Node<K, T> rotateRight(Node<K, T> node) {
		Node<K, T> y = node.getLeft();
		node.setLeft(y.getRight());
		y.setRight(node);
		y.setSize(node.getSize());
		node.setSize(1 + size(node.getLeft()) + size(node.getRight()));
		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
		y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
		return y;
	}

	private Node<K, T> rotateLeft(Node<K, T> node) {
		Node<K, T> y = node.getRight();
		node.setRight(y.getLeft());
		y.setLeft(node);
		y.setSize(node.getSize());
		node.setSize(1 + size(node.getLeft()) + size(node.getRight()));
		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
		y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
		return y;
	}

	public void delete(K key) {
		root = delete(root, key);
	}

	private Node<K, T> delete(Node<K, T> node, K key) {

		if (node.compareTo(key) > 0) {
			node.setLeft(delete(node.getLeft(), key));
		} else if (node.compareTo(key) < 0) {
			node.setRight(delete(node.getRight(), key));
		} else {
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else {
				Node<K, T> y = node;
				node = min(y.getRight());
				node.setRight(deleteMin(y.getRight()));
				node.setLeft(y.getLeft());
			}
		}
		node.setSize(1 + size(node.getLeft()) + size(node.getRight()));
		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
		return balance(node);
	}

	private Node<K, T> deleteMin(Node<K, T> node) {
		if (node.getLeft() == null)
			return node.getRight();
		node.setLeft(deleteMin(node.getLeft()));
		node.setSize(1 + size(node.getLeft()) + size(node.getRight()));
		node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
		return balance(node);
	}

	private Node<K, T> min(Node<K, T> node) {
		if (node.getLeft() == null)
			return node;
		return min(node.getLeft());
	}

}

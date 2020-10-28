package model.data_structures.abb;

public interface BinarySearchTreeInterface<K extends Comparable<K>, V> {

	public boolean add(K key, V value, int height, int size);

	public boolean update(K key, V value);

	public boolean remove(K key);

	public V search(K key);

	public int getWeight();

	public int getHeight();

	public boolean isEmpty();

}

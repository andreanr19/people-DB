package model.data_structures_trie;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * Represents a node in a Trie
 * @author andreanunezrodriguez
 *
 */
public class TrieNode implements Serializable {

	private HashMap<Character, TrieNode> children;
	private String text;
	private boolean isWord;
	
	/** Create a new TrieNode*/
	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
		text="";
		isWord= false;
	}
	
	/**Create a new TrieNode given a text String to store in it*/
	public TrieNode(String text) {
		this();
		this.text= text;
	}
	
	public TrieNode getChild(Character c) {
		return children.get(c);
	}
	
	public TrieNode insert(Character c) {
		
		if(children.containsKey(c)) {
			return null;
		}
		TrieNode next = new TrieNode(text + c.toString());
		children.put(c, next);
		return next;
	}
	
	public String getText() {
		return text;
	}
	
	public void setEndsWord(boolean b) {
		
		isWord=b;
	}
	
	public boolean endsWord() {
		return isWord;
	}
	public Set<Character> getValidNextCharacters(){
		return children.keySet();
	}
}

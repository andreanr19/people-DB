package model.data_structures_trie;

import java.util.LinkedList;
import java.util.List;


public class AutocompleteTrie {

	public TrieNode root;
	private int size;
	
	public AutocompleteTrie() {
		root= new TrieNode();
		size= 0;
	}
	
	public boolean addWord(String word) {
		String wordToAdd= word.toLowerCase();
		TrieNode node = root;
		for(int i=0; i < wordToAdd.length(); i++) {
			char c= wordToAdd.charAt(i);
			if(node.getValidNextCharacters().contains(c)) {
				node= node.getChild(c);
			}else {
				node= node.insert(c);
			}
		}
		if(!node.endsWord()) {
			node.setEndsWord(true);
			size++;
			return true;
		}
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isWord(String word) {
		String wordToCheck= word.toLowerCase();
		TrieNode node = root;
		for(int i=0; i< wordToCheck.length(); i++) {
			char c= wordToCheck.charAt(i);
			if(node.getValidNextCharacters().contains(c)) {
				node= node.getChild(c);
			}else {
				return false;
			}
		}
		if(node.endsWord()) {
			return true;
		}
		return false;
	}
	
	public List<String> predictCompletions(String prefix, int numCompletions){
		String prefixToCheck = prefix.toLowerCase();
		List<String> result= new LinkedList<String>();
		TrieNode node= root;
		for(int i=0; i < prefixToCheck.length();i++) {
			char c = prefixToCheck.charAt(i);
			if(node.getValidNextCharacters().contains(c)) {
				node = node.getChild(c);
			}else {
				return result;
			}
		}
		int count = 0;
		if(node.endsWord()) {
			result.add(node.getText());
			count++;
		}
		
		List<TrieNode> nodeQueue = new LinkedList<TrieNode>();
		List<Character> childrenC = new LinkedList<Character>(node.getValidNextCharacters());
		
		for(int i=0; i< childrenC.size(); i++) {
			char c = childrenC.get(i);
			nodeQueue.add(node.getChild(c));
		}
		while(!nodeQueue.isEmpty() && count < numCompletions) {
			TrieNode tn = nodeQueue.remove(0);
			if(tn.endsWord()) {
				result.add(tn.getText());
				count++;
			}
			
			List<Character> cs= new LinkedList<Character>(tn.getValidNextCharacters());
			for(int i=0; i < cs.size(); i++) {
				char c = cs.get(i);
				nodeQueue.add(tn.getChild(c));
			}
		}
		return result;
	}
	
 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}


	public TrieNode getRoot() {
		return root;
	}


	public void setRoot(TrieNode root) {
		this.root = root;
	}
}


























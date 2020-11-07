package model.data_structure_trie;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import model.data_structures_trie.AutocompleteTrie;

class AutocompleteTrieTest {

	private String wordsFile = "data/names.small.txt";

	AutocompleteTrie emptyTrie;
	AutocompleteTrie smallTrie;
	AutocompleteTrie largeTrie;

	@Before
	public void setUp() throws Exception {

		emptyTrie = new AutocompleteTrie();
		smallTrie = new AutocompleteTrie();
		largeTrie = new AutocompleteTrie();

		smallTrie.addWord("Andrea");
		smallTrie.addWord("ANdReA");
		smallTrie.addWord("animal");
		smallTrie.addWord("Ann");
		smallTrie.addWord("Andd");
		smallTrie.addWord("Adri");
		smallTrie.addWord("Alejandra");
		smallTrie.addWord("Danna");
		smallTrie.addWord("Camilo");

		BufferedReader reader = null;
		try {
			String nextWord;
			reader = new BufferedReader(new FileReader(wordsFile));
			while ((nextWord = reader.readLine()) != null) {
				largeTrie.addWord(nextWord);
			}
		} catch (IOException e) {
			System.err.println("Problem loading dictionary file: " + wordsFile);
			e.printStackTrace();
		}

	}

	@Test
	public void sizeTest() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("Testing size for empty trie", 0, emptyTrie.size());
		assertEquals("Testing size for empty trie", 8, smallTrie.size());
		assertEquals("Testing size for large trie", 4438, largeTrie.size());

	}

	@Test
	public void isWordTest() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("Testing isWord on emptyTrie: Andrea", false, emptyTrie.isWord("Andrea"));
		assertEquals("Testing isWord on smallTrie: Andrea", true, smallTrie.isWord("Andrea"));

		assertEquals("Testing isWord on emptyTrie: animal", true, smallTrie.isWord("animal"));
		assertEquals("Testing isWord on smallTrie: animal", true, largeTrie.isWord("animal"));

		assertEquals("Testing isWord on emptyTrie: animalww", false, smallTrie.isWord("animalww"));
		assertEquals("Testing isWord on smallTrie: animalww", false, largeTrie.isWord("animalww"));

		assertEquals("Testing isWord on emptyTrie: empty string", false, emptyTrie.isWord(""));
		assertEquals("Testing isWord on smallTrie: empty string", false, smallTrie.isWord(""));
		assertEquals("Testing isWord on largeTrie: empty string", false, largeTrie.isWord(""));

	}

	@Test
	public void addWord() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("Asserting camillow is not in emptyTrie", false, emptyTrie.isWord("camillow"));
		assertEquals("Asserting camillow is not in smallTrie", false, smallTrie.isWord("camillow"));
		assertEquals("Asserting camillow is not in largeTrie", false, largeTrie.isWord("camillow"));

		emptyTrie.addWord("camillow");
		smallTrie.addWord("camillow");
		largeTrie.addWord("camillow");

		assertEquals("Asserting camillow is in empty dict", true, emptyTrie.isWord("camillow"));
		assertEquals("Asserting camillow is in small dict", true, smallTrie.isWord("camillow"));
		assertEquals("Asserting camillow is in large dict", true, largeTrie.isWord("camillow"));
	}

	@Test
	public void predictCompletionsTest() {
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> completions;
		completions = smallTrie.predictCompletions("", 0);
		assertEquals(0, completions.size());

		completions = smallTrie.predictCompletions("", 4);
		assertEquals(4, completions.size());

		assertTrue(completions.contains("ann"));

	}

}

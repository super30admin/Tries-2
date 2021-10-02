// Time Complexity : O(n*l*(26^l)), n -> Number of words, l -> Length of each word
// Space Complexity : O(n*l)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem1;

import java.util.ArrayList;
import java.util.List;

public class WordSquares {
	List<List<String>> res;
	TrieNode root;

	public List<List<String>> wordSquares(String[] words) {
		res = new ArrayList<>();
		if (words == null || words.length == 0) {
			return res;
		}

		root = new TrieNode();

		for (String word : words) {
			insert(word);
		}

		List<String> list = new ArrayList<>();

		for (String word : words) {
			list.add(word);
			backtrack(list, word.length());
			list.remove(list.size() - 1);
		}

		return res;
	}

	private void backtrack(List<String> list, int len) {
		// Base
		if (list.size() == len) {
			res.add(new ArrayList<>(list));
			return;
		}
		// Logic
		StringBuilder sb = new StringBuilder();
		int index = list.size();

		for (String word : list) {
			sb.append(word.charAt(index));
		}
		List<String> words = startsWith(sb.toString());

		for (String word : words) {
			list.add(word);
			backtrack(list, word.length());
			list.remove(list.size() - 1);
		}

	}

	private void insert(String word) {
		TrieNode curr = root;

		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (curr.children[ch - 'a'] == null) {
				curr.children[ch - 'a'] = new TrieNode();
			}
			curr.prefixWords.add(word);
			curr = curr.children[ch - 'a'];
		}
	}

	private List<String> startsWith(String prefix) {
		TrieNode curr = root;

		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			if (curr.children[ch - 'a'] == null) {
				return new ArrayList<>();
			}
			curr = curr.children[ch - 'a'];
		}

		return curr.prefixWords;
	}

	private void print(List<List<String>> wordSquares) {
		for (List<String> wordSquare : wordSquares) {
			for (String word : wordSquare) {
				System.out.println(word);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		WordSquares obj = new WordSquares();
		String[] words = { "area", "lead", "wall", "lady", "ball" };

		List<List<String>> wordSquaresList = obj.wordSquares(words);
		obj.print(wordSquaresList);
	}

}

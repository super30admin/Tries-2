package tries2;

import java.util.ArrayList;
import java.util.List;

public class WordSquares {
	//Time Complexity : O(n * l * 26^l), where n is the number of words and l is the length of each word
	//Space Complexity : O(n * l)
	//Did this code successfully run on Leetcode : Yes
	//Any problem you faced while coding this : No
	class TrieNode {
		TrieNode[] children;
		List<String> words;
		public TrieNode() {
			children = new TrieNode[26];
			words = new ArrayList<>();
		}
	}

	TrieNode root;
	private void insert(String word) {
		TrieNode curr = root;
		for(char c: word.toCharArray()) {
			if(curr.children[c - 'a'] == null)
				curr.children[c - 'a'] = new TrieNode();
			curr = curr.children[c - 'a'];
			curr.words.add(word);
		}
	}

	private List<String> search(String prefix) {
		TrieNode curr = root;
		for(char c: prefix.toCharArray()) {
			if(curr.children[c - 'a'] == null)
				return new ArrayList<>();
			curr = curr.children[c - 'a'];
		}
		return curr.words;
	}

	public List<List<String>> wordSquares(String[] words) {
		List<List<String>> result = new ArrayList<>();
		// null
		if(words == null || words.length == 0)
			return result;
		root = new TrieNode();
		for(String word: words)
			insert(word);

		List<String> li = new ArrayList<>();
		for(int i=0; i<words.length; i++) {
			// action
			li.add(words[i]);
			// recurse
			backtrack(li, result, words[0].length());
			// backtrack
			li.remove(li.size() - 1);
		}
		return result;
	}

	private void backtrack(List<String> li, List<List<String>> result, int k) {
		// base
		if(li.size() == k) {
			result.add(new ArrayList<>(li));
			return;
		}

		// logic
		int size = li.size();
		StringBuilder prefix = new StringBuilder();
		for(String word: li)
			prefix.append(word.charAt(size));

		List<String> words = search(prefix.toString());
		for(int i=0; i<words.size(); i++) {
			// action
			li.add(words.get(i));
			// recurse
			backtrack(li, result, k);
			// backtrack
			li.remove(li.size() - 1);
		}
	}
}

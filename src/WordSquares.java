import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//Time Complexity : O(NL26^L)
//Space Complexity : O(NL)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**
 * Create a Trie with maintaining index of the word at each and every character
 * of that word. So, if a character is present in two strings, both indices will
 * be added to that character in the trie. Now apply backtracking by adding 1st
 * word to the list and step as 1 since 0th is done already. Get the characters
 * from the step index and form the prefix. Check if that prefix exists and then
 * find the indices of that prefix. Iterate over those words and add them to the
 * list and increment step and apply backtracking again. Then remove the last
 * added word. If words reach expected length, then push to final array and
 * return that result.
 *
 */
class Solution {
	int n;
	String[] words;
	TrieNode trie;

	public List<List<String>> wordSquares(String[] words) {
		this.n = words[0].length();
		this.words = words;
		List<List<String>> res = new ArrayList<>();
		buildTrie(words);
		for (String word : words) {
			LinkedList<String> list = new LinkedList<>();
			list.add(word);
			backtrack(1, list, res);
		}
		return res;
	}

	public void backtrack(int step, LinkedList<String> list, List<List<String>> res) {
		if (step == n) {
			res.add(new ArrayList<>(list));
			return;
		}

		StringBuilder sb = new StringBuilder();
		for (String word : list) {
			sb.append(word.charAt(step));
		}

		for (int idx : getWordsWithPrefix(sb.toString())) {
			list.addLast(words[idx]);
			backtrack(step + 1, list, res);
			list.removeLast();
		}
	}

	public List<Integer> getWordsWithPrefix(String prefix) {
		TrieNode root = trie;
		for (char c : prefix.toCharArray()) {
			if (root.children.containsKey(c)) {
				root = root.children.get(c);
			} else {
				return new ArrayList<>();
			}
		}
		return root.wordList;
	}

	public void buildTrie(String[] words) {
		trie = new TrieNode();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			TrieNode root = trie;
			for (char l : word.toCharArray()) {
				if (root.children.containsKey(l)) {
					root = root.children.get(l);
				} else {
					TrieNode newNode = new TrieNode();
					root.children.put(l, newNode);
					root = newNode;
				}
				root.wordList.add(i);
			}
		}
	}
}

class TrieNode {
	Map<Character, TrieNode> children = new HashMap<>();
	List<Integer> wordList = new ArrayList<>();
}
import java.util.ArrayList;
import java.util.List;

/*
* Time Complexity of the algorithm is O(n!) where n factorial. or exponential. 
* space Complexity is constant.
*/

public class WordSquares {

	class TrieNode {

		TrieNode[] children;

		List<String> startsWith;

		public TrieNode() {
			children = new TrieNode[26];
			startsWith = new ArrayList<>();
		}
	}

	private TrieNode buildTrie(String[] words) {

		TrieNode root = new TrieNode();

		for (String word : words) {

			TrieNode curr = root;

			for (int i = 0; i < word.length(); i++) {

				char c = word.charAt(i);

				if (curr.children[c - 'a'] == null) {

					curr.children[c - 'a'] = new TrieNode();
				}

				curr = curr.children[c - 'a'];
				curr.startsWith.add(word);

			}
		}

		return root;
	}

	private List<String> startWith(String prefix, TrieNode root) {

		TrieNode curr = root;

		for (int i = 0; i < prefix.length(); i++) {

			char c = prefix.charAt(i);

			if (curr.children[c - 'a'] == null) {

				return new ArrayList<>();

			}

			curr = curr.children[c - 'a'];

		}

		return curr.startsWith;

	}

	List<List<String>> result;

	public List<List<String>> wordSquares(String[] words) {

		result = new ArrayList<>();

		if (words == null || words.length == 0) {
			return result;
		}

		TrieNode root = buildTrie(words);

		List<String> li = new ArrayList<>();

		for (String word : words) {

			// action

			li.add(word);

			// recurse

			backtrack(li, root, word.length());

			// backtrack
			li.remove(li.size() - 1);

		}

		return result;
	}

	private void backtrack(List<String> li, TrieNode root, int length) {

		// base
		if (li.size() == length) {
			result.add(new ArrayList<>(li));
			return;
		}

		// logic

		// get the prefix from the my list

		StringBuilder sb = new StringBuilder();

		int i = li.size();

		for (String word : li) {

			sb.append(word.charAt(i));
		}

		List<String> startWithList = startWith(sb.toString(), root);

		for (String word : startWithList) {

			li.add(word);

			// recurse

			backtrack(li, root, word.length());

			// backtrack
			li.remove(li.size() - 1);

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

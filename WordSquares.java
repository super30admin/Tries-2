// Time Complexity: O(N*26^L*L) where L is the length of the trie, N is the number of words
// Space Complexity: O(N*L)

class Solution {
	public List<List<String>> wordSquares(String[] words) {
		// init
		List<List<String>> result = new LinkedList<>();
		TrieNode root = new TrieNode();
		int limit = words[0].length();

		// construct the Trie
		constructTrie(root, words);

		for(String word: words) {
			List<String> square = new LinkedList<>();
			square.add(word);
			backtrack(root, limit, words, result, square, 1);
		}

		return result;
	}

	private void backtrack(TrieNode root, int limit, String[] words,
		             List<List<String>> result, List<String>, state, int index) {
		if(index == limit) {
			result.add(new LinkedList<String>(state));
			return;
		}

		StringBuilder prefix = new StringBuilder();
		for(String word: state) {
			prefix.append(word.charAt(index));
		}

		for(Integer wordIndex: getWordIndexListWithPrefix(root, prefix.toString())) {
			String word = words[wordIndex];
			state.add(word);
			backtrack(root, limit, words, result, state, index + 1);
			state.remove(state.size() - 1);
		}

	}

	private List<Integer> getWordIndexListWithPrefix(TrieNOde root, String prefix) {
		TrieNode cursor = root;

		for(char ch: prefix.toCharArray()) {
			TrieNode child = cursor.children.get(ch);
			if(child == null) return new ArrayList<>();
			cursor = child;
		}

		return cursor.wordIndexList;
	}

	private void constructTrie(TrieNode root, String[] words) {
		for(int i = 0; i < words.length; i++) {
			insert(root, words[i], i);
		}
	}

	private void insert(TrieNode root, String word, int index) {
		TrieNode cursor = root;

		for(char ch: word.toCharArray()) {
			TrieNode child = cursor.children.get(ch);
			if(child == null) {
				child = new TrieNode();
				cursor.children.put(ch, child);
			}
			cursor = child;
			curosr.wordIndexList.add(index);
		}
	}
}

class TrieNode {
	Map<Character, TrieNode> children;
	List<Integer> wordIndexList;
	boolean endOfWord;

	public TrieNode() {
		children = new HashMap<>();
		wordIndexList = new LinkedList<>();
	}

}
/**
 * Builds a prefix trie storing all words for fast prefix lookup and uses backtracking to build word squares.
 * At each step, constructs column-wise prefix and retrieves candidates from trie for DFS expansion.
 * Time: O(N * L^2 + backtracking), Space: O(N * L^2) for trie and recursion depth O(L).
 */
public class WordSquare {
    List<List<String>> result;
    TrieNode root;

    class TrieNode {
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.startsWith = new ArrayList<>();
        }
    }

    private void insert(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.startsWith.add(word);
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        this.result = new ArrayList<>();
        this.root = new TrieNode();

        for (String word : words) {
            insert(word);
        }

        List<String> path = new ArrayList<>();
        for (String word : words) {
            path.add(word);
            dfs(words, path);
            path.remove(path.size() - 1);
        }

        return result;
    }

    private List<String> search(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }

        return curr.startsWith;
    }

    private void dfs(String[] words, List<String> path) {
        if (path.size() == words[0].length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        int idx = path.size();
        StringBuilder prefix = new StringBuilder();
        for (String str : path) {
            prefix.append(str.charAt(idx));
        }

        for (String word : search(prefix.toString())) {
            path.add(word);
            dfs(words, path);
            path.remove(path.size() - 1);
        }
    }
}

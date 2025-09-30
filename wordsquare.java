//Time Complexity: O(n ^ L) where n is the number of words that starts with the prefix and L is the length of each word.
//Space Complexity:O(N^2 * L^2) - N is the number of given words and L is the length of each word.

// Insert all words into a trie; at each node store a startsWith list of words passing through it.
// Build a square row by row: at depth d, form the needed prefix from column d of words in path, fetch candidates via trie.startsWith(prefix), and try each (add → recurse → remove).
// When path size reaches L (word length), record the square; pruning happens naturally since only prefix-matching words are explored.

class Solution {
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

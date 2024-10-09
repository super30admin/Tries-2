// Time Complexity: O(N ^ l)
// Space Complexity: O(n)

class Solution {
    class TrieNode {
        TrieNode[] children;
        List<String> li;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.li = new ArrayList<>();
        }
    }

    private void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null)
                curr.children[c - 'a'] = new TrieNode();
            curr = curr.children[c - 'a'];
            curr.li.add(word);
        }
    }

    private List<String> prefix(TrieNode root, String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null)
                return new ArrayList<>();
            curr = curr.children[c - 'a'];
        }
        return curr.li;
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        TrieNode root = new TrieNode();
        if (words.length == 0)
            return result;

        for (String word : words)
            insert(root, word);

        List<String> path = new ArrayList<>();

        for (String word : words) {
            // action
            path.add(word);
            // recurse
            backtrack(root, path, result);
            // backtrack
            path.remove(path.size() - 1);
        }

        return result;
    }

    private void backtrack(TrieNode root, List<String> path, List<List<String>> result) {
        // base
        if (path.size() == path.get(0).length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        // logic
        int size = path.size();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            sb.append(path.get(i).charAt(size));
        }

        List<String> words = prefix(root, sb.toString());

        for (String word : words) {
            // action
            path.add(word);
            // recurse
            backtrack(root, path, result);
            // backtrack
            path.remove(path.size() - 1);
        }
    }
}
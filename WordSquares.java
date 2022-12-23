// Time complexity: O (k *n) + O(n ^ n)
// Space complexity: o (k*n) -> Trie + O(n) -> Recurse stack space
class Solution {

    class TrieNode {
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode() {
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }

    TrieNode root;
    List<List<String>> result;

    // Helps construct a trie
    private void insert(String word) {
        TrieNode curr = root;

        for(int i =0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c- 'a'] == null) {
                // create node among children
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c-'a']; // go down
            curr.startsWith.add(word); // add word to starts with list
        }
    }

    // Implement word search prefix
    private List<String> search (String prefix) {
        TrieNode curr = root;
        for (int i =0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(curr.children[c-'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.startsWith; // Entire prefix exists so return the arraylist that
        // has words starting with that prefix
    }

    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) return new ArrayList<>();
        result = new ArrayList<>();
        root = new TrieNode();

        // Have a trie ready with all words and startswith
        for (String word : words) {
            insert(word);
        }

        // Choose one word to start the word square from
        List<String> list = new ArrayList<>();
        for (String word : words) {
            // action
            list.add(word);
            // recurse
            backtrack(list, word.length());
            //backtrack
            list.remove(list.size()-1);
        }

        return result;
    }

    private void backtrack(List<String> list, int len) {
        // base case
        if (list.size() == len) {
            result.add(new ArrayList<>(list));
            return;
        }

        // logic
        StringBuilder sb = new StringBuilder();
        // For all the appended words in the list
        for (String w : list) {
            sb.append(w.charAt(list.size()));
        }

        // Get list of strings that start with SB as prefix
        List<String> startsWith = search(sb.toString());
        for (String word : startsWith) {
            // action
            list.add(word);
            // recurse
            backtrack(list, len);
            //backtrack
            list.remove(list.size()-1);
        }
    }
}
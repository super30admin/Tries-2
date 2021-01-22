// Time Complexity : O(n * 26 ^ l) --> where n is lenght of input words list and l is the lenght of each individual words
// Space Complexity : O(n * l)
// Did this code successfully run on Leetcode (425): Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    
    class TrieNode {
        TrieNode children[];
        List<String> startsWith;
        public TrieNode() {
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }
    
    // TrieNode root;
    private TrieNode buildTree(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr.startsWith.add(word);
                curr = curr.children[c - 'a'];
            }
        }
        return root;
    }
    
    private List<String> search (TrieNode root, String prefix) {
        // search the prefix in the trie
        // return all the strings that starts with that prefix
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.children[c - 'a'] == null) return new ArrayList<>();
            curr = curr.children[c - 'a'];
        }
        return curr.startsWith;
    }
    
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        // edge case
        if (words == null || words.length == 0) return result;
        
        TrieNode root = buildTree(words);
        List<String> li = new ArrayList<>();
        for (String word : words) { // was for first word
            li.add(word); // action
            backtrack(root, li, word.length()); // recurse
            li.remove(li.size() - 1); // backtrack
        }
        return result;
    }
    
    private void backtrack(TrieNode root, List<String> li, int l) {
        // base case
        if (l == li.size()) {
            result.add(new ArrayList<>(li));
            return;
        }
        
        // logic
        StringBuilder sb = new StringBuilder();
        int i = li.size();
        for (String str : li) {
            sb.append(str.charAt(i));
        }
        List<String> allWordsWithPrefix = search(root, sb.toString());
        for (String pre : allWordsWithPrefix) { // for subsequent words in li
            li.add(pre); // action
            backtrack(root, li, l); // recurse
            li.remove(li.size() - 1); // backtrack
        }
    }
}
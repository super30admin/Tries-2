// Approach: Optimized backtracking and Trie
// Time: O(n^k) where k = avg length of word
// Space: O(nk)

import java.util.*;

class ListOfWordSquares {

    TrieNode root;
    List<List<String>> result;

    static class TrieNode {
        TrieNode[] children;
        List<String> startsWith;
        public TrieNode(){
            this.children = new TrieNode[26];
            this.startsWith = new ArrayList<>();
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i<word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c-'a'] == null) {
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
            curr.startsWith.add(word);
        }
    }

    private List<String> startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.children[c-'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c-'a'];
        }
        return curr.startsWith;
    }

    public List<List<String>> wordSquares(String[] words) {

        result = new ArrayList<>();
        if (words == null || words.length == 0) return result;
        root = new TrieNode();

        for (String word : words) {
            insert(word);
        }

        List<String> li = new ArrayList<>();
        for (String word : words) {
            // action
            li.add(word);
            // recurse
            backtrack(li, word.length());
            // backtrack
            li.remove(li.size() - 1);
        }
        return result;
    }

    private void backtrack(List<String> li, int l) {
        // base
        if (li.size() == l) {
            result.add(new ArrayList<>(li));
            return;
        }

        // logic
        StringBuilder prefix = new StringBuilder();
        int idx = li.size();
        for (String word : li) {
            prefix.append(word.charAt(idx));
        }
        List<String> startsWith = startsWith(prefix.toString());
        for (String word : startsWith) {
            // action
            li.add(word);
            // recurse
            backtrack(li, l);
            // backtrack
            li.remove(li.size() - 1);
        }
    }
}
// Backtracking + Trie
// Time Complexity: O(N⋅26^L⋅L)
// Space Complexity: O(N⋅L)
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

import java.util.*;
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
    public void insert(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.startsWith.add(word);
        }
    }
    
    private List<String> search(String prefix) {
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.startsWith;
    } 
    
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        root = new TrieNode();
        for(String word: words) {
            insert(word);
        }
        // backtracking
        result = new ArrayList<>();
        List<String> li = new ArrayList<>();
        for(String word: words) {
            // action
            li.add(word);
            // recurse
            backtrack(li, word.length());
            // backtrack
            li.remove(li.size() - 1);
        }
        return result;
    }
    
    private void backtrack(List<String> li, int k) {
        // base
        if(k == li.size()) {
            // we got our square
            result.add(new ArrayList<>(li));
            return;
        }
        
        // logic
        int i = li.size();
        StringBuilder sb = new StringBuilder();
        for(String s: li) {
            sb.append(s.charAt(i));
        }
        List<String> startsWith = search(sb.toString());
        for(String word: startsWith) {
            // action
            li.add(word);
            // recurse
            backtrack(li, word.length());
            // backtrack
            li.remove(li.size() - 1);
        }
    }
    
}
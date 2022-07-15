// Time Complexity : O(k 26 ^ k)
// Space Complexity : O(k)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    
    class TrieNode {
        TrieNode[] children;
        List<String> words;
        
        TrieNode() {
            children = new TrieNode[26];
            words = new ArrayList<>(); 
        }
    }
    
    
    TrieNode root;
    
    private void insert(String word) {
        TrieNode current = root;
        
        for(char ch: word.toCharArray()) {
            if(current.children[ch - 'a'] == null) {
                current.children[ch - 'a'] = new TrieNode();
            }
            
            current.words.add(word);
            
            current = current.children[ch - 'a'];
        }
    }
    
    private List<String> searchByPrefix(String prefix) {
        TrieNode current = root;
        
        for(char ch: prefix.toCharArray()) {
            if(current.children[ch - 'a'] == null) {
                return new ArrayList<>();
            }
            
            current = current.children[ch - 'a'];
        }
        
        return current.words;
    }
    
    List<List<String>> result;
    
    public List<List<String>> wordSquares(String[] words) {
        if(words == null || words.length == 0) {
            return new ArrayList<>();
        }
        
        root = new TrieNode();
        result = new ArrayList<>();
        
        for(String word: words) {
            insert(word);
        }
        
        List<String> list = new ArrayList<>();
        
        for(String word: words) {
            // action
            list.add(word);
            // recurse
            backtrack(list);
            // backtrack
            list.remove(list.size() - 1);
        }
        
        return result;
    }
    
    private void backtrack(List<String> list) {
        
        // base case
        if(list.size() == list.get(0).length()) {
            result.add(new ArrayList<>(list));
            return;
        }
        
        //logic
        StringBuilder sb = new StringBuilder();
        
        for(String str: list) {
            sb.append(str.charAt(list.size()));
        }
        
        List<String> words = searchByPrefix(sb.toString());
        
        for(String word: words) {
            // action
            list.add(word);
            
            // recurse
            backtrack(list);
            
            // backtrack
            list.remove(list.size() - 1);
        }
    }
}
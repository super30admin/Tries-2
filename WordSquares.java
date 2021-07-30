// Time Complexity : O(N*K)
// Space Complexity : O(N*K)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// We make use of Tries and DFS to find all possible Word Squares.

class Solution {
    
    public Solution() {
        head = new TrieNode();
    }
    
    class TrieNode{
        TrieNode [] children;
        List<String> words;
        public TrieNode(){
            this.children = new TrieNode[26];
            words = new ArrayList<>();
        }
    } 
    
    TrieNode head;
    
    public void insert(String word) {
        TrieNode node = head;
        for (int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (node.children[c-'a'] == null)
                node.children[c-'a'] = new TrieNode();
            node = node.children[c-'a'];
            node.words.add(word);
        }
        node.words.add(word);
    }
    
    public List<String> find(String word){
        TrieNode node = head;
        for (int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if (node.children[c-'a'] == null)
                return null;
            node = node.children[c-'a'];
        }
        return node.words;
    }
    
    List<List<String>> result;
    
    public List<List<String>> wordSquares(String[] words) {
     
        for (String word : words)
            insert(word);
    
        result = new ArrayList<>();
        for (String word : words){
            List<String> strings = new ArrayList<>();
            System.out.println(word);
            strings.add(word);
            dfs(strings);
        }
        
        return result;
    }
    
    public void dfs(List<String> strings){
        
        int n = strings.size();
        int m = strings.get(0).length();
                
        if (n == m){
            result.add(new ArrayList<>(strings));
            return;
        }
        
        String s = new String();
        for (int i=0; i<n; i++){
            s += strings.get(i).charAt(n);
        }
        
        List<String> findStrings = find(s);
        if (findStrings == null)
            return;
        else{
            
            for (String findString : findStrings){
                 strings.add(findString);
                 dfs(strings);
                 strings.remove(strings.size()-1);   
            }
            
        }
    }
}
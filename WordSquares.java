// Time Complexity : O(exponential)   
// Space Complexity : O(Nk)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// We can solve this using Tries and backtracking
class Solution {
    class TrieNode{
        TrieNode[] children;
        List<String> startsWith;
        public TrieNode(){
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }
    TrieNode root;
    private void insert(String word){
        TrieNode curr = root;
        for(int i =0; i<word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c-'a'] == null){
                curr.children[c-'a'] = new TrieNode();
                
            }
            curr = curr.children[c-'a'];
            curr.startsWith.add(word);
        }
    }
    private List<String> search(String prefix){
        TrieNode curr = root;
        for(int i =0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c-'a'] == null)
                return new ArrayList<>();
            curr = curr.children[c-'a'];
        
        }
        return curr.startsWith;
    }
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        if(words == null || words.length == 0) return new ArrayList<>();
        result = new ArrayList<>();
        root = new TrieNode();
        List<String> li = new ArrayList<>();
        for(String word : words){
            insert(word);
        }
        for(String word : words){
            li.add(word);
            backtrack(li, word.length());
            li.remove(li.size() -1);
        }
        return result;
    }
    public void backtrack(List<String> li, int k){
        if(li.size() == k){
            result.add(new ArrayList<>(li));
            return;
        }
        int i = li.size();
        StringBuilder prefix = new StringBuilder();
        for(String w : li){
            prefix.append(w.charAt(i));
        }
        List<String> startsWith = search(prefix.toString());
        for(String word : startsWith){
            li.add(word);
            backtrack(li,k);
            li.remove(li.size()-1);
        }
    }
}
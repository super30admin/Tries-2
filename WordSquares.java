// Time Complexity : O(Nx26^LxL)
// Space Complexity : O(NxL)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
// Backtraking with Trie
class Solution {
    
    class TrieNode{
        TrieNode[] children;
        List<String> startWith;
        public TrieNode(){
            children = new TrieNode[26];
            startWith = new ArrayList<>(); 
        }
    }
    
    private TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for(String word: words){
            TrieNode curr = root;
            for(int i=0; i<word.length(); i++){
                char c = word.charAt(i);
                if(curr.children[c-'a'] == null){
                    curr.children[c-'a'] = new TrieNode(); 
                }
                curr.startWith.add(word);
                curr = curr.children[c-'a'];
            }
        }
        return root;
    }
    
    private List<String> searchPrefix(TrieNode root, String prefix){
        TrieNode curr = root;
        for(int i=0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c-'a'] == null){
                return new ArrayList<>(); 
            }
            curr = curr.children[c-'a'];
        }
        return curr.startWith;
        
    }
    
    private List<List<String>> result = new ArrayList<>();
    
    public List<List<String>> wordSquares(String[] words) {
        if(words==null || words.length==0)
            return result;
        
        TrieNode root = buildTrie(words);
        List<String> li = new ArrayList<>();
        for(String word : words){
            //Action
            li.add(word);
            
            //recurse
            backtracking(root, li, word.length());
            
            //backtracking
            li.remove(li.size()-1);
        }
        return result;
    }
    
    private void backtracking(TrieNode root, List<String> li, int len){  
        //base case
        if(li.size()== len){
            result.add(new ArrayList<>(li));
            return;
        }
        
        //logic
        StringBuilder prefix = new StringBuilder();
        int i = li.size();
        for(String word : li){
            prefix.append(word.charAt(i));
        }
        List<String> words = searchPrefix(root, prefix.toString());  
        for(String word :  words){
            li.add(word);
            backtracking(root, li, len);
            li.remove(li.size()-1);
        }
    }
}

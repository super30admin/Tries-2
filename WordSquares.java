// Time Complexity : O(nPl) (n Permutations l)
// Space Complexity : O(n*l)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Create a trie with all words so that we can do prefix search fast
// At each node store all the words starting with the prefix
// Use backtracking approach to form word squares

class Solution {
    class TrieNode{
        TrieNode[] children;
        List<String> prefixWords;
        TrieNode(){
            this.children = new TrieNode[26];
            this.prefixWords = new ArrayList<>();
        }
    }
    TrieNode root;
    void insertWord(String word){
        TrieNode curr = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(curr.children[ch-'a']==null) curr.children[ch-'a'] = new TrieNode();
            curr = curr.children[ch-'a'];
            curr.prefixWords.add(word);
        }
    }
    List<String> startsWith(String prefix){
        TrieNode curr = root;
        for(int i=0;i<prefix.length();i++){
            char ch = prefix.charAt(i);
            if(curr.children[ch-'a']==null) return new ArrayList<>();
            curr = curr.children[ch-'a'];
        }
        return curr.prefixWords;
    }
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        root = new TrieNode();
        result = new ArrayList<>();
        for(String word:words){
            insertWord(word);
        }
        List<String> li = new ArrayList<>();
        for(String word:words){
            //action
            li.add(word);
            //recurse
            backtrack(li, word.length());
            //backtrack
            li.remove(li.size()-1);
        }
        return result;
    }
    void backtrack(List<String> li, int len){
        if(li.size()==len) {
            result.add(new ArrayList<>(li));
            return;
        }
        StringBuilder prefix = new StringBuilder();
        int idx = li.size();
        for(String s:li){
            prefix.append(s.charAt(idx));
        }
        List<String> words = startsWith(prefix.toString());
        for(String word:words){
            //action
            li.add(word);
            //recurse
            backtrack(li, len);
            //backtrack
            li.remove(li.size()-1);
        }
    }
}
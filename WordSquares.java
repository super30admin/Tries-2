// Time Complexity : O(N*26^L*L) where n is the no of words and L is the length of each word
// Space Complexity : O(N * L)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Create trie and add all the words to it
// In trie also put list of words which are having that flow in tree of prefix
// Now write baktrack method with passing each of the words in the string array
// Implement logic where the current list size i would be used to fetch the ith character from each word in list
// We form prefix out of it and get the list of words corresponding to it
// Now we will add these words to cur list and recurse
// In the base case if we reach the cur list size equal to size of any words, we add that list to our result
// Finally we will return the result
class Solution {
    TrieNode root;
    List<List<String>> result;
    List<String> cur;
    public List<List<String>> wordSquares(String[] words) {
        root = new TrieNode();
        result = new ArrayList<>();
        cur = new ArrayList<>();
        for(String word: words){
            insert(word);
        }
        for(String word: words){
            cur.add(word);
            wordSquare1();
            cur.remove(cur.size()-1);
        }
        return result;
    }
    public void wordSquare1(){
        //base
        if(cur.size() == cur.get(0).length()){
            result.add(new ArrayList<>(cur));
            return;
        }
        //recurse
        StringBuilder prefix = new StringBuilder();
        for(String word: cur){
            prefix.append(word.charAt(cur.size()));
        }
        List<String> wordsWithPrefix = getPrefix(prefix.toString());
        for(String word: wordsWithPrefix){
            cur.add(word);
            wordSquare1();
            cur.remove(cur.size()-1);
        }
    }
    public void insert(String word){
        TrieNode node = root;
        for(char ch: word.toCharArray()){
            if(node.children[ch-'a'] == null){
                node.children[ch-'a'] = new TrieNode();
            }
            node = node.children[ch-'a'];
            node.wordList.add(word);
        }
    }
    
    public List<String> getPrefix(String pre){
        TrieNode node = root;
        int i = 0;
        while(i < pre.length()){
            char ch = pre.charAt(i);
            if(node.children[ch-'a'] == null){
                return new ArrayList<>();  //return empty
            } 
            node = node.children[ch-'a'];
            i++;
        }
        return node.wordList;
    }
    
}
class TrieNode{
    TrieNode[] children;
    List<String> wordList;
    TrieNode(){
        children = new TrieNode[26];
        wordList = new ArrayList<>();
    }
}
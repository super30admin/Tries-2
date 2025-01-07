// 425. Word Squares

// Given an array of unique strings words, return all the word squares you can build from words. The same word from words can be used multiple times. You can return the answer in any order.
// A sequence of strings forms a valid word square if the kt row and column read the same string, where 0 <u k < max(numRows, numColumns) .
// • For example, the word sequence ["ball", "area", "lead", "lady"] forms a word square because each word reads the same both horizontally and vertically.
// Example 1:
// Input: words = ["area", "lead", "wall", "lady", "ball"]
// Output: [["ball", "area", "lead", "lady"], ["wall", "area" ,"lead" ," lady"]]| Explanation:
// The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
// Example 2:
// Input: words = ["abat", "baba", "atan", "atal"]
// Output: [ ["baba", "abat"
// "baba"
// "atal"],
// ["baba", "abat", "baba", "atan" ]]
// Explanation:
// The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).


public class Main {
    TrieNode root;
    List<List<String>> result;
    
    class TrieNode{//creating trienode
        TrieNode[] children;
        List<String> startsWith;
    
    public TrieNode(){
        this.children = new TrieNode[26];
        this.startsWith = new ArrayList<>();
      }
   }
    
    private void insert(String word){//adding character to trienode with words at startswith
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            if(curr.children[c-'a'] == null){
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
            curr.startsWith.add(word);
        }
    }
    
    private List<String> searchPrefix(String prefix){// code to find letter in trie and if find all //letter send the word from startswith
        TrieNode curr = root;
        for(char c : prefix.toCharArray()){
            if(curr.children[c-'a'] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c-'a'];
        }
        return curr.startsWith;
    }
    
    public  List<List<String>> wordSquares(String[] words){
        this.root = new TrieNode();
        this.result = new ArrayList<>();
        for(String word: words){
            insert(word);
        }
        
        List<String> path = new ArrayList<>();
        
        for(String word: words){
            //action
            path.add(word);//adding word  "ball"
            
            //recurse
            dfs(words, path);
            
            //backtrack
            path.remove(path.size()-1); //remove word in path to start creating new word square //starting with diff word.
        }
        return result;
    }
    
    private void dfs(String[] words, List<String> path){
        //base
        if(path.size() == words[0].length()){
            result.add(new ArrayList<>(path));
            return;
        }
        
        //logic
        StringBuilder prefix = new StringBuilder();
        int idx = path.size();//no.of words
        for(String word: path){
            prefix.append(word.charAt(idx));
        }
        
        List<String> startsWithPrefix =  searchPrefix(prefix.toString());
        
        for(String word: startsWithPrefix){//calling dfs on the words with correct prefix
            //action
            path.add(word);
            //recurse
            dfs(words, path);
            //backtrack
            path.remove(path.size()-1);
        }
    }
    
    public void main(String[] args) {
        System.out.println("Hello World!");
        
        String[] words1 = {"area", "lead", "wall", "lady", "ball"};
        //o/p - [["ball", "area","lead", "lady"], ["wall", "area" ,"lead" ," lady"]]
        String[] words2 = {"abat", "baba", "atan", "atal"};
        //o/p - [["baba", "abat", "baba", "atal"], ["baba", "abat", "baba", "atan" ]]
        
        List<List<String>> result = wordSquares(words2);

        for (List<String> square : result) {
            System.out.println(square);
        }
    }
}

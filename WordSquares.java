import java.util.ArrayList;
import java.util.List;

// Time Complexity : O(n^n);
// Space Complexity : O(n*l*n*l);  Trie Space(n*l -> for each word , n*l > for the list of words at each node
// Did this code successfully run on Leetcode : Yes


public class WordSquares {
    TrieNode root;
    class TrieNode{
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode(){
            this.children = new TrieNode[26];
            this.startsWith = new ArrayList<>();
        }
    }

    private void insert(String word){
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.startsWith.add(word);
        }
    }

    private List<String> search(String prefix){
        TrieNode curr = root;
        for(char c : prefix.toCharArray()){
            if(curr.children[c - 'a'] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.startsWith;
    }

    List<List<String>> result;
    List<String> path;
    public List<List<String>> wordSquares(String[] words) {

        result = new ArrayList<>();
        path = new ArrayList<>();
        root = new TrieNode();

        for(String word: words){   // insert all the words in the trie TC: O(nl)  SC:n*l*n*l
            insert(word);
        }

        for(String word : words){   // Take one word and go for all other words
            path.add(word);
            helper(words);
            path.remove(path.size()-1);
        }
        return result;
    }

    private void helper(String[] words){

        if(path.size() == words[0].length()){
            result.add(new ArrayList<>(path));
            return;
        }
        StringBuilder currPrefix = new StringBuilder();    // form the prefix to select the next word
        int idx = path.size();
        for(String choosen : path){
            currPrefix.append(choosen.charAt(idx));
        }

        List<String> matchingWords = search(currPrefix.toString());
        for(String word: matchingWords){
            path.add(word);
            helper(words);
            path.remove(path.size()-1);
        }
    }
}
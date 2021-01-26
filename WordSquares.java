package S30.Tries_2;


/*
Time Complexity : O(nlogn)
Space Complexity : O(n)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/

import java.util.ArrayList;
import java.util.List;

public class WordSquares {

    class TrieNode{

        TrieNode[] children;
        List<String> words;

        public TrieNode(){
            this.children = new TrieNode[26];
            this.words = new ArrayList<>();
        }
    }

    public TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();

        for(String word : words){
            addWord(word,root);
        }
        return root;
    }

    public void addWord(String word, TrieNode current){
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(current.children[ch - 'a'] == null){
                current.children[ch - 'a'] = new TrieNode();
            }
            current = current.children[ch - 'a'];
            current.words.add(word);
        }
    }

    public List<String> getAllWordsStartingWith(String prefix,TrieNode current){

        List<String> result = new ArrayList<>();
        for(int i = 0; i < prefix.length(); i++){
            char ch = prefix.charAt(i);
            if(current.children[ch - 'a'] == null) return result;
            current = current.children[ch - 'a'];
        }
        return current.words;
    }


    List<List<String>> result;
    TrieNode root;
    public List<List<String>> wordSquares(String[] words) {
        this.result = new ArrayList<>();
        if(words == null || words.length == 0) return result;
        this.root = buildTrie(words);

        List<String> temp = new ArrayList<>();
        for(String word : words){
            temp.add(word);
            backtrack(temp,root, word.length());
            temp.remove(temp.size()-1);
        }
        return result;

    }

    public void backtrack(List<String> temp, TrieNode root, int length){

        //base
        if(temp.size() == length){
            result.add(new ArrayList<>(temp));
            return;
        }

        //logic
        StringBuilder prefix = new StringBuilder();
        int size = temp.size();
        for(String word : temp){
            prefix.append(word.charAt(size));
        }
        List<String> allwordsStartingWith = getAllWordsStartingWith(prefix.toString(),root);
        for(String word : allwordsStartingWith){
            temp.add(word);
            backtrack(temp,root, word.length());
            temp.remove(temp.size()-1);
        }

    }
}

// Time Complexity : Exponential, but with optimized
// Space Complexity : (n); n - size of word
// Did this code successfully run on Leetcode : Yes

import java.util.ArrayList;
import java.util.List;

public class WordSquares {

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
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c-'a'] == null){
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
            //add word to list
            curr.startsWith.add(word);
        }
    }

    private List<String> search(String prefix){
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c-'a'] == null) return new ArrayList<>();
            curr = curr.children[c-'a'];
        }
        return curr.startsWith;
    }

    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {

        result = new ArrayList();
        if(words == null || words.length == 0) return result;

        root = new TrieNode();
        for(String word: words){ // TC - O(nk)
            insert(word);
        }

        //li is each square being formed
        List<String> li = new ArrayList<>();
        for(int i = 0; i < words.length;i++){
            String curr = words[i];
            //action - add to square
            li.add(curr);
            //recurse
            backtrack(li, words[0].length());
            //backtrack
            li.remove(li.size() - 1);
        }
        return result;
    }

    //k - know size of square to be formed
    private void backtrack(List<String> li, int k){
        //base
        if(li.size() == k){
            result.add(new ArrayList<>(li));
            return;
        }


        //logic
        //prefix will be looking is length of current li
        StringBuilder prefix = new StringBuilder();
        int l= li.size(); // we need to go to that column and get characters
        for(String word: li){
            prefix.append(word.charAt(l));
        }
        List<String> children = search(prefix.toString());
        for(String child: children){
            //action
            li.add(child);
            //recurse
            backtrack(li, k);
            //backtrack
            li.remove(li.size() - 1);
        }

    }
}

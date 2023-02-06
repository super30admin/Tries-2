package s30.tries.tries2;

import java.util.ArrayList;
import java.util.List;



//Algo: use trie a data source instead of input array for faster prefix match. We need to implement
// this using backtracking.

//TC: O( N * 26^L)
public class WordSquares {


    Trie trie;
    List<List<String>> res;
    List<String> curr;
    public List<List<String>> wordSquares(String[] words) {
        trie = new Trie();
        res = new ArrayList();
        curr = new ArrayList();


        for(String word : words){
            trie.insert(word);
        }

        for(String word : words){
            curr.add(word);
            wordSquares();
            curr.remove( curr.size() -1);
        }


        return res;
    }

    private void wordSquares(){
        //base
        int n = curr.size();
        if( curr.get(0).length() == n){
            // System.out.println(res);
            res.add(new ArrayList(curr));
            return;
        }


        StringBuilder prefix = new StringBuilder();
        System.out.println(curr);
        for(String s : curr){
            prefix.append(s.charAt(n));
        }

        System.out.println(prefix + "====" + trie.getPrefix(prefix.toString()));

        for(String s : trie.getPrefix(prefix.toString())){
            curr.add(s);
            wordSquares();
            curr.remove(curr.size() - 1);
        }

    }






}

class Trie{

    TrieNode root;

    public Trie(){
        root = new TrieNode();
    }


    public void insert(String word){

        TrieNode node = root;
        // node.preWords.add(word);
        for(int i = 0; i < word.length(); i++){
            int ch = word.charAt(i) - 'a';
            if(node.children[ch] == null){
                node.children[ch] = new TrieNode();

            }

            node = node.children[ch];
            node.preWords.add(word);
        }

        node.isWord = true;

    }


    public List<String> getPrefix( String prefix){

        TrieNode node = root;

        for(int i=0; i < prefix.length() ; i++){
            int ch = prefix.charAt(i) - 'a';

            if(node.children[ch] == null){
                return new ArrayList();
            }
            else node = node.children[ch];
        }

        return node.preWords;
    }



    public class TrieNode {
        TrieNode[] children;
        List<String> preWords;
        Boolean isWord;


        public TrieNode(){
            children = new TrieNode[26];
            preWords = new ArrayList();
        }
    }
    public static void main(String[] args) {

    }
}

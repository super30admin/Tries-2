import java.util.ArrayList;
import java.util.List;
/*
Creating Trie Data Structure
 */
public class Trie {
    public class TrieNode{
        List<String> words;
        TrieNode[] children;

        TrieNode(){
            words = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    public TrieNode root;

    Trie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode node = root;
        root.words.add(word);
        for(int i = 0; i<word.length(); i++){
            char c = word.charAt(i);

            if(node.children[c-'a'] == null){
                node.children[c-'a'] = new TrieNode();
            }
            node = node.children[c-'a'];
            node.words.add(word);
        }
    }

    public List<String> getPrefixWords(String prefix){
        TrieNode node = root;

        for(int i = 0; i<prefix.length(); i++){
            char c = prefix.charAt(i);

            if(node.children[c-'a'] == null){
                node = null;
                break;
            }
            node = node.children[c-'a'];
        }

        return node == null ? new ArrayList<>() : node.words;
    }
}

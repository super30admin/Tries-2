import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Time Complexity: N*l + n^h
// Space Complexity: O(n^2 * l^2)


public class problem1 {
    class TrieNode{
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode(){
            this.children= new TrieNode[26];
            startsWith= new ArrayList<>();
        }
    }
    List<List<String>> result;
    private TrieNode root;

    private void insert(String word)
    {
        TrieNode curr= root;
        for (char c: word.toCharArray())
        {
            if (curr.children[c-'a'] ==null)
            {
                curr.children[c-'a']= new TrieNode();
            }
            curr= curr.children[c-'a'];
            curr.startsWith.add(word);
        }
    }
    private List<String> searchPrefix(String prefix)
    {
        TrieNode curr= root;
        for (char c: prefix.toCharArray())
        {
            if(curr.children[c-'a']==null)
            {
                return new ArrayList<>();
            }
            curr= curr.children[c-'a'];

        }
        return curr.startsWith;
    }
    public List<List<String>> wordSquares(String[] words)
    {
        this.root= new TrieNode();
        this.result= new ArrayList<>();
        for (String word: words)
        {
            insert(word);
        }
        List<String> path= new ArrayList<>();
        for (String word: words)
        {
            path.add(word); // action
            dfs(words, path); // recurse
            path.remove(path.size()-1); // backtrack
        }
        return result;
    }
    private void dfs(String[] words,List<String> path ){
        // base case
        if (path.size()== words[0].length())
        {
            result.add(new ArrayList<>(path)); return;
        }
        StringBuilder prefix= new StringBuilder();
        int idx= path.size();
        for (String word:path)
        {
            prefix.append(word.charAt(idx));
        }
        for (String word: searchPrefix(prefix.toString()))
        {
            path.add(word);
            dfs(words, path);
            path.remove(path.size()-1);
        }
    }


}

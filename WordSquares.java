import java.util.ArrayList;
import java.util.List;
/*
Time Complexity: O()
Space Complexity: O(N), N is the length of recursive stack
Run on leetcode: Yes
Any difficulties: No

Approach:
1. Building Trie node for every string and this TrieNode also have prefix strings
2. Recursively tracking all the strings with the same pattern
 */
public class WordSquares {
    public static List<String> current;
    public static List<List<String>> result;
    public static List<List<String>> wordSquares(String[] words){

        Trie trie = new Trie();

        for(String word: words){
            trie.insert(word);
        }

        current = new ArrayList<>();
        result = new ArrayList<>();

        wordSquares(trie);

        return result;
    }
    public static void wordSquares(Trie trie){
        // Base condition

        if(current.size()>0 && current.size() == current.get(0).length()){
            result.add(new ArrayList<>(current));
            return;
        }

        // Action Condition making prefix string

        StringBuilder prefix = new StringBuilder();

        for(String word: current){
            prefix.append(word.charAt(current.size()));
        }

        List<String> prefixStrings = trie.getPrefixWords(prefix.toString());
        // Recursion on the trie structure to get all the prefix strings

        for(String word: prefixStrings){
            // Action
            current.add(word);
            // Recursion
            wordSquares(trie);

            // Backtracking
            current.remove(current.size()-1);
        }
    }

    public static void main(String[] args){
        System.out.println("Word Squares: "+ wordSquares(new String[]{"area","lead","wall","lady","ball"}));
    }
}

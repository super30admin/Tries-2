import java.util.ArrayList;
import java.util.List;
/*
Word Squares
approach: we can see that next word we chose should be starting with the prefix at curr+1 position i.e. if our word is
ball, index is 0, then next word should start with the prefix at index 1 "a" and next at index 2 and so on.
time: O(n*avg(len)+(26^avg(len)))
space: O(avg(len)) stack
 */
public class Problem1 {
    class TrieNode {
        TrieNode[] children;
        List<String> words;
        public TrieNode() {
            this.children = new TrieNode[26];
            this.words = new ArrayList<>();
        }
    }
    TrieNode root;
    List<List<String>> res;
    private void insert(String word) {
        TrieNode curr = root;

        for (int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if (curr.children[c-'a']==null) {
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
            curr.words.add(word);
        }
    }

    private List<String> startsWith(String prefix) {
        TrieNode curr = root;

        for (int i=0;i<prefix.length();i++) {
            char c = prefix.charAt(i);
            if (curr.children[c-'a']!=null) {
                curr = curr.children[c-'a'];
            }
            else return new ArrayList<>();
        }
        return curr.words;
    }

    private List<List<String>> wordSqaures(String[] words) {
        res = new ArrayList<>();
        if(words==null || words.length==0) return res;
        root = new TrieNode();
        for (String word:words) {
            insert(word);
        }

        List<String> temp = new ArrayList<>();
        
        for (String word: words) {
            temp.add(word);
            backtrack(temp, word.length());
            temp.remove(temp.size()-1);
        }
        return res;
    }

    private void backtrack(List<String> temp, int length) {
        if (temp.size()==length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        StringBuilder sb = new StringBuilder();
        int c = temp.size();

        for (int i=0;i<temp.size();i++) {
            sb.append(temp.get(i).charAt(c));
        }

        for (String word: startsWith(sb.toString())) {
            temp.add(word);
            backtrack(temp, length);
            temp.remove(c-1);
        }
    }
}

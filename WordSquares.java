// Time Complexity : O(n^l) words ^ length
// Space Complexity : O(n^2*l)
// Did this code successfully run on Leetcode : NA
// Any problem you faced while coding this : NO

// Your code here along with comments explaining your approach
// need to form a square of length = word.length usings words
// if the first word is "ball" -> 2nd word needs to start with a (charAt(1))
// if the 2nd word is "area" -> 3nd word needs to start with le (charAt(2) of baLl and arEa)

// Form a trie to get the words starting with some prefix in O(L)
// Use Backtracking to find all posible combination by starting with each word

public class WordSquares {

    List<List<String>> result;
    TrieNode root;

    class TrieNode {

        TrieNode[] children;
        List<String> startsWith;

        TrieNode() {
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }

    public void insert(String word, TrieNode root) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.startsWith.add(word);
        }
    }

    public List<String> searchPrefix(String prefix, TrieNode root) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }

        return curr.startsWith;
    }

    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        root = new TrieNode();
        for (String s : words)
            insert(s, root);

        List<String> li = new ArrayList<>();
        for (String s : words) {
            li.add(s);
            backtrack(root, li);
            li.remove(li.size() - 1);
        }

        return result;

    }

    private void backtrack(TrieNode root, List<String> li) {
        if (li.size() == li.get(0).length()) {
            result.add(new ArrayList<>(li));
            return;
        }

        StringBuilder prefix = new StringBuilder();
        int i = li.size();
        for (String s : li) {
            prefix.append(s.charAt(i));
        }

        List<String> prefixList = searchPrefix(prefix.toString(), root);
        for (String next : prefixList) {
            li.add(next);
            backtrack(root, li);
            li.remove(li.size() - 1);
        }
    }
}

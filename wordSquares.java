// Time Complexity = Exponential
// Space Complexity = O(N*k)^2, where N=no. of words, k=avg size of each word
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// Solved using Backtracking + Trie
class Solution {
    class TrieNode {
        TrieNode[] children;
        List<String> startsWith;

        TrieNode () {
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }

    TrieNode root;

    // inserts the word into the trie and update the startsWith list for each prefix on the way
    private void insert(String word) {
        TrieNode curr = root;

        for (int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c-'a'] == null) {
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
            curr.startsWith.add(word);
        }
    }

    // returns a list of words that starts with the input prefix
    private List<String> search(String prefix) {
        TrieNode curr = root;
        for (int i=0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.children[c-'a'] == null) {     // the prefix is not present
                return new ArrayList<>();
            }
            curr = curr.children[c-'a'];
        }
        return curr.startsWith;
    }

    List<List<String>> result;

    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        if (words == null || words.length == 0) return result;

        root = new TrieNode();
        result = new ArrayList<>();

        // insert all the words into Trie
        for (String word: words) {
            insert(word);
        }

        // try forming a word square
        List<String> li = new ArrayList<>();
        for (String word: words) {
            //action
            li.add(word);

            //recurse
            backtrack(li, word.length());

            //backtrack
            li.remove(li.size() - 1);
        }

        return result;
    }

    private void backtrack(List<String> li, int k) {
        // base
        if (li.size() == k) {
            result.add(new ArrayList<>(li));
            return;
        }

        // logic
        int i = li.size();
        StringBuilder sb = new StringBuilder();

        for (String s : li) {
            sb.append(s.charAt(i));
        }

        List<String> list = search(sb.toString());
        for (String word: list) {
            //action
            li.add(word);
            //recurse
            backtrack(li, word.length());
            //backtrack
            li.remove(li.size() - 1);
        }
    }
}
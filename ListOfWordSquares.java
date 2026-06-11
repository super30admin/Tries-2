// Time Complexity : O(n^L) where n = number of words that start with prefix and L=length of each word
// Space Complexity : O(N^2 * L^2)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We initially start with a word to form the square, then, instead of checking all combinations of words with
trial and error, we try to check if the prefix of the 1st inserted word can be used using Tries to identify
those words which match with the prefix or start with that prefix. The core ask is when we form the square,
the 1st character should match horizontally,vertically, similarly,2nd character should match with 2nd char
as like in a transpose of the matrix.So, for comparison of prefix, we need to recurse/dfs all the other words
to find the possible combinations and backtrack if doesnt work out.We continue the dfs until we find the square
length equal to the any word's length
 */
class Solution {
    List<List<String>> result;
    TrieNode root;

    class TrieNode {
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.startsWith = new ArrayList<>();
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for(char ch : word.toCharArray()) {
            if(curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
            curr.startsWith.add(word);
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        this.result = new ArrayList<>();
        this.root = new TrieNode();

        for(String word : words) {
            insert(word);
        }
        List<String> path = new ArrayList<>();
        for(String word : words) {
            path.add(word);
            dfs(words, path);
            path.remove(path.size() - 1);
        }
        return result;
    }

    private List<String> searchPrefix(String prefix) {
        TrieNode curr = root;
        for(char ch : prefix.toCharArray()) {
            if(curr.children[ch - 'a'] == null)
                return new ArrayList<>();
            curr = curr.children[ch - 'a'];
        }
        return curr.startsWith;
    }

    private void dfs(String[] words, List<String> path) {
        if(path.size() == words[0].length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        int idx = path.size();
        StringBuilder prefix = new StringBuilder();
        for(String str : path) {
            prefix.append(str.charAt(idx));
        }
        List<String> prefixList = searchPrefix(prefix.toString());
        for(String word : prefixList) {
            path.add(word);
            dfs(words, path);
            path.remove(path.size() - 1);
        }
    }
}
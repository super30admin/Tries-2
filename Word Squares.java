// Time Complexity : 0(n*l)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {

    //aesha
    TrieNode root;
    List<List<String>> result;

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
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.startsWith.add(word);
        }
    }

    private List<String> searchPrefix(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.startsWith;
    }

    public List<List<String>> wordSquares(String[] words) {
         this.root = new TrieNode();
        this.result = new ArrayList<>();

        for (String word : words) {
            insert(word);
        }

        List<String> path = new ArrayList<>();
        for (String word : words) {
            path.add(word);
            dfs(words[0].length(), path); 
            path.remove(path.size() - 1); 
        }

        return result;
    }

    private void dfs(int words, List<String> path )
    {
        //base case
        if (path.size() == words) {
            result.add(new ArrayList<>(path));
            return;
        }

        StringBuilder prefix = new StringBuilder();
        int idx = path.size();
        for (String word : path) {
            prefix.append(word.charAt(idx));
        }

        List<String> startsWithPrefix = searchPrefix(prefix.toString());
        for (String word : startsWithPrefix) {
            path.add(word);
            dfs(words, path); 
            path.remove(path.size() - 1);
    }
}
}
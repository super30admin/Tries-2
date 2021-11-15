//TC: exponential, O(NL 26^L), N is total no. of words given, L is average length of each word, 26 is characters. 
//SC: O(m*n), building a trie
//Running on leetcode: yes
class Solution {
    class TrieNode{
        TrieNode [] children;
        List<String> startsWith;
        public TrieNode() {
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }
    
    TrieNode root;
    //create a trie node
    private void insert(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr.startsWith.add(word);
            curr = curr.children[c - 'a'];
        }
    }
    private List<String> startsWith(String prefix) {
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.startsWith;
    }
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        if(words == null || words.length == 0) return new ArrayList<>();
        root = new TrieNode();
        //build the trie
        for(String word : words) {
            insert(word);
        }
        result = new ArrayList<>();
        List<String> li = new ArrayList<>();
        for(String word : words) {
            li.add(word);
            backtrack(li, word.length());
            li.remove(li.size() - 1);
        }
        return result;
    }
    private void backtrack(List<String> li, int len) {
        //base
        if(li.size() == len) {
            result.add(new ArrayList<>(li));
            return;
        }
        //logic
        int index = li.size();
        StringBuilder sb = new StringBuilder();
        for(String word : li) {
            sb.append(word.charAt(index));
        }
        List<String> list = startsWith(sb.toString());
        for(String word : list) {
            //action
            li.add(word);
            //recurse
            backtrack(li, len);
            //backtrack
            li.remove(li.size() - 1);
        }
    }
}

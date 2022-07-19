/*
Problem: https://leetcode.com/problems/stream-of-characters/
*/
// TC: O(n * k)
// SC: O(n * k)
class TrieNode {
    TrieNode children[];
    boolean isEnd;
    
    public TrieNode() {
        children = new TrieNode[26];
        isEnd = false;
    }
}

class Trie {
    TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void buildTrie(String words[]) {
        for (String word : words) {
            insert(word);
        }
    }
    
    // Insert word in reverse order
    public void insert(String word) {
        TrieNode cur = root;
        
        for (int i = word.length() - 1; i >= 0; --i) {
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.isEnd = true;
    }
}

class StreamChecker {
    Trie t;
    StringBuilder sb;
    
    public StreamChecker(String[] words) {
        sb = new StringBuilder();
        t = new Trie();
        t.buildTrie(words);
    }
    
    public boolean query(char letter) {
        TrieNode cur = t.root;
        sb.append(letter);
        for (int i = sb.length() - 1; i >= 0; --i) {
            char c = sb.charAt(i);
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            
            cur = cur.children[c - 'a'];
            if (cur.isEnd) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
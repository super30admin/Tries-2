/*
Problem: https://leetcode.com/problems/word-squares/
TC: O(exponential)
SC: O(k) for recursive stack space + O(n * k) for trie
where k = avg length of the word, n = number of words
*/
class TrieNode {
    TrieNode children[];
    List<String> words;
    
    public TrieNode() {
        children = new TrieNode[26];
        words = new ArrayList<>();
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
    
    public void insert(String word) {
        TrieNode cur = root;
        
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode();
            }
            cur = cur.children[ch - 'a'];
            cur.words.add(word);
        }
    }
    
    public List<String> getWordsWithPrefix(String prefix) {
        TrieNode cur = root;
        
        for (int i = 0; i < prefix.length(); ++i) {
            char ch = prefix.charAt(i);
            if (cur.children[ch - 'a'] == null) {
                return new ArrayList<>();
            }
            cur = cur.children[ch - 'a'];
        }
        
        return cur.words;
    }
}

class Solution {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        
        Trie t = new Trie();
        t.buildTrie(words);
        
        List<String> wordSquare = new ArrayList<>();
        
        for (String word : words) {
            wordSquare.add(word);
            backtrack(wordSquare, word, result, t);
            wordSquare.remove(word);
        }
        
        return result;
    }
    
    private void backtrack(List<String> wordSquare, String word, List<List<String>> result, Trie t) {
        // base
        if (wordSquare.size() == word.length()) {
            result.add(new ArrayList<>(wordSquare));
            return;
        }
        
        // login
        int index = wordSquare.size();
        StringBuilder prefix = new StringBuilder();
        for (String word1 : wordSquare) {
            prefix.append(word1.charAt(index));
        }
        
        List<String> wordsWithSamePrefix = t.getWordsWithPrefix(prefix.toString());
        for (String wordWithSamePrefix : wordsWithSamePrefix) {
            wordSquare.add(wordWithSamePrefix);
            backtrack(wordSquare, wordWithSamePrefix, result, t);
            wordSquare.remove(wordSquare.size() - 1);
        }
    }
}
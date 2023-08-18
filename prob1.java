// Time Complexity : O(nm^2) 
// Space Complexity : O(nm^2)
// Did this code successfully run on Leetcode : Yes

class Solution {
    public List<List<String>> wordSquares(String[] words) {
       
        TrieNode root = new TrieNode();
        for (String word : words) {
             TrieNode node = root;
            for (char ch : word.toCharArray()) {
                node.children.computeIfAbsent(ch, k -> new TrieNode());
                node =  node.children.get(ch);
                node.setWord(word);
            }
        }
        int n = words[0].length();
        List<List<String>> ans = new ArrayList<>();
        for (String word : words) {
            List<String> wordSquares = new ArrayList<String>();
            wordSquares.add(word);
            match(root,wordSquares, ans, 1);
        }
        return ans;
        
    }
    
    private void  match(TrieNode root,  List<String> wordSquares, List<List<String>> ans, int step) {
        int n = wordSquares.get(0).length();
        if (step == n) {
            ans.add(new ArrayList<>(wordSquares));
            return;
        }
        StringBuilder prefix = new StringBuilder();
        prefix = getPrefix(wordSquares, step);
        for (String matchedWord : getWordsWithPrefix(root, prefix.toString())) {
            wordSquares.add(matchedWord);
            match(root, wordSquares, ans, step + 1);
            wordSquares.remove(wordSquares.size() - 1);
        }
    }
    private  List<String> getWordsWithPrefix(TrieNode root, String prefix) {
        TrieNode node = root;
        for (Character letter : prefix.toCharArray()) {
        if (node.children.containsKey(letter)) {
            node = node.children.get(letter);
        } else {
            // return an empty list.
            return new ArrayList<String>();
        }
        }
        return node.words;
  }

    private StringBuilder getPrefix(List<String> wordSquares, int step) {
        StringBuilder prefix = new StringBuilder();
        for (String word : wordSquares) {
            prefix.append(word.charAt(step));
        }
        return prefix;
    }
    
    class TrieNode {
        public Map<Character, TrieNode> children;
        public List<String> words;
        
        public TrieNode() {
            children = new HashMap<>();
        }
        public void setWord(String word) {
            if (words == null) {
                words = new ArrayList<>();
            }
            words.add(word);
        }
    }
}
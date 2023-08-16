class Solution {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }
        
        int squareSize = words[0].length();
        TrieNode root = buildTrie(words);
        
        List<String> square = new ArrayList<>();
        for (String word : words) {
            square.add(word);
            backtrack(result, square, squareSize, root);
            square.remove(square.size() - 1);
        }
        
        return result;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
                node.words.add(word);
            }
        }
        return root;
    }
    
    private void backtrack(List<List<String>> result, List<String> square, int squareSize, TrieNode root) {
        if (square.size() == squareSize) {
            result.add(new ArrayList<>(square));
            return;
        }
        
        int index = square.size();
        StringBuilder prefix = new StringBuilder();
        for (String word : square) {
            prefix.append(word.charAt(index));
        }
        
        List<String> candidates = getCandidates(prefix.toString(), root);
        for (String candidate : candidates) {
            square.add(candidate);
            backtrack(result, square, squareSize, root);
            square.remove(square.size() - 1);
        }
    }
    
    private List<String> getCandidates(String prefix, TrieNode root) {
        List<String> candidates = new ArrayList<>();
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return candidates;
            }
            node = node.children[index];
        }
        candidates.addAll(node.words);
        return candidates;
    }
    
    class TrieNode {
        TrieNode[] children;
        List<String> words;
        
        TrieNode() {
            children = new TrieNode[26];
            words = new ArrayList<>();
        }
    }
}


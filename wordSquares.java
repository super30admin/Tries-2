// Time Complexity : O(n*l*26^l) where n is the number of words in the square and l is the length of a word
// Space Complexity : O(n*l) where n is the number of words in the square and l is the length of a word
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class wordSquares {
    
    class Node {
        Node[] children;
        List<String> startsWith;
        public Node() {
            children = new Node[26];
            startsWith = new ArrayList<>();
        }
    }

    public Node buildTrie(String[] words) {
        Node root = new Node();
        for (String word : words) {
            Node node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c-'a'] == null) {
                    node.children[c-'a'] = new Node();    
                }
                node.children[c-'a'].startsWith.add(word);
                node = node.children[c-'a'];
            }
        }
        return root;
    }

    public List<String> searchWords(String prefix, Node root) {
        Node node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c-'a'] == null) return new ArrayList<>();
            node = node.children[c-'a'];    
        }
        return node.startsWith;
    }

    List<List<String>> ans;
    public List<List<String>> wordSquares(String[] words) {
        ans = new ArrayList<>();
        Node root = buildTrie(words);
        List<String> list = new ArrayList<>();
        int len = words[0].length();
        for (String word : words) {
            list.add(word);
            backtrack(list, len, root);
            list.remove(list.size() - 1);
        }
        return ans;
    }

    private void backtrack(List<String> list, int len, Node root) {
        // base 
        if (list.size() == len) {
            ans.add(new ArrayList<>(list));
            return;
        }
        // logic
        StringBuilder sb = new StringBuilder();
        int i = list.size();
        for (String word : list) {
            sb.append(word.charAt(i));
        }
        List<String> prefixes = searchWords(sb.toString(), root);
        for (String word : prefixes) {
            list.add(word);
            backtrack(list, len, root);
            list.remove(list.size() - 1);
        }
    }
}
// "static void main" must be defined in a public class.
public class Main {

    // Time Complexity: O(n*26^N)
    // Space Complexity: O(26^N)

    static class TrieNode {
        TrieNode[] children;
        List<String> startsWith;
        
        public TrieNode() {
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }
    
    private static TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for(String word : words){
            TrieNode curr = root;
            for(char c : word.toCharArray()){
                if(curr.children[c - 'a'] == null)
                    curr.children[c - 'a'] = new TrieNode();
                curr.startsWith.add(word);
                curr = curr.children[c - 'a'];
            }
        }
        return root;
    }
    
    private static List<String> getsPrefix(TrieNode root, String prefix){
        TrieNode curr = root;
        for(char c : prefix.toCharArray()){
            if(curr.children[c - 'a'] == null)
                return new ArrayList<>();
            
            curr = curr.children[c - 'a'];
        }
        return curr.startsWith;
    }
    
    public static List<List<String>> wordSquares(String[] words){
        List<List<String>> result = new ArrayList<>();
        if(words == null || words.length == 0)
            return result;
        
        List<String> path = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        for(String word : words){
            path.add(word);
            backtracking(result, path, word.length(), root);
            path.remove(path.size()-1);
        }
        
        return result;
    }
    
    
    private static void backtracking(List<List<String>> result, List<String> path, int length, TrieNode root){
        if(path.size() == length){
            result.add(new ArrayList<>(path));
            return;
        }
        
        int i = path.size();
        StringBuilder prefix = new StringBuilder();
        for(String word : path){
            prefix.append(word.charAt(i));
        }
        
        List<String> startsWithPrefix = getsPrefix(root, new String(prefix));
        for(String word : startsWithPrefix){
            path.add(word);
            backtracking(result, path, length, root);
            path.remove(path.size() - 1);
        }
    }
    
    public static void main(String[] args) {        
        String[] words = new String[]{"area","lead","wall","lady","ball"};
        List<List<String>> result = wordSquares(words);
        
        System.out.println(result);
    }
}
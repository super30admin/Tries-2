// Time: O(n^2)
// Space: O(n)

class Solution {
    
    class TrieNode{
        TrieNode [] children;
        List<String> li;
        
        public TrieNode(){
            this.children = new TrieNode[26];
            this.li = new ArrayList<>();
        }
        
    }
    
    private void insert(String word){
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c-'a'] == null){
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
            curr.li.add(word);
        }
    }
    
    private List<String> search(String prefix){
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if (curr.children[c-'a'] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c-'a'];
        }
        return curr.li;
    }
    
    TrieNode root;
    List<List<String>> result = new ArrayList<>();
    public List<List<String>> wordSquares(String[] words) {
        int len = words[0].length();
        
        root = new TrieNode();
        for (String word: words){
            insert(word);
        }
        
        List<String> li = new ArrayList<>();
        for(int i = 0; i < words.length; i++){
            li.add(words[i]);
            backtrack(li, len);
            li.remove(li.size()-1);
        }
        
        return result;
    }
    
    private void backtrack(List<String> square, int len){
        // base
        if (square.size() == len){
            result.add(new ArrayList<>(square));
            return;
        }
        // logic
        StringBuilder prefix = new StringBuilder();
        int idx = square.size();
        for (String str: square){
            prefix.append(str.charAt(idx));
        }
        List<String> li = search(prefix.toString());
        for(String st: li){
            square.add(st);
            backtrack(square, len);
            square.remove(square.size()-1);
        }
    }
    
}

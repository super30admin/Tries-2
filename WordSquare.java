class Solution {
    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        List<String> startsWith;
        
        public TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
            startsWith = new ArrayList<>();
        }
    }
    
    class Trie{
        TrieNode root;
        
        public Trie(String[] words){
            root = new TrieNode();
            
            for(String word : words){
                addWord(word);
            }
        }
        
        public void addWord(String word){
            TrieNode cur = root;
            
            for(char ch : word.toCharArray()){
                if(cur.children[ch-'a']==null){
                    cur.children[ch-'a'] = new TrieNode();
                }
                
                cur.startsWith.add(word);
                cur = cur.children[ch-'a'];
            }
            
            cur.isEnd = true;
        }
        
        public List<String> search(String prefix){
            TrieNode cur = root;
            
            for(char ch : prefix.toCharArray()){
                if(cur.children[ch-'a']==null){
                    return new ArrayList<>();
                }
                else{
                    cur = cur.children[ch-'a'];
                }
            }
            
            return cur.startsWith;
        }
    }
    
    List<List<String>> output;
    
    public List<List<String>> wordSquares(String[] words) {
        output = new ArrayList<>();
        Trie trie = new Trie(words);
        
        List<String> curSquare = new ArrayList<>();
        
        for(String word : words){
            curSquare.add(word);
            helper(curSquare, trie, word.length());
            curSquare.remove(curSquare.size()-1);
        }
        
        return output;
    }
    
    private void helper(List<String> curSquare, Trie trie, int l){
        if(curSquare.size()==l){
            output.add(new ArrayList<>(curSquare));
            return;
        }
        
        StringBuilder prefix = new StringBuilder();
        int i = curSquare.size();
        
        for(String word : curSquare){
            prefix.append(word.charAt(i));    
        }
        
        List<String> matchingResults = trie.search(prefix.toString());
        
        for(String matchResult : matchingResults){
            curSquare.add(matchResult);
            helper(curSquare, trie, l);
            curSquare.remove(curSquare.size()-1);
        }
    }
}
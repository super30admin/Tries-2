//Tc: O(n * L) 
//Sc : O(n* L)
class Solution {
    class TrieNode{
        TrieNode[] children;
        List<String> startwithPrefix;
        public TrieNode(){
            children = new TrieNode[26];
            startwithPrefix = new ArrayList<>();
        }
    }
    TrieNode root;
        public void insert(String word){
            TrieNode curr = root;
            for(int i = 0; i< word.length(); i++){
                char ch = word.charAt(i);
                if(curr.children[ch - 'a'] == null){
                    curr.children[ch - 'a'] = new TrieNode();
                }
                curr = curr.children[ch - 'a'];
                curr.startwithPrefix.add(word);
            }
        }
        public List<String> search(String word){ 
            TrieNode curr = root;
            for(int i = 0; i< word.length(); i++){
                char ch = word.charAt(i);                
                if(curr.children[ch - 'a'] == null){
                    return new ArrayList<>();
                }                
                curr = curr.children[ch - 'a'];                
            }
            
            return curr.startwithPrefix;
        }
    
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        if(words == null || words.length == 0){
            return new ArrayList<>();
        }
        
        root = new TrieNode();
        for(String s : words){
            insert(s); // Inserting all the words in Trie first
        }
        
        List<String> list = new ArrayList<>();
        for(String word : words){
            list.add(word);
            
            helper(list, word.length());//Recurse after adding each word in list
            
            list.remove(list.size() - 1);
        }
        return result;
    }

    public void helper(List<String> list, int k){ 
        if(list.size() == k){
            result.add(new ArrayList<>(list));
            return;
        }   
        StringBuilder prefix = new StringBuilder();
        
        int ix = list.size();
        
        for(String s : list){
            prefix.append( s.charAt(ix) );
        }
        List<String> startsWith = search(prefix.toString() );
        
        for(String s : startsWith){
             list.add(s);
            
            helper(list, s.length());
            
            list.remove(list.size() - 1);
        }
        
    }
}

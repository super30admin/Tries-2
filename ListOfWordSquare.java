//Tc: O(n * L * 26^L)  -- L is length of word and n IS TOTAL words
//Sc : O(n* L) - L is length of word
class Solution {
    class TrieNode{
        TrieNode[] children;
        List<String> withPrefix;
        public TrieNode(){
            children = new TrieNode[26];
            withPrefix = new ArrayList<>();
        }
    }
    TrieNode root;
        public void insert(String word){ // INSERTING WORD in trie/dictionary
            TrieNode curr = root;
            for(int i = 0; i< word.length(); i++){
                char ch = word.charAt(i);
                if(curr.children[ch - 'a'] == null){
                    curr.children[ch - 'a'] = new TrieNode();
                }
                curr = curr.children[ch - 'a'];
                curr.withPrefix.add(word);
            }
        }
        public List<String> search(String word){ // Searching for prefix in Trie and getting its list of words 
            TrieNode curr = root;
            for(int i = 0; i< word.length(); i++){
                char ch = word.charAt(i);                
                if(curr.children[ch - 'a'] == null){
                    return new ArrayList<>();
                }                
                curr = curr.children[ch - 'a'];                
            }
            
            return curr.withPrefix;
        }
    
    List<List<String>> res;
    public List<List<String>> wordSquares(String[] words) {
        res = new ArrayList<>();
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
        return res;
    }

    public void helper(List<String> list, int k){ // k =word.length everytime
        if(list.size() == k){
            res.add(new ArrayList<>(list));
            return;
        }   
        
        //We are adding words in list based on prefix which we will be find 
        //by characters at index (temp.size()) of all the words in current list
        
        //Then we will be searching and doing recursion-backtrack for words with that prefix
        
        StringBuilder prefix = new StringBuilder();
        
        int ix = list.size();
        
        for(String s : list){
            prefix.append( s.charAt(ix) );
        }
        
        //Retrieving wordsList with above prefix from Trie
        
        List<String> startsWith = search(prefix.toString() );
        
        for(String s : startsWith){
             list.add(s);
            
            helper(list, s.length());//Recurse after adding word in list
            
            list.remove(list.size() - 1);
        }
        
    }
}
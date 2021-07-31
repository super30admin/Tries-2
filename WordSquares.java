class WordSquares {
    
    //time:O(m*(26^L)*L) where m = avg num of words, L= avg length of each word
    //space:O(m*L +  m*(L/2) ) where m*L=>for trie,   m*(L/2)=space taken by the prefixes of all words. In the worst case, we have no overlapping among the prefixes.
    //Output list of list to store words in squares
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        
        //initializing result array
        result = new ArrayList<>();
        
        //if input is empty
        if(words == null || words.length==0) return result;
        
        //initializing root for trieNode
        root = new TrieNode();
        
        //building trie
        buildTrie(words);//time:O(m*n)
        
        //list to store current words in a square
        List<String> li  = new ArrayList<>();
        
        //looping through all the words
        for(int i=0; i<words.length; i++){
            //action
            //add current word to list
            li.add(words[i]);
            
            //recurse
            //calling backtrack method on current list
            backtrack(li);
            
            //backtrack
            //removing current word 
            li.remove(li.size()-1);
        }
        
        return result;
    }
    
    public void backtrack(List<String> li){
        
        //base
        //if num of words in same as length of each word which implies we have a square
        if(li.size()==li.get(0).length()){
            //add list to result
            result.add(new ArrayList<>(li));
            return;
        }
        
        //index to find prefix for next words
        int idx = li.size();
        
        StringBuilder prefix = new StringBuilder();
        for(String word:li){//O(L)
            prefix.append(word.charAt(idx));
        }
        
        //fetching words with  prefix
        List<String> words = startsWith(prefix.toString());
        
        //Lopping through all the words with prefix
         for(int i=0; i<words.size(); i++){
            //action
            li.add(words.get(i));
            //recursion
            backtrack(li);
             //backtrack
            li.remove(li.size()-1);
        }
    }
    
    //class for TrieNode
    class TrieNode{
        TrieNode[] children;
        List<String> startsWith;
        
        TrieNode(){
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }
    
    //Global root for the TrieNode
    TrieNode root;
    
    //creating Trie from words
    //time:O(m*n) where m = avg num of words, n= avg length of each word
    public void buildTrie(String[] words){
        
        for(String word: words){
            TrieNode curr = root;
            for(int i=0; i<word.length(); i++){
                Character c = word.charAt(i);
                
                if(curr.children[c-'a']==null){
                    curr.children[c-'a'] = new TrieNode();
                }
                curr.startsWith.add(word);
                curr = curr.children[c-'a'];  
            }   
        }
    }
    
    //searching for a string in Trie
    //time: O(m), where m = avg num of words, m= avg length of word
    public List<String> startsWith(String word){
        
        TrieNode curr = root;
        for(int i=0; i<word.length(); i++){
            Character c = word.charAt(i);

            if(curr.children[c-'a']==null){
                return new ArrayList<>();
            }
            curr = curr.children[c-'a'];  
        }
        return curr.startsWith;
    }
}
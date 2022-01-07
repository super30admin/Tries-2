class Solution{
    class TrieNode{
        TrieNode [] children;
        List<String> startsWith;
        public TrieNode(){
          children = new TrieNode[26];
          startsWith = new ArrayList<>();
        }
    }
  
    TrieNode root;
    private TrieNode buildTrie(String[] words){
        for(String word: words){
            TrieNode curr = root;
            for(int i = 0; i < word.length();i++){
                char c = word.charAt(i);
                if(curr.children[c - 'a'] == null){
                    curr.chilren[c - 'a'] = new TrieNode();
                }
              curr.startsWith.add(word);
              curr = curr.children[c - 'a'];
            }
        }
    }
   private List<String> startWith(String prefix){
          TrieNode curr = root;
          for(int i =0;i < prefix.length();i++){
              char c = prefix.charAt(i);
              if(curr.children[c - 'a'] == null){
                  return new ArrayList<>();
              }
            curr = curr.children[c - 'a']}
     }
      return curr.startsWith;
}
  List<List<String>> result;
  public List<List<String>> wordSquares(String[] words){
      result = new ArrayList<>();
      if(words == null || words.length == 0) return result;
      
      root = new TrieNode();
      buildTrie(words);
      List<String> li = new ArrayList<>();
      for(int i = 0; i < words.length(); i++){
          
          //action
          li.add(words[i]);
          //recurse
          backtrack(li);
          //backtrack
          li.remove(li.size() - 1;
}
     return result;
}
                  
//TC:O(W*L) W ->no of words, L -> length of each word

  
    
                                   
  

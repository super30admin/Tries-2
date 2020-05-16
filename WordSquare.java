// Time Complexity : O(N^2)
// Space Complexity : O(logN)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    public List<List<String>> wordSquares(String[] words) {
        int limit = words[0].length();
        TrieNode root = new TrieNode();
        List<List<String>> result = new LinkedList<>();
       
        constructTrie(root,words);
        
       for(String word: words){
           List<String> square = new LinkedList<>();
           square.add(word);
           backtrack(root,limit,words,result,square,1);
       }
      return result;  
    }
    
    private void constructTrie(TrieNode root, String words[]){
        for(int i=0;i<words.length;i++){
            insert(root, words[i],i);
        }
        
    }
    private void insert(TrieNode root, String word, int index){
     TrieNode cursor = root;
     for(char ch: word.toCharArray()){
         TrieNode child = cursor.children.get(ch);
         if(child == null){
             child = new TrieNode();
             cursor.children.put(ch,child);
         }
         cursor = child;
         cursor.wordIndex.add(index);
     }
        
    }
    private void backtrack(TrieNode root, int limit, String[] words,List<List<String>> result,List<String> state, int index){
        
        if(index == limit){
            result.add(new LinkedList<>(state));
            return;
        }
        StringBuilder prefix = new StringBuilder();
        for(String word: state){
            prefix.append(word.charAt(index));
        }
        for(Integer wordIndex: getWordIndexListWithPrefix(root,prefix.toString())){
            String word = words[wordIndex];
            state.add(word);
            backtrack(root,limit,words,result,state,index+1);
            state.remove(state.size()-1);
        }
        
    }
    private List<Integer> getWordIndexListWithPrefix(TrieNode root,String prefix){
        TrieNode cursor = root;
        for(char ch : prefix.toCharArray()){
            TrieNode child = cursor.children.get(ch);
            if(child == null){
                return new ArrayList<>();
            }
            cursor=child;
        }
        return cursor.wordIndex;
    }
}


class TrieNode{
    boolean endOfWord;
    Map<Character,TrieNode> children;
    List<Integer> wordIndex;
    TrieNode(){
        children = new HashMap<>();
        wordIndex = new LinkedList<>();
    }
}
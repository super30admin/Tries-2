//time complexity-O(n*26^N)
//Space complexity-O(26^N)
//Ran on leetcode-Yes
//Solution with comments-
class Solution {
    class TrieNode{//TrieNode class
        TrieNode[] children;
        List<String> startwith;//will have a list of the final word at each index of it's each character
        TrieNode(){
            children = new TrieNode[26];
            startwith = new ArrayList<>();
        }
    }
    private TrieNode build(String[] words){//building the trie for all the words in input
        TrieNode root= new TrieNode();
        for(String word: words){
            TrieNode curr= root;
            for(int i=0;i<word.length();i++){
                if(curr.children[word.charAt(i)-'a']==null){
                    curr.children[word.charAt(i)-'a']=new TrieNode();
                }
                curr=curr.children[word.charAt(i)-'a'];
                curr.startwith.add(word);
            }
            
        }
        return root;
    }
    
    public List<String> getPrefix(TrieNode root, String prefix){//get the list of all the words with that prefix
        TrieNode curr= root;
        for(char ch : prefix.toCharArray()){
            if(curr.children[ch-'a']==null){
                return new ArrayList<>();
            }
            curr=curr.children[ch-'a'];
        }
        return curr.startwith;
    }
    
    TrieNode root;
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> output= new ArrayList<>();
        
        if(words==null || words.length==0){
            return output;    
        }
        root= build(words);
     
        List<String> path = new ArrayList<>();
        
        for(String word: words){
            path.add(word);
            backtrack(output, path, word.length());
            path.remove(path.size()-1);
        }
        return output;
    }
    
    public void backtrack(List<List<String>> output, List<String> path, int length){
        if(path.size()==length){
            output.add(new ArrayList<>(path));
            return ;
        }
        int i= path.size();
        StringBuilder prefix= new StringBuilder();
      
        for(String word: path){
            prefix.append(word.charAt(i));
           
        }
          List<String> prefixwords=getPrefix(root,prefix.toString());
        
        for(String word : prefixwords){
            path.add(word);
            backtrack(output, path, word.length());
            path.remove(path.size()-1);
        }
    }
}
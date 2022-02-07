import java.util.*;
class Solution {
    //exponential time and o(m * n) space
    class TrieNode{
        TrieNode[] children;
        List<String> startsWith;
        
        public TrieNode(){
            children = new TrieNode[26];
            startsWith  = new ArrayList<>();
        }
        
    }
    TrieNode root;
        public void insert(String word){
            TrieNode curr = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(curr.children[c - 'a'] == null){
                    curr.children[c-'a'] = new TrieNode();
                }
                curr = curr.children[c-'a'];
                curr.startsWith.add(word);
            }
        }
        public List<String> search(String word){
            TrieNode curr = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(curr.children[c - 'a'] == null){
                    return new ArrayList<>();
                }
                curr = curr.children[c - 'a'];
            }
            return curr.startsWith;
        }
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();    
        root = new TrieNode();
        for(String word: words){
            insert(word);
        }
        List<String> li = new ArrayList<>();
        for(String word: words){
            li.add(word);
            //recursion
            backtrack(li, word.length());
            //backtrack
            li.remove(li.size() -1);
        }
        return result;
    }
    private void backtrack(List<String> li, int k){
        //base
        
        if(li.size() == k){
            result.add(new ArrayList<>(li));
            return;
        }
        //logic
        int i = li.size();
        StringBuilder sb = new StringBuilder();
        for(String s: li){
            sb.append(s.charAt(i));
        }
        List<String> startWith = search(sb.toString());
        for(String word: startWith){
            li.add(word);
            //recursion
            backtrack(li, word.length());
            //backtrack
            li.remove(li.size() -1);
        }
    }

    
        public List<Boolean> camelMatch(String[] queries, String pattern) {
            //o(n) time const space
            List<Boolean> result = new ArrayList<>();
            for(String query: queries){
                int i = 0; 
                boolean flag = false;
                for(int j = 0; j < query.length(); j++){
                    char qChar = query.charAt(j);
                    if(i < pattern.length() && qChar == pattern.charAt(i)){
                        i++;
                        if(i == pattern.length()){
                            flag = true;
                        }
                    } else if(Character.isUpperCase(qChar)){
                        flag = false;
                        break;
                    }
                }
                result.add(flag);
            }
            return result;
        }
    
}


class TrieNode{
    TrieNode[] children;
    List<String> startsWith;
    public TrieNode(){
        children = new TrieNode[26];
        startsWith = new ArrayList<>();
    }
}

class Solution {
        TrieNode root;
        List<List<String>> retList;
    public List<List<String>> wordSquares(String[] words) {
        retList = new ArrayList<>();
        if(words == null || words.length ==0){
            return retList;
        }
        
        buildTrie(words);
        
        
        List<String> list =  new ArrayList<>();
        int length = words[0].length();
        for(String word : words){
            list.add(word);
            backtracking(length, list);
            list.remove(list.size() -1);
            
        }
        return retList;
    }
    private void backtracking(int length, List<String> list){
       // base case
        if(list.size() >= length){
            retList.add(new ArrayList<>(list));
            return;
        }
        //recursive case
        int searchIndex = list.size();
        String possibleLet = "";
        for(String word: list){  //[ 'ball', 'area'] possibleLet = le
            possibleLet += word.charAt(searchIndex);
        }
        
        // call trie and get list<String>
        
        List<String> possibleWord = possibleWords(possibleLet);
        
        for(String word: possibleWord){
            list.add(word);
            backtracking(length,list);
            list.remove(list.size()-1);
        }
    

        
    }
    private List<String> possibleWords(String possibleLet){
        TrieNode node = root;
        List<String> result = new ArrayList<>();
        
        for(int i=0;i<possibleLet.length(); i++){
            char ch = possibleLet.charAt(i);
            if(node.children[ch - 'a'] == null) return result;
        node= node.children[ch-'a'];
    }
    
        result.addAll(node.startsWith);
        return result;
    
    }
    
    
    private void buildTrie(String[] words){
        root = new TrieNode();
        for(String word: words){
            TrieNode node = root;
            for(int i=0;i< word.length(); i++){
                char ch = word.charAt(i);
                
                if(node.children[ch - 'a'] == null){
                    node.children[ch - 'a'] = new TrieNode();
                }
                node.children[ch-'a'].startsWith.add(word);
                node = node.children[ch - 'a'];
            }
        }
    }
}

class Solution {
    //m is avg length & n is number of words
    // Time Complexity O(n^m * m + m * n)
    //Space Complexity: O(mn) 
    //Solved on LeeetCode : Yes
    class TrieNode{
        TrieNode[] children;
        List<String> words;
        public TrieNode(){
            children = new TrieNode[26];
            words = new ArrayList<>();
        }
    }

    private TrieNode root;
    private List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        if(words == null || words.length == 0) return new ArrayList<>();
        result = new ArrayList<>();
        root = new TrieNode();
        for(String word : words){
            insertWord(word);
        }
        List<String> currWordList = new ArrayList<>();
        int n = words[0].length();
        for(String word: words){
            //action
            currWordList.add(word);
            //recurse
            backtrack(currWordList,n);
            //backtracking
            currWordList.remove(currWordList.size()-1);
        }
        return result;
    }

    private void backtrack(List<String> currWordList, int n){
        //base case
        if(currWordList.size() == n) {
            result.add(new ArrayList<>(currWordList));
            return;
        }
        //logic
        StringBuilder sb = new StringBuilder();
        int size = currWordList.size();
        for(int i = 0; i < size; i++){
            sb.append(currWordList.get(i).charAt(size));
        }
        List<String> possibleWords = searchWords(sb.toString());
        for(String word: possibleWords){
            //action
            currWordList.add(word);
            //recurse
            backtrack(currWordList, n);
            //backtrack
            currWordList.remove(currWordList.size() -1);
        }
    }
    
    private List<String> searchWords(String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c -'a'];
        }
        return curr.words;
    }

    private void insertWord(String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c -'a'];
            curr.words.add(word);
        }
    }
}
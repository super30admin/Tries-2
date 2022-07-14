class Solution {
    class TrieNode{
        TrieNode [] children;
        List<String> words;

        public TrieNode(){
            children = new TrieNode[26];
            words = new ArrayList<>();
        }
    }

    TrieNode root;

    public void insert(String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.words.add(word);
        }
    }

    public List<String> startsWith(String prefix){
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c - 'a'] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.words;
    }

    List<List<String>> result;

    public List<List<String>> wordSquares(String[] words) {
        root = new TrieNode();
        result = new ArrayList<>();

        if(words == null || words.length == 0){
            return new ArrayList<>();
        }

        for(String word : words){
            insert(word);
        }

        List<String> list = new ArrayList<>();

        for(String word: words){
            //action
            list.add(word);
            //recurse
            backtracking(list, word);
            //backtrack
            list.remove(list.size() - 1);
        }
        return result;
    }

    public void backtracking(List<String> list, String word){
        //base
        if(list.size() == word.length()){
            result.add(new ArrayList<>(list));
            return;
        }

        //logic
        int index = list.size();
        StringBuilder sb = new StringBuilder();

        for(String word1: list){
            sb.append(word1.charAt(index));
        }
        List<String> words = startsWith(sb.toString());

        for(String word1: words){
            //action
            list.add(word1);
            //recurse
            backtracking(list, word);
            //backtrack
            list.remove(list.size() - 1);
        }
    }
}
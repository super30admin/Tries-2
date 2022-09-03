// Time: O(N*K^L) | Space: O(NL) -//Tries

class Solution {
    class Trie {
        Trie[] children;
        List<String> words;
        public Trie(){
            children = new Trie[26];
            words = new ArrayList<>();
        }
    }
    Trie root = new Trie();
    private void insertWord(String word) {
        Trie curr = root;
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if(curr.children[c-'a'] == null) {
                curr.children[c-'a'] = new Trie();
            }
            curr = curr.children[c-'a'];
            curr.words.add(word);

        }
    }
    private List<String> search(String searchKeyword) {
        Trie curr = root;
        for(int i=0;i<searchKeyword.length();i++) {
            char c = searchKeyword.charAt(i);
            if(curr.children[c-'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c-'a'];
        }
        return curr.words;
    }
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        for(String word: words) {
            insertWord(word);
        }
        List<String> li = new ArrayList<>();
        int len = words[0].length();
        for(int i=0;i<words.length;i++) {
            li.add(words[i]);
            backtrack(li, len);
            li.remove(li.size()-1);
        }
        return result;
    }
    private void backtrack(List<String> square, int length) {
        int idx = square.size();

        //base
        if(idx == length) {
            result.add(new ArrayList<>(square));
            return;
        }
        //log
        StringBuilder prefix = new StringBuilder();
        for(String word: square) {
            prefix.append(word.charAt(idx));
        }
        List<String> li = search(prefix.toString());
        for(String each: li) {
            square.add(each);
            backtrack(square, length);
            square.remove(square.size()-1);
        }
    }
}
//TC : O(26^k k)
//SC : O(k)
class Solution {
    class TrieNode {
        TrieNode[] children;
        List<String> words;

        TrieNode() {
            children = new TrieNode[26];
            words = new ArrayList<>();
        }
    }


    TrieNode root;

    private void insert(String word) {
        TrieNode current = root;

        for(char ch: word.toCharArray()) {
            if(current.children[ch - 'a'] == null) {
                current.children[ch - 'a'] = new TrieNode();
            }
            current.words.add(word);
            current = current.children[ch - 'a'];
        }
    }

    private List<String> searchInTrie(String prefix) {
        TrieNode current = root;
        for(char ch: prefix.toCharArray()) {
            if(current.children[ch - 'a'] == null) {
                return new ArrayList<>();
            }
            current = current.children[ch - 'a'];
        }
        return current.words;
    }
    List<List<String>>  result;
    public List<List<String>> wordSquares(String[] words) {
        if(words == null || words.length == 0) {
            return new ArrayList<>();
        }

        root = new TrieNode();
        result = new ArrayList<>();

        for(String word: words) {
            insert(word);
        }
        List<String> l = new ArrayList();
        for(String word : words){
            l.add(word);
            backtracking(l);
            l.remove(l.size()-1);
        }
        return result;
    }

    private void backtracking(List<String> list){
        //base
        if(list.size() == list.get(0).length())
        {
            result.add(new ArrayList(list));
            return;
        }
        //logic
        StringBuilder sb = new StringBuilder();
        for(String s : list){
            sb.append(s.charAt(list.size()));
        }

        List<String> matchingWords = searchInTrie(sb.toString());

        for(String s : matchingWords){
            list.add(s);
            backtracking(list);
            list.remove(list.size()-1);
        }
    }
}
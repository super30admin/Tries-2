//let N is the no. of words and l is the length of each word
//time complexity O(N. 26^l)
//space complexity O(Nl)

class Solution {
    class TrieNode {
        TrieNode [] children;
        List<String> startswith;
        public TrieNode(){
            children = new TrieNode[26];
            startswith = new ArrayList<>();
        }
    }
    private TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for(String word : words){
            TrieNode curr = root;
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(curr.children[c - 'a'] == null){
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr.startswith.add(word);
                curr = curr.children[c - 'a'];
            }
        }
        return root;
    }
    public List<String> search(TrieNode root, String prefix){
            TrieNode curr = root;
            for(int i = 0; i < prefix.length(); i++){
                char c = prefix.charAt(i);
                if(curr.children[c - 'a'] == null) return new ArrayList<>();
                curr = curr.children[c - 'a'];
            }
        return curr.startswith;

    }
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        if(words == null || words.length == 0) return result;
        TrieNode root = buildTrie (words);
        List<String> li = new ArrayList<>();
        for(String word: words){
            li.add(word);//action
            backtrack(root, li, word.length());//recurse
            li.remove(li.size() - 1);//backtrack
        }
        return result;
    }
    private void backtrack(TrieNode root, List<String> li, int l){
        //base
        if(li.size() == l){
            result.add(new ArrayList<>(li));
            return;
        }
        //logic
        StringBuilder prefix = new StringBuilder();
        int i = li.size();
        for(String s: li){
            prefix.append(s.charAt(i));
        }
        List<String> prefixWords = search(root, prefix.toString());
        for(String pre: prefixWords){
            li.add(pre);//action
            backtrack(root, li, l);//recurse
            li.remove(li.size() - 1);//backtrack
        }
    }
}

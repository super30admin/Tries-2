//Backtracking + Trie
//TC: O(n!) -> nPk -> k : length of word, n : no . of words, P : permutation
//SC: O(nk) -> k : length of word, n : no . of words,
//Did it run successfully on Leetcode? : Yes
class Solution {
    class TrieNode{
        TrieNode[] children;
        List<String> startsWith;
        TrieNode(){
            children = new TrieNode[26];
            startsWith = new ArrayList();
        }
    }
    TrieNode root = new TrieNode();
    List<List<String>> result;
    private void insertIntoTrie(String[] words){
        for ( String word : words){
            TrieNode curr = root;
          for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.startsWith.add(word);
        }
      }
    }
    private List<String> wordsWithCertainPrefix(String prefix){
          TrieNode curr = root;
          for (int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if (curr.children[c - 'a'] == null){
               return new ArrayList();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.startsWith;
      }
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList();
        if (words == null || words.length == 0)
            return result;
        insertIntoTrie(words);
        List<String> list = new ArrayList();
        for (String word : words){
            //action
            list.add(word);
            //recurse
            backtrack(list, word.length());
            //backtrack
            list.remove(list.size()-1);
        }
        return result;
    }
    private void backtrack(List<String> list, int length){
        //base
        if ( list.size() == length){
            result.add(new ArrayList(list));
            return;
        }
        
        //logic
        StringBuilder sb = new StringBuilder();
        int i = list.size();
        for (String word : list){
            sb.append(word.charAt(i));
        }
        List<String> stringsThatstartsWithPrefix = wordsWithCertainPrefix(sb.toString());
        for (String word : stringsThatstartsWithPrefix ){
            list.add(word);
            //recurse
            backtrack(list, word.length());
            //backtrack
            list.remove(list.size()-1);
        }
      }
        
    }

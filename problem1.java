//time complexity: O(N*L*26^L) L=lenght of word[i], N = no.of word in words.
//space complexity: O(N*L)
//ran on leetcode: Yes
//form a trie structure. calculate the prefixes
class Solution {

    class TrieNode{

        TrieNode [] children;

        List<String> startWith;

        public TrieNode(){

            children = new TrieNode[26];

            startWith = new ArrayList<>();

        }

    }

    private TrieNode buildTrie(String[] words){

        TrieNode root = new TrieNode();

        for(String word: words){

            TrieNode curr = root;

            for(int i = 0; i < word.length(); i++){

                char c = word.charAt(i);

                if(curr.children[c - 'a'] == null){

                    curr.children[c - 'a'] = new TrieNode();

                }

                curr.startWith.add(word);

                curr = curr.children[c - 'a'];

            }

        }



        return root;

    }

    private List<String> allStartWith(TrieNode root, String prefix){

        TrieNode curr = root;

        for(int i = 0; i < prefix.length(); i++){

            char c = prefix.charAt(i);

             if(curr.children[c - 'a'] == null){

                 return new ArrayList<>();

             }

             curr = curr.children[c - 'a'];

        }

        return curr.startWith;

    } 

    List<List<String>> result;

    public List<List<String>> wordSquares(String[] words) {

        result = new ArrayList<>();

        TrieNode root = buildTrie(words);

        List<String> li = new ArrayList<>();//["ball"]

        for(String word : words){

            //action

            li.add(word);

            // recurse

            backtrack(root, li, word.length());

            //bactrack

            li.remove(li.size() - 1);

        }

        return result;

    }

    private void backtrack(TrieNode root, List<String> li, int l){

        // base

        if(li.size() == l){

            result.add(new ArrayList<>(li));

            return;

        }

        // logic

        int i = li.size();

        StringBuilder prefix = new StringBuilder();

        for(String liWord: li){ //

            prefix.append(liWord.charAt(i));

        }

        List<String> allStartWith = allStartWith(root, prefix.toString());

        for(String word: words ){

            //action

            li.add(word);

            // recurse

            backtrack(root, li, l);

            //bactrack

            li.remove(li.size() - 1);

        }

    }

}

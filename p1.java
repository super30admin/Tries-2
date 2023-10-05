// Time Complexity : O(l * n P l) n is the number of words, l is the average length
// Space Complexity :O(n*l*l)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    //Using a trie to store all the words and list of words that start with that prefix
    class Trie{
        Trie[] children;
        List<String> words;
        public Trie(){
            this.children = new Trie[26];
            this.words = new ArrayList<>();
        }
    }
    Trie head;
    
    public void insert(String word){
        Trie node = head;
        node.words.add(word);
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(node.children[c - 'a'] == null){
                Trie newNode = new Trie();
                node.children[c-'a'] = newNode;
            }
            node = node.children[c-'a'];
            node.words.add(word);
        }
    }

    public List<String> search(String word){
        Trie node = head;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(node.children[c - 'a'] == null){
                return new ArrayList<>();
            }
            node = node.children[c-'a'];
        }
        return node.words;
    }
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        result= new ArrayList<>();
        head = new Trie();
        //Insert all words in trie
        for(String word: words){
            insert(word);
        }

        //recurse
        for(String word: words){
            ArrayList<String> path = new ArrayList<>();
            path.add(word);
            dfs(path);
        }

        return result;
    }

    public void dfs(ArrayList<String> path){
        //base
        if(path.size() == path.get(0).length()){
            result.add(new ArrayList<>(path));
            return;
        }

        //logic
        //action
        int s = path.size();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s; i++){
            char c = path.get(i).charAt(s);
            sb.append(c);
        }
        
        List<String> pot = search(sb.toString());
        for(String p: pot){
            path.add(p);
            //recurse
            dfs(path);
            //backtrack
            path.remove(path.size()-1);
        }
    }
}
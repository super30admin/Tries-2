//Time - O(n*k^l)
//Space - O(nk)
class Solution {

    class TrieNode {
        TrieNode[] children;
        List<String> li;

        public TrieNode(){
            this.children = new TrieNode[26];
            this.li = new ArrayList<>();
        }
    }

    private void insert(String word){
        TrieNode curr = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c-'a'] == null){
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.li.add(word);
        }

    }

    private List<String> search(String prefix){
        TrieNode curr = root;
        for(int i=0; i< prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c - 'a'] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.li;
    }

    TrieNode root;

    List<List<String>> result = new ArrayList<>();
    public List<List<String>> wordSquares(String[] words) {
        root = new TrieNode();
        result = new ArrayList<>();
        int le = words[0].length();
        for(int i=0; i<words.length; i++){
            insert(words[i]);
        }
        List<String> tempList = new ArrayList<>();
        for(int i=0; i<words.length ; i++){
            tempList.add(words[i]);
            backtrack(tempList,le);
            tempList.remove(tempList.size()-1);
        }

        return result;
    }
    void backtrack(List<String> li, int l){
        //base
        if(li.size() == l){
            result.add(new ArrayList<>(li));
            return;
        }

        //logic
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<li.size(); i++){
            sb = sb.append(li.get(i).charAt(li.size()));
        }
        List<String> prefixStrings = search(sb.toString());
        for(int j=0; j<prefixStrings.size(); j++){
            li.add(prefixStrings.get(j));
            backtrack(li,l);
            li.remove(li.size()-1);
        }

    }
}
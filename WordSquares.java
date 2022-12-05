class Solution {
    //tc-26^k+nK(Trie)
    //sc-o(n)
    class TrieNode
    {
        TrieNode[] children;
        List<String> startsWith;
        public TrieNode()
        {
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }
    TrieNode root;
    public void insert(String word)
    {
        TrieNode curr = root;
        for(int i=0;i<word.length();i++)
        {
            char c = word.charAt(i);
            if(curr.children[c-'a'] == null)
            {
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
            curr.startsWith.add(word);
        }
    }
    private List<String> startsWith(String word)
    {
        TrieNode curr = root;
        for(int i=0;i<word.length();i++)
        {
            char c = word.charAt(i);
            if(curr.children[c-'a'] == null) return new ArrayList<>();
            curr = curr.children[c-'a'];
        }
        return curr.startsWith;
    }
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        if(words == null || words.length==0) return result;
        root = new TrieNode();
        for(String word : words)
        {
            insert(word);
        }
        List<String> temp = new ArrayList<>();
        for(String word: words)
        {
            //action
            temp.add(word);
            //recurse
            backtrack(temp,word.length());
            //backtrack
            temp.remove(temp.size()-1);
        }
        return result;     
    }

    private void backtrack(List<String> temp,int k)
    {
        //base
        if(temp.size() == k)
        {
            result.add(new ArrayList<>(temp));
            return;
        }
        //logic
        StringBuilder prefix = new StringBuilder();
        int i=temp.size();
        for(String word: temp)
        {
            prefix.append(word.charAt(i));
        }
        List<String> startsWith = startsWith(prefix.toString());
        for(String word: startsWith)
        {
            //action
            temp.add(word);
            //recurse
            backtrack(temp,k);
            // backtrack
            temp.remove(temp.size()-1);

        }

    }
}
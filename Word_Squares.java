class Solution {
    //Time O(N*26^L)
    //Space O(N*L)
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
    private void BuildTrie(String[] words)
    {
        for(String word : words)
        {
            TrieNode curr = root;
            for(int i = 0 ; i < word.length() ; i++)
            {
                char c = word.charAt(i);
                if(curr.children[c-'a'] == null)
                {
                    curr.children[c-'a'] = new TrieNode();
                }
                curr.startsWith.add(word);
                curr = curr.children[c-'a'];
            }
        }
    }
    
    private List<String> startsWith(String prefix)
    {
        TrieNode curr = root;
        for(int i = 0 ; i < prefix.length() ; i++)
        {
            char c = prefix.charAt(i);
            if(curr.children[c - 'a'] == null)
            {
                return new ArrayList<>();
            }
            curr = curr.children[c-'a'];
        }
        return curr.startsWith;
    }
    
    List<List<String>> result;
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        if(words == null || words.length == 0)
        {
            return result;
        }
        root = new TrieNode();
        BuildTrie(words);
        List<String> list = new ArrayList<>();
        for(String word : words)
        {
            list.add(word);
            
            Backtrack(list);
            
            list.remove(list.size()-1);
        }
        return result;
    }
    
    private void Backtrack(List<String> list)
    {
        //base
        if(list.size() == list.get(0).length())
        {
            result.add(new ArrayList<>(list));
            return;
        }
        //logic
        StringBuilder sb = new StringBuilder();
        int index = list.size();
        for(String word : list)
        {
            sb.append(word.charAt(index));
        }
        List<String> WordsList = startsWith(sb.toString());
        for(String word : WordsList)
        {
            list.add(word);
            
            Backtrack(list);
            
            list.remove(list.size()-1);
        }
    }
}
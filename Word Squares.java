//Time Complexity-O(26^n)
class Solution {
    class TrieNode{
            TrieNode[] children;
            List<String>startsWith;
            TrieNode()
            {
                children=new TrieNode[26];
                startsWith=new ArrayList();
            }            
        }
        TrieNode CreateTrie(String[] words)
        {
            TrieNode root=new TrieNode();
            for(String word:words)
            {
                TrieNode cur=root;
                for(Character c:word.toCharArray())
                {
                    if(cur.children[c-'a']==null)
                    {
                        cur.children[c-'a']=new TrieNode();
                    }
                    cur.startsWith.add(word);
                    cur=cur.children[c-'a'];
                }
            }
            return root;
        }
        
        List<String>StartsWithPrefix(TrieNode root,String prefix)
        {
            TrieNode cur=root;
            for(Character p:prefix.toCharArray())
            {
                if(cur.children[p-'a']==null)
                {
                        return new ArrayList();
                }
                cur=cur.children[p-'a'];
            }
            return cur.startsWith;
        }
    List<List<String>>output=new ArrayList();
    public List<List<String>> wordSquares(String[] words) {
        if(words==null||
          words.length==0)
        {
            return output;
        }
        TrieNode root= CreateTrie(words);
        List<String>list=new ArrayList();
        for(String word:words)
        {
            list.add(word);
            BackTrack(root,list,word.length());
            list.remove(list.size()-1);
        }
     return output;   
    }
    void BackTrack(TrieNode root,List<String>list,int len)
    {
        if(list.size()==len)
        {
            output.add(new ArrayList(list));
            return;
        }
        StringBuilder prefix=new StringBuilder();
        int i=list.size();
        for(String w:list)
        {
            prefix.append(w.charAt(i));
        }
        List<String>li=StartsWithPrefix(root,prefix.toString());
        for(String word:li)
        {
            list.add(word);
            BackTrack(root,list,len);
            list.remove(list.size()-1);
        }
    }
}
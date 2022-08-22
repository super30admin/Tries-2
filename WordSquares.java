//Time complexity: O(n2)
//Space complexity: O(n)
class Solution {
    TrieNode root;
    List<List<String>> result;
    class TrieNode{
        TrieNode[] arr;
        Boolean isEnd;
        List<String> preWords;
        public TrieNode(){
            arr=new TrieNode[26];
            isEnd=false;
            preWords=new ArrayList<>();
            
        }
    }
    public void buildTrie(String word){
        TrieNode curr=root;
        for(int i=0;i<word.length();i++)
        {
            Character c=word.charAt(i);
            if(curr.arr[c-'a']==null)
                curr.arr[c-'a']=new TrieNode();
            curr=curr.arr[c-'a'];
            curr.preWords.add(word);
        }
        curr.isEnd=true;
    }
    public List<String> prefixSearch(String word){
        TrieNode curr=root;
        for(int i=0;i<word.length();i++)
        {
           Character c=word.charAt(i);
            if(curr.arr[c-'a']==null)
                return new ArrayList<>();
            else
                curr=curr.arr[c-'a'];
        }
        return curr.preWords;
    }
    public List<List<String>> wordSquares(String[] words) {
        TrieNode root=new TrieNode();
        result=new ArrayList<>();
        this.root=root;
        for(int i=0;i<words.length;i++)
        {
            String word=words[i];
            buildTrie(word);
        }
        for(int j=0;j<words.length;j++)
        {
            List<String> temp=new ArrayList<>();
            temp.add(words[j]);
            helper(words[j].length(),1,temp);
        }
        return result;
    }
    
    public void helper(int k,int idx,List<String> path){
        
        if(idx==k)
        {
            result.add(new ArrayList<String>(path));
            return;
        }

        StringBuilder sb=new StringBuilder();
        for(int j=0;j<=idx-1;j++){
            sb.append(path.get(j).charAt(idx));
        }
        List<String> prefixMatch= prefixSearch(sb.toString());
        for(String ele:prefixMatch)
        {
            if(ele.length()==k){
                path.add(ele);
                helper(k,idx+1,path);
                path.remove(path.size()-1);
                }
        }
        
         
    }
}
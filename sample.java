//Problem 1: List of word squares
// Time Complexity : O(n^n)
// Space Complexity : O(n^2*l) l=avg length of word
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
class Solution {
    //O(n^n) O(n^2*l)
        class TrieNode{
            TrieNode[] children;
            List<String> startsWith;

            public TrieNode(){
                this.children=new TrieNode[26];
                this.startsWith=new ArrayList<>();
            }
        }
    public void insert(String word) {
        TrieNode cur=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(cur.children[c-'a'] == null){ //if there is no character present, add a new TrieNode 
                cur.children[c-'a']=new TrieNode();
            }
            cur=cur.children[c-'a']; //shift current position
            cur.startsWith.add(word);
        }
    }

    public List<String> searchWords(String prefix) {
       TrieNode cur=root;
        for(int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            if(cur.children[c-'a'] ==null){
                return new ArrayList<>();
            }
            cur=cur.children[c-'a'];
        }
        return cur.startsWith; 
    }
    List<List<String>> res;
    TrieNode root;
    public List<List<String>> wordSquares(String[] words) {
        this.res=new ArrayList<>();
        this.root= new TrieNode();
        for(String wor: words){
           insert(wor); 
        }

        List<String> li= new ArrayList<>();
        int l=words[0].length();
        for(String word: words){

           //action
            li.add(word);
           //logic
            backtrack(li,l);

            li.remove(li.size()-1);
        }
        return res;
    }

    private void backtrack(List<String> li, int len){
        //base
        if(len==li.size()){
            res.add(new ArrayList<>(li));
            return;
        }
        //logic
        //all words which are starting with current prefix
        StringBuilder curPrefix=new StringBuilder();
        int idx=li.size();
        for(String str: li){
            curPrefix.append(str.charAt(idx));
        }

        List<String> startsWith= searchWords(curPrefix.toString());

        for(String st: startsWith){
            li.add(st);
            backtrack(li, len);
            li.remove(li.size()-1);
        }
    }
}

//Problem 2: Match Cases
// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
//Two pointer approach, when we match the first pattern character, look for second and do this untill we finish pattern characters.
class Solution {
    //O(N*(m+k)) O(N^2*L^2)
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res= new ArrayList<>();
        int n=pattern.length();
        
        for(String query: queries){
            int m=query.length();
            boolean result=false;
            int j=0;
            for(int i=0;i<m;i++){
                char c=query.charAt(i);
                if(j<n && c==pattern.charAt(j)){
                    j++;
                    if(j==n){
                        result=true;
                    }
                }else if(Character.isUpperCase(c)){
                    result=false;
                    break;
                }
            }
            res.add(result);
        }
        return res;
    }
}

//Problem 3: Top K Frequently Repeating
// Time Complexity : O(nlogk)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
class Solution {
    //O(nlogk) //O(n)
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums){
            map.put(i, map.getOrDefault(i,0)+1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a)); //Sort by Frequency in descending order   
        int res[] = new int[k];
        for (int i = 0; i < k; ++i)
            res[i] = list.get(i);
        return res;
    }
}
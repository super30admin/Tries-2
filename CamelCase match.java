//time complexity-O(q*p)
//Space complexity-O(1)
//Ran on leetcode-Yes
//Solution with comments-
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int pIndex;
        List<Boolean> out= new ArrayList<>();
        
        for(String q: queries){
            pIndex=0;
            boolean ans=false;
            for(int qIndex=0;qIndex<q.length();qIndex++){
                if(pIndex<pattern.length() && q.charAt(qIndex)==pattern.charAt(pIndex)){//comparing same characters
                    pIndex++;
                    if(pIndex==pattern.length())
                        ans=true;
                }
                else if(Character.isUpperCase(q.charAt(qIndex))){//if there is an extra upper case letter in q
                    ans=false;
                    break;
                }
            }
            out.add(ans);
        }
        return out;
    }
}
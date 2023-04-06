// Time Complexity : O(n(l+k))
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Use two pointer approach
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for(String q:queries){
            int j=0;boolean flag = true;
            for(int i=0;i<q.length();i++){
                if(Character.isUpperCase(q.charAt(i))){
                    if(j== pattern.length() || q.charAt(i)!=pattern.charAt(j)){
                        flag = false; break;
                    }
                    else j++;
                }
                else if(j< pattern.length() && pattern.charAt(j)==q.charAt(i)) j++;
            }
            if(j!=pattern.length()) flag = false;
            result.add(flag);
        }
        return result;
    }
}
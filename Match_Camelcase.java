// Time Complexity : O(nk) // n: queries len, k: avg word len  
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        
        
        ArrayList<Boolean> result = new ArrayList<>();

        for (String word : queries){
            int j=0; // pattern pointer
            boolean flag = false;
            for(int i = 0; i < word.length(); i++){
                if(j< pattern.length() && word.charAt(i) == pattern.charAt(j)){
                    j++;
                }
                else if(Character.isUpperCase(word.charAt(i))){
                    flag = false;
                    break;
                }
                if(j == pattern.length()){
                    flag = true;
                }
            }
            result.add(flag);
        }
        return result;
    }
}
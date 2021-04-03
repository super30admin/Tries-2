// Time Complexity : The time complexity is O(mn) where m is the length of each string and n is the lenght of the array
// Space Complexity : Te space complexity is O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {

        List<Boolean> res = new ArrayList<>();

        for(String query:queries){

            int len1 = query.length();
            int len2 = pattern.length();

            if(len2 > len1){
                res.add(false);
                continue;
            }

            int i = 0;
            int j = 0;

            //compare each string with the pattern
            while(i < len1 && j < len2){

                char char1 = query.charAt(i);
                char char2 = pattern.charAt(j);

                if(char1 == char2){
                    i++;j++;
                }
                else{
                    if(Character.isUpperCase(char1)){
                        break;
                    }
                    i++;
                }
            }

            if(j < len2){
                res.add(false);
            }
            else{
                while(i < len1 && !Character.isUpperCase(query.charAt(i))){
                    i++;
                }
                if(i < len1){
                    res.add(false);
                }
                else{
                    res.add(true);
                }
            }
        }
        return res;
    }
}
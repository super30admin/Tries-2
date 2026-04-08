// Time Complexity : O(N*(q+p))
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// For each word, run a 2 pointer to go through the pattern
// if both match, increment both, 
// if they dont match and word contain upper case, break and mark it false
// check if the pattern pointer is fully traversed

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList();

        for (String s : queries) {
            boolean flag = true;
            int i = 0;
            int j = 0;
            while (i < s.length()) {
                if (j < pattern.length() && s.charAt(i) == pattern.charAt(j)) {
                    j++;
                } else if (Character.isUpperCase(s.charAt(i))) {
                    flag = false;
                    break;
                }
                i++;
            }
            if (j != pattern.length())
                flag = false;
            result.add(flag);
        }

        return result;
    }
}
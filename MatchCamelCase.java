// Time Complexity : O(N * (m + n)), N = number of queries, m,n = lengths of query and pattern strings
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We use 2 pointers to compare each character for both the strings. If the letter is an uppercase and they match,
we increment both the pointers. If not, we return false. If the letter is a lowercase, we just increment the
query pointer. We check if we have reached the end of the pattern pointer and return true/false accordingly.
 */
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();

        for(String query : queries) {
            result.add(isMatch(query, pattern));
        }
        return result;
    }

    private boolean isMatch(String query, String pattern) {
        int i = 0 , j = 0;

        while(i < query.length()) {
            if(j < pattern.length() && query.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            else if(Character.isLowerCase(query.charAt(i)))
                i++;
            else
                return false;
        }
        return j == pattern.length();
    }
}
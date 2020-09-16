// Time Complexity : O(m * n) --> where m is the average length of each word and n is the legnth of the input string array
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        // edge case
        if (queries == null || queries.length == 0) return result;
        for (String query : queries) {
            int i = 0; // pattern pointer
            boolean flag = false;
            for (int j = 0; j < query.length(); j++) {
                if (i < pattern.length() && query.charAt(j) == pattern.charAt(i)) {
                    i++;
                    if (i == pattern.length()) flag = true;
                }
                else if (Character.isUpperCase(query.charAt(j))) {
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }
}
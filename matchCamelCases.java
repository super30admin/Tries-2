// Time complexity = O(N *(k+m)), where N=no. of strings in the list, k = avg length of the string and m = length of pattern
// Space complexity = O(1)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// We use 2 pointers one for query and another for pattern, once we reach the length of the pattern
// we check if the query still has a (unmatched) capital letter and we mark it as false and break the loop.
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if (queries == null || queries.length == 0) return new ArrayList<>();
        List<Boolean> result = new ArrayList<Boolean>();

        for (String query: queries) {
            int i = 0;                                // pointer for pattern
            boolean matched = false;

            for (int j=0; j<query.length(); j++) {  // traverse each character in query
                if (i<pattern.length() && pattern.charAt(i) == query.charAt(j)) {
                    i++;
                    if (i == pattern.length()) {
                        matched = true;
                    }
                }
                else if (Character.isUpperCase(query.charAt(j))) {
                    matched = false;
                    break;
                }
            }
            result.add(matched);
        }

        return result;
    }
}
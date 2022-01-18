// Time Complexity: O(m + n)
// Space Complexity: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for(String query : queries) {
            int i = 0; // ptr on pattern
            boolean flag = false;
            for(int j = 0; j < query.length(); j++) {
                char qChar = query.charAt(j);
                if(i < pattern.length() && qChar == pattern.charAt(i)) {
                    i++;
                    if(i == pattern.length()) {
                        flag = true;
                    }
                } else if(Character.isUpperCase(qChar)) {
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }
}
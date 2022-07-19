/*
Problem: https://leetcode.com/problems/camelcase-matching/
TC: O(n * k) where k is the average length of queries
*/

// Approach 1
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        if (queries == null || queries.length == 0)
            return result;
        
        for (String query : queries) {
            int patternIndex = 0;
            boolean isMatch = false;
            
            for (int qIndex = 0; qIndex < query.length(); ++qIndex) {
                if (patternIndex < pattern.length() && pattern.charAt(patternIndex) == query.charAt(qIndex)) {
                    ++patternIndex;
                    if (patternIndex == pattern.length()) {
                        isMatch = true;
                    }
                } else if (Character.isUpperCase(query.charAt(qIndex))) {
                    isMatch = false;
                    break;
                }
            }
            result.add(isMatch);
        }
        
        return result;
    }
}
// Time Complexity : O(n*(m+l)) where n is the length of the array queries, m is the length of the string 
// pattern and l is the average length of strings in queries
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class camelCaseMatching {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        for (String query : queries) {
            boolean flag = false;
            int i = 0;
            for (int j = 0; j < query.length(); j++) {
                char c = query.charAt(j);
                if (i < pattern.length() && c == pattern.charAt(i)) {
                    i++;
                    if (i == pattern.length()) {
                        flag = true;
                    }
                }
                else if (Character.isUpperCase(c)) {
                    flag = false;
                    break;
                }
            }
            ans.add(flag);
        }
        return ans;
    }
}
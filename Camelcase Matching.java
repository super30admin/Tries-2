// Time Complexity : 0(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


class Solution {
    //aesha
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();

        for (String query : queries) {
            int j = 0;
            boolean flag = false;
            for (int i = 0; i < query.length(); i++) {
                if (j < pattern.length() && query.charAt(i) == pattern.charAt(j)) {
                    j++;
                    if (j == pattern.length())
                        flag = true;
                } else if (Character.isUpperCase(query.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }

}
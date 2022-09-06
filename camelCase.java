// Time Complexity : O(N(m +n));
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;
class Main {
    // approch 1 two pointers
    public static List<Boolean> camelMatch(String[] queries, String pattern) {
        // null case
        if (queries == null || queries.length == 0)
            return new ArrayList<>();

        List<Boolean> result = new ArrayList<>();
        // go over all the queries and
        // check it is valid or not
        for (String query : queries) {
            int j = 0;
            // intially flag is false
            boolean flag = false;
            for (int i = 0; i < query.length(); i++) {
                // check if both char matches in query
                // and pattern increase j pointer
                if (j < pattern.length() && query.charAt(i) == pattern.charAt(j)) {
                    j++;
                    // if j pointer reaches at the end
                    // make flag true
                    if (j == pattern.length()) {
                        flag = true;
                    }
                } else if (Character.isUpperCase(query.charAt(i))) {
                    // else also check if encounter uppercase letter
                    // in query and does not matches with the
                    // pattern make flag false and break the loop
                    flag = false;
                    break;
                }
            }
            // add flag inside the result
            result.add(flag);

        }
        // return result
        return result;
    }
    public static void main(String[] args) {
        String[] queries = new String[] { "FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack" };
        String pattern = "FB";
        System.out.println(camelMatch(queries, pattern));
    }
}
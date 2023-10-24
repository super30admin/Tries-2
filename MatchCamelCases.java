import java.util.ArrayList;
import java.util.List;

// Time Complexity : O(k*(m+n)); k is the number of queries,
// m is the average length of each query and n is the length of the pattern.
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes

public class MatchCamelCases {
    public List<Boolean> camelMatch(String[] queries, String pattern) {

        List<Boolean> result = new ArrayList<>();
        int n = pattern.length();
        for (String query : queries) {
            boolean flag = false;
            int m = query.length();
            int j = 0;
            for (int i = 0; i < m; i++) {
                char qc = query.charAt(i);

                if (j < n && qc == pattern.charAt(j)) {
                    j++;
                    if (j == n) flag = true;
                } else if (Character.isUpperCase(qc)) {
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }
}
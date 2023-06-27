// Approach: 2 pointers
// Time: O(nk) where n is the avg length of each query
// Space: O(1)

import java.util.*;

class CamelcaseMatching {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for (String query : queries) {
            boolean flag = false;
            int j = 0;      // pattern string ptr
            for (int i = 0; i<query.length(); i++) {
                char cq = query.charAt(i);
                if (j < pattern.length() && cq == pattern.charAt(j)) {
                    j++;
                    if (j == pattern.length()) {
                        flag = true;
                    }
                } else if (Character.isUpperCase(cq)) {
                    flag = false;
                    break;
                }
            }
            result.add(flag);
        }
        return result;
    }
}
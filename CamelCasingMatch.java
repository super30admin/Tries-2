// Time: O(N(M+K)) | Space: O(1)

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for (String query : queries) {
            int p = pattern.length();
            int q = query.length();
            int i = 0;
            int j = 0;
            boolean flag = true;
            while (i < q) {
                if (j < p && query.charAt(i) == pattern.charAt(j)) {
                    j++;
                    if (j == p) flag = true;
                } else {
                    if (Character.isUpperCase(query.charAt(i))) {
                        flag = false;
                        break;
                    }
                }
                i++;
            }
            if (j < p) flag = false;
            result.add(flag);
        }
        return result;
    }
}
// Time Complexity: O(n * l)
// Space Complexity: O(n)

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>();
        for (String query : queries) {
            boolean flag = false;
            int j = 0;

            for (int i = 0; i < query.length(); i++) {
                if (j < pattern.length() && pattern.charAt(j) == query.charAt(i)) {
                    j++;

                    if (j == pattern.length())
                        flag = true;

                } else if (!Character.isLowerCase(query.charAt(i))) {
                    flag = false;
                    break;
                }
            }

            result.add(flag);
        }

        return result;
    }
}
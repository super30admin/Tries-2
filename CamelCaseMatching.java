// Time Complexity: O(k *(n + m)) where k is the length of queries, n is the average lenght of query and
// m is the lenght of the pattern.
// Space Complexity: O(1)
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if (queries == null || queries.length == 0) return new ArrayList<>();
        List<Boolean> result = new ArrayList<>();

        for (String query: queries) {
            int i=0;
            boolean isMatched = false;
            for (int j =0; j<query.length(); j++) {
                if (i< pattern.length() && query.charAt(j) == pattern.charAt(i)) {
                    i++;
                    if (i == pattern.length()) {
                        isMatched = true;
                    }
                } else if (query.charAt(j) >= 'A' && query.charAt(j) <='Z') {
                    isMatched = false;
                    break;
                }
            }
            result.add(isMatched);
        }

        return result;
    }
}
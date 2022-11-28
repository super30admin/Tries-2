// TC:O(Query Length) 
// SC:O(No of Queries)

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ret = new LinkedList<>();
        for (String query:queries) {
            ret.add(check(query, pattern));
        }

        return ret;
    }

    private Boolean check(String query, String pattern) {
        int patternLen = pattern.length(), patternPos = 0, uppercaseCount = 0;
        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i);
            if (Character.isUpperCase(c)) {
                if (patternPos < patternLen && c != pattern.charAt(patternPos)) {
                    return false;
                }
                uppercaseCount++;
                if (uppercaseCount > patternLen) {
                    return false;
                }
                patternPos++;
            } else {
                if (patternPos < patternLen && c == pattern.charAt(patternPos)) {
                    patternPos++;
                }
            }
        }

        return patternPos == patternLen;
    }
}
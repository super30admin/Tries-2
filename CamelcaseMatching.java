// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> result = new ArrayList<>(queries.length);
        for(String query: queries) {
        	result.add(match(query, pattern));
        }
        return result;
    }

    private boolean match(String query, String pattern) {
    	int index = 0; // pattern
    	for(char ch: query.toCharArray()) {
    		if(index < pattern.length() && pattern.charAt(index) == ch) {
    			index++;
    		} else if(Character.isUpperCase(ch)) {
    			return false;
    		}
    	}

    	return index == pattern.length();
    }
}
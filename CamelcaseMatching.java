// Simply iterating through each word, and running a inner loop on each char of that word, comparing if the chars at pattern and 
// char at curr index of word matches, we increment pointer on pattern also, indicating that the first matching char of P found in 
// word. If doenst match, we check if the char at word is uppercase than simply make the flag false. At end if the flag is true and
// we have matched all the chars in pattern, add true in result else false.

// Time Complexity : O(NL) L = avg length of each word and N = length of input array
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        // Base case
        if (queries == null || queries.length == 0) {
            return new ArrayList<>();
        }
        List<Boolean> result = new ArrayList<>();
        // Iterate over each word
        for (String word : queries) {
            // Take a flag
            boolean flag = true;
            int i = 0; // Index on the pattern
            // Inner loop on each char of this word
            for (int j = 0; j < word.length(); j++) {
                // Take one char at a time
                char c = word.charAt(j);
                // Check if the chars matches
                if ((i < pattern.length()) && (pattern.charAt(i) == c)) {
                    // Increment pointer on the pattern
                    i++;
                }
                // Else if doesnt match, check if the char in word is a uppercase, which is not
                // allowed
                else if (Character.isUpperCase(c)) {
                    // Turn flag to false
                    flag = false;
                    // And break
                    break;
                }
            }
            // Check if the flag is true and the we have matched all chars in pattern that
            // i==pattern.length()
            if (flag == true && i == pattern.length()) {
                // In that case add true
                result.add(true);
            } else {
                // Else false
                result.add(false);
            }

        }
        return result;
    }
}
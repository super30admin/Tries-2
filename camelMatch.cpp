// Time complexity: O(m+n)k where m is the length of string, n is the number of queries and k is the pattern len
// Space complexity: O(1)

/*
The solution is true when:
Pattern matches and we reach the end of the pattern 
provided we don't see any new Capital letters in the query 
*/

class Solution {
public:
    vector<bool> camelMatch(vector<string>& queries, string pattern) {
        if(queries.size() == 0)
            return vector<bool>();

        vector<bool> result;
        for(string query: queries) {
            int p = 0;
            bool match = false;
            for(int q = 0; q<query.length(); q++) {
                if(p < pattern.length() && pattern[p] == query[q]) {
                    p++;
                    if(p == pattern.length())
                        match = true;
                }
                else if(query[q] >= 'A' && query[q] <= 'Z') {
                    match = false;
                    break;
                }
            }
            result.push_back(match);
        }
        return result;
    }
};
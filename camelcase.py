# Time Complexity : O(N*M * k ) where N is number of queries and M is length of each query and k is length of pattern
# Space Complexity : O(N) list to store result
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am using two pointers to match each query with the pattern.
# For each character in the query, if it matches the current character in the pattern, I move both pointers forward.
# If it does not match and the character in the query is uppercase, I return False as it cannot match the pattern.
# If it does not match and the character in the query is lowercase, I only move the query pointer forward.
# At the end, if I have matched all characters in the pattern, I return True.
# I repeat this for all queries and store the results in a list. 
from typing import List
class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        res = []
        def match(query):
            i = 0
            j = 0
            while i < len(query):
                if j < len(pattern) and query[i] == pattern[j]:
                    i += 1
                    j += 1
                elif query[i].isupper():
                    return False
                else:
                    i += 1
            return j == len(pattern)
        for i in queries:
            res.append(match(i))
        return res
        
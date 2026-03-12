# Time Complexity : O(Q * L)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# Use two pointers to match pattern with query.
# Lowercase characters can be skipped.
# Uppercase characters must match pattern.

class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        
        def match(q):
            i = 0
            
            for c in q:
                if i < len(pattern) and c == pattern[i]:
                    i += 1
                elif c.isupper():
                    return False
            
            return i == len(pattern)
        
        return [match(q) for q in queries]
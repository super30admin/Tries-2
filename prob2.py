# Time Complexity : O(mn)
# Space Complexity : O(1)
# Passed on Leetcode: yes

class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        def canGenerate(query, pattern):
            i = j = 0
            
            while i < len(query) and j < len(pattern):
                if query[i] == pattern[j]:
                    i += 1
                    j += 1
                elif query[i].isupper():
                    return False  # Upper-case letters cannot be inserted
                else:
                    i += 1
            
            # Check if there are any remaining upper-case letters in the query
            while i < len(query):
                if query[i].isupper():
                    return False
                i += 1
            
            return j == len(pattern)
        
        result = [canGenerate(query, pattern) for query in queries]
        return result

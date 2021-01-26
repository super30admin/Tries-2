#Time Complexity : O(n*(m+k)) where n is the number of queries, m is the length of the pattern and k is the length of the queries
#Space Complexity : O(1) 
#Did this code successfully run on Leetcode : Yes

class Solution:
    def check(self, query, pattern):
        p = 0
        for j, char in enumerate(query):
            if p < len(pattern) and pattern[p] == char:
                p += 1
            elif char.isupper():
                return False

        return p == len(pattern)

    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        result = []

        for query in queries:
            result.append(self.check(query, pattern))

        return result

from typing import List
class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        result = []
        for query in queries:
            i = 0
            flag = False
            for j in range(len(query)):
                if i < len(pattern) and pattern[i] == query[j]:
                    i += 1
                    if i == len(pattern):
                        flag = True
                elif query[j].isupper():
                    flag = False
                    break
            result.append(flag)
        return result

# Time Complexity: O(n * (k + l)) where n is number of queries, k = length of query, l = length of pattern
# Space Complexity: O(1)
from typing import List
class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        res = []
        def doesmatch(query, pattern):
            i=0 #query
            j=0 #pattern
            while i<len(query):
                if j<len(pattern) and pattern[j] == query[i]:
                    i+=1
                    j+=1
                elif query[i].isupper():
                    return False
                else:
                    i+=1
            return j == len(pattern)
        for query in queries:
            res.append(doesmatch(query, pattern))
        return res

#Time: O(nk)
#Space: O(1)
#Program ran on leetcode successsfully

class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        res = []
        for query in queries:
            flag = False
            pIx = 0
            for i in range(len(query)):
                if pIx < len(pattern) and query[i] == pattern[pIx]:
                    pIx += 1
                    if pIx == len(pattern):
                        flag = True
                elif query[i].isupper():
                    flag = False
                    break
            res.append(flag)
        
        return res
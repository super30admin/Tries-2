'''
TC: O(m*n) where m is the number of words and n is the length of each words
SC: O(1) - since ans is the only space used and others are just pointers
'''
from typing import List

class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        
        def isMatch(query, pattern):
            i,j = 0,0
            while i<len(query):
                if j<len(pattern):
                    if query[i] == pattern[j]:
                        i+=1
                        j+=1
                        continue
                if ord(query[i])<97:
                    return False
                else:
                    i+=1
            return True if j>=len(pattern) else False

        ans = []
        for query in queries:
            ans.append(isMatch(query, pattern))
        return ans
s = Solution()
print(s.camelMatch(["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], "FB"))
print(s.camelMatch(["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], "FoBa"))
print(s.camelMatch(["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], "FoBaT"))

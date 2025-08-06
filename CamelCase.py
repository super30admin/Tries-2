#TC:O(n*l) l => average length of the word
#SC: O(1)
#2 pointers
#https://leetcode.com/problems/camelcase-matching/
class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        output = []
        for query in queries:
            output.append(self.checkMatch(query, pattern))
        return output
        
    def checkMatch(self,query, pattern):
        p = 0
        for q in range(0, len(query)):
            if p == len(pattern):
                if query[q].isupper():
                    return False
            elif query[q]==pattern[p]:
                p+=1
            elif query[q].isupper():
                return False
        return p == len(pattern)
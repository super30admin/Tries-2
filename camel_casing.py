#Time complexity : O(N*max(L+k)) where n is number of elements L is average size of string and k is length of patetrn
#Space complexity : O(1) 
class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        if len(queries)==0:
            return []
        pn = len(pattern)
        res = []
        for query in queries:
            ptr = 0
            match = True
            for index,word in enumerate(query):
                # print(word,pattern[ptr])
                if ptr<pn and word==pattern[ptr]:
                    ptr+=1
                elif ptr <pn and word.isupper() and word!=pattern[ptr]:
                    match = False
                    break
                elif word.isupper() :
                    match =False
                    break
            if ptr < pn :
                match=False
            res.append(match)
        return res
                
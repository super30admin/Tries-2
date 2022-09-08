""""// Time Complexity : O(n*(m+k))
                n -- no of words in list
                m -- len of pattern word
                k -- avg len of words in list
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""

class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        result = []

        for word in queries:
            flag = False
            j = 0
            for i in range(len(word)):
                if j < len(pattern) and word[i] == pattern[j]:
                    j += 1
                    if j == len(pattern):
                        flag = True
                elif j < len(pattern) and word[i].isupper() and pattern[j].islower():
                    flag = False
                    break

                elif word[i].isupper() == True:
                    flag = False
                    break
            result.append(flag)
        return result

# class Solution:
#     def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
#         result=[]

#         for query in queries:
#             flag=False
#             p=len(pattern)
#             q=len(query)
#             i=0
#             j=0


#             while i<q:
#                 if j<p and query[i]==pattern[j]:
#                     j+=1
#                     if j==p:
#                         flag=True
#                 elif query[i].isupper()==True:
#                     flag=False
#                     break

#                 i+=1

#             if j<p:
#                 flag=False
#             result.append(flag)
#         return result





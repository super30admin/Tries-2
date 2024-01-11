# Time: O(mn)
# Space: O(1)
# Did it run on Leetcode: yes

class Solution(object):
    def camelMatch(self, queries, pattern):
        """
        :type queries: List[str]
        :type pattern: str
        :rtype: List[bool]
        """
        # O(mn)
        # O(1)
        res=[]
        for query in queries:
            m=len(query)
            n=len(pattern)
            j=0
            flag=False
            for i in range(m):
                c=query[i]
                if(j<n and c==pattern[j]):
                    j+=1
                    if(j==len(pattern)):
                        flag=True
                elif(c.isupper()):
                    flag=False
                    break
            res.append(flag)
        return res
                
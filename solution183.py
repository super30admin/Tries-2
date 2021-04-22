#Time Complexity:O(n)
#Space Complexity:O(1)
#Approach:For each string in the queries array, Parse each character of the string. if the uppercase letters encountered is same as pattern
#append true into the result array , else return false. 
class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        result=[]
        for query in queries:
            flag=False
            i=0
            for j in range(len(query)):
                c=query[j]
                if i<len(pattern) and c==pattern[i]:
                    i+=1
                    if i==len(pattern):
                        flag=True
                elif c.isupper():
                    flag=False
                    break
            result.append(flag)
        return result
        
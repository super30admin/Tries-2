#Time O(n)

class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        a=[]
        
        for st in queries:
            a.append(self.check(st,pattern))
            
        return a
    
    def check(self,st,pattern):
        i = 0
        j = 0
        
        while i<len(st) or j<len(pattern):
                    
            if j==len(pattern):
                while i<len(st):
                    if st[i].isupper():
                        return False
                    i+=1
                    
            elif i==len(st):
                return False
            elif st[i]==pattern[j]:
                j+=1 
            elif st[i].isupper() and st[i]!=pattern[j]:
                return False
            i+=1
        return True 
 

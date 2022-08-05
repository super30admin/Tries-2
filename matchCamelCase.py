'''
Time Complexity: 0(n*(m+k)) 
                n -- no of words in list
                m -- len of pattern word
                k -- avg len of words in list
Space Complexity: 0(1)
Run on LeetCode: Yes
'''

class Solution:
    
    def __init__(self):
        self.boolean = []
    
    def chkMatch(self,query,pattern):
        
        # ptr for pattern
        ptr2 = 0
        
        for ptr1 in range(0,len(query)):
            
            # logic-case
            if ptr2 < len(pattern) and query[ptr1] == pattern[ptr2]:
                ptr2+= 1
            
            elif query[ptr1].isupper() == True:
                result = False
                return result
            
            else:
                continue
        
        '''end of iteration'''
        
        return (ptr2 == len(pattern)) 
    
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        
        for query in queries:
            self.boolean.append(self.chkMatch(query,pattern))
        
        return self.boolean
        
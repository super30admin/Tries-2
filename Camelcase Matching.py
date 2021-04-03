class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        #Approach: Two pointers
        #Time Complexity: O(n * k)
        #Space Complexity: O(1)
        #where, n is the number of queries and k is the average length of a query
        
        result = []
        for query in queries:
            p = q = 0
            
            flag = True
            while flag and q < len(query):
                while flag and p < len(pattern) and q < len(query):
                    if query[q] == pattern[p]:
                        p += 1
                    elif query[q].isupper():
                        flag = False
                    q += 1
            
                if q < len(query):    
                    if query[q].islower():
                        q += 1
                    else:
                        flag = False
                    
            result.append(flag and p == len(pattern))
            
        return result
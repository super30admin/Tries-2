# Time complexity :  n * (m + k) --> n - number of queries, m - avg. len of queries, k - lenght of pattern
# Space complexity : O(1)
# Leetcode : Solved and submitted

class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        # declare an empty res
        res = []
        
        # check for null case and return an empty res
        if not queries or not pattern:
            return res
        
        # traverse over each query
        for query in queries:
            # initialize the default value
            # i --> idx of query
            # j --> idx of pattern string
            # flag to identify valid or invalid string
            i, j = 0, 0
            flag = False
            
            # Traverse until the end of query string
            while i < len(query):
                ch = query[i]
                # if we are within bound of the pattern string and the chars match, then increment the j
                if j < len(pattern) and ch == pattern[j]:
                    j += 1
                    # if we have reached the end of pattern string, then make the flag as True
                    if j == len(pattern):
                        flag = True
                
                # Before that we find an upper case letter which is not in the string, then update the flag as False and break
                elif ch.isupper():
                    flag = False
                    break
                i += 1
                
            # append the flag value for each query in res and return the result
            res.append(flag)
        
        return res

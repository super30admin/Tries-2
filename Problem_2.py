# Camelcase Matching

# Time Complexity : Nk ie N is the number of queries, k is average length of query
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes Runtime 28 ms and Memory 14.3 MB
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
# Approach
"""
Using two pointer approach where one pointer is in queries and other is pointing to pattern
if character in queries for a string matches with character of string in pattern true is returned

"""

class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        return [self.isCamelMatch(query, pattern) for query in queries]
        
    def isCamelMatch(self, query: str, pattern: str) -> bool:
        i = 0 # query index
        j = 0 # pattern index
        while i < len(query) and j < len(pattern):
            if query[i] != pattern[j]:
                if not query[i].islower():
                    return False
            else:
                j =j + 1 
            i =i+ 1
        return j == len(pattern) and all(q.islower() for q in query[i:]) # The all() function returns True if all items in an iterable are true, 
                                                                         # otherwise it returns False.

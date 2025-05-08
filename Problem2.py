# Problem 2 : Camelcase Matching
# Time Complexity : O(max(m, n)) where m is the length of the query and n is the length of the pattern
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : None

# Your code here along with comments explaining your approach

from typing import List
class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        # define the result which will store array of boolean values for each query
        result = []
        # loop through the queries array
        for query in queries:
            # define j and set to 0 which will be used as pointer for the pattern
            j = 0
            # get the length of the pattern
            patternLength = len(pattern)
            # define flag and set to False initially
            flag = False
            # loop through each character of the query
            for i in range(len(query)):
                # check if j is less than the length of the pattern and character of query at ith position is equal to character of pattern at jth position
                if j < patternLength and query[i] == pattern[j]:
                    # if condition is true then increment the jth pointer
                    j += 1
                    # check if the j is equal to length of the pattern
                    if j == patternLength:
                        # if it is then set flag to True
                        flag = True
                # else check if the character of query at ith position is an upper case alphabet
                elif query[i].isupper():
                    # if it is then set the flag as False and break
                    flag = False
                    break
            # append the value flag to the result array
            result.append(flag)
        # return result array
        return result

from typing import List


class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        """
            https://leetcode.com/problems/camelcase-matching/
            Time Complexity - O(N(L+M))
            'L' is the length of pattern
            'M' is the avg length of query
            'N' is the number of queries
            Space Complexity - O(1)
        """
        result = []
        for query in queries:
            # pattern string
            i = 0
            flag = False
            for j in range(len(query)):
                # char at query string
                ch = query[j]
                if i < len(pattern) and ch == pattern[i]:
                    i += 1
                    # we are done with pattern string
                    if i == len(pattern):
                        flag = True
                # the ch is not a match with pattern string
                # and is upper case
                elif ch.isupper():
                    flag = False
                    break
            result.append(flag)
        return result
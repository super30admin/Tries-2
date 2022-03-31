# Time Complexity : O(m+n)k - k is no. of queries
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : Yes

from typing import List


class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        def match(query, patt):

            m = len(query)
            n = len(patt)

            if m < n:
                return False

            # two pointers
            i = 0
            j = 0

            while i < m and j < n:

                if query[i] == patt[j]:
                    if j == n-1:

                        for k in range(i+1, m):
                            if query[k].isupper():
                                return False

                        return True

                    i += 1
                    j += 1

                # query[i] != patt[j]
                else:
                    while i < m and query[i] != patt[j]:
                        i += 1

                        if i < m and query[i] != patt[j] and query[i].isupper():
                            return False

                    if (i < m and query[i] != patt[j]) or i == m:
                        return False

        return [match(queries[i], pattern) for i in range(len(queries))]

""""// Time Complexity : O(n*(m+k))
// Space Complexity :O(1)
"""

class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        result = []

        for word in queries:
            flag = False
            j = 0
            for i in range(len(word)):
                if j < len(pattern) and word[i] == pattern[j]:
                    j += 1
                    if j == len(pattern):
                        flag = True
                elif j < len(pattern) and word[i].isupper() and pattern[j].islower():
                    flag = False
                    break

                elif word[i].isupper() == True:
                    flag = False
                    break
            result.append(flag)
        return result

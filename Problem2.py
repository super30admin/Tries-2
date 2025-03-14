# Problem 2
# Match Camelcases (https://leetcode.com/problems/camelcase-matching/)

class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        result = []
        for word in queries:
            j = 0
            flag = False
            for i in range(len(word)):
                if j < len(pattern) and word[i] == pattern[j]:
                    j += 1
                    if j == len(pattern):
                        flag = True
                elif word[i].isupper():
                    flag = False
                    break
            result.append(flag)

        return result
        
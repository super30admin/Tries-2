'''
Solution:
1.  For each string, check whether it matches the pattern query and add the corresponding boolean the result.
2.  For each char in query, if equal => increment the pattern index otherwise if upper char found in query string when
    pattern's index is out of bounds => return False.
3.  At the end, check whether the pattern just reached the last index.

Time Complexity:    O(N * k) where N - avg. query size and k is number of queries.
Space Complexity:   O(1)

--- Passed all testcases successfully on leetcode.
'''


class CamelMatch:

    def __isMatch(self, query: str, pattern: str) -> bool:

        #   initializations
        currentPatternIndex = 0

        #   for each char in query
        for char in query:

            #   if equal => increment the pattern index
            if (currentPatternIndex < len(pattern) and char == pattern[currentPatternIndex]):
                currentPatternIndex += 1

            #   if upper char found in query string when pattern's index is out of bounds => return False
            elif (char.isupper()):
                return False

            else:
                continue

        #   check whether the pattern just reached the last index
        return currentPatternIndex == len(pattern)

    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:

        #   for each string, check whether it matches the pattern query and add the corresponding boolean the result
        boolResults = []

        for query in queries:
            boolResults.append(self.__isMatch(query, pattern))

        #   return the final result array
        return boolResults
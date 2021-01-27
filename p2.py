#time: O(m+k)*n, N is number of queries. K is len of each query and m is length of pattern
#Space: O(1)

class Solution:

    def isMatch(self, query: str, pattern: str) -> bool:

       
        currentPatternIndex = 0

        
        for ch in query:

           
            if (currentPatternIndex < len(pattern) and char == pattern[currentPatternIndex]):
                currentPatternIndex += 1

           
            elif (ch.isupper()):
                return False

            else:
                continue

       
        return currentPatternIndex == len(pattern)

    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:

       
        results = []

        for query in queries:
            results.append(self.isMatch(query, pattern))

       
        return results

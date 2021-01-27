class Solution:

    def isMatch(self, query: str, pattern: str) -> bool:

        index = 0
        for char in query:
            if index<len(pattern) and char == pattern[index]:
                index+=1
            elif char.isupper():
                return False
        return index == len(pattern)

    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:


        results = []

        for query in queries:
            results.append(self.isMatch(query, pattern))


        return results
# Time : O((Average word length + Pattern Length) * total number of words)
# Space: O(1)

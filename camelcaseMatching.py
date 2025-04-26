class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        # T: O(m * n), S: O(1)
        def matches(query: str) -> bool:
            i = 0  # Pointer for pattern
            for char in query:
                if i < len(pattern) and char == pattern[i]:
                    i += 1
                elif char.isupper():
                    return False  # extra uppercase char not allowed
            return i == len(pattern)

        return [matches(query) for query in queries]

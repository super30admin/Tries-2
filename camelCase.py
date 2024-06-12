class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        #Tc: O(n*m) Sc: O(n)
        def match(query: str, pattern: str) -> bool:
            i, j = 0, 0
            while i < len(query) and j < len(pattern):
                qc, pc = query[i], pattern[j]
                if qc == pc:
                    i += 1
                    j += 1
                elif qc.isupper():
                    return False
                else:
                    i += 1
            if j != len(pattern):
                return False
            return all(not c.isupper() for c in query[i:])

        return [match(query, pattern) for query in queries]

        
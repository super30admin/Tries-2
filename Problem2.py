class Solution:
    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        # Time complexity: O(N*M), where N is the number of queries and M is the maximum length of a query.
        # Space complexity: O(N), where N is the number of queries.
        def isMatch(query, pattern):
            i, j = 0, 0  # Initialize pointers for query and pattern
            while i < len(query) and j < len(pattern):
                if query[i] == pattern[j]:
                    i += 1  # Move to the next character in query
                    j += 1  # Move to the next character in pattern
                elif query[i].isupper():
                    return False  # If an uppercase character in query doesn't match, it's not a valid match
                else:
                    i += 1  # Continue comparing the next character in query

            # After the loop, if j == len(pattern), it means pattern is completely matched.
            # Now, check if there are any uppercase letters in the remaining part of query.
            if j == len(pattern):
                return not any(char.isupper() for char in query[i:])

            # If j != len(pattern), it means the pattern is not fully matched
            return False

        result = []  # Initialize an empty list to store the results

        for query in queries:
            # Check each query against the pattern and append the result to the list
            result.append(isMatch(query, pattern))

        return result  # Return the list of boolean results

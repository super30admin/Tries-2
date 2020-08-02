'''
Time Complexity: O(n)
Space Complexity: O(1)
Did this code successfully run on Leetcode : Yes
Explanation: For every word check if the pattern exists or not in it, you do this by using a 2 pointer approach in which
you check if the pattern[ptr1] == pattern[ptr2] if this is true you increase the pointer of the pattern by 1. If they are not
equall and the query is lower case ie query is lower case and pattern is uppercase you just move the pointer of the query by 1
If the query is Uppercase and pattern in lowercase this means its an invalid pattern so return False.
'''
class Solution:
    def match(self, query, pattern):
        index = 0  # pattern
        for ch in query:
            if index < len(pattern) and pattern[index] == ch:
                index += 1
            # query is upper case and pattern is lowercase
            elif ch.isupper():
                return False
                # skip over if query is lowercase and pattern is upper case until query becomes uppercase
        return index == len(pattern)

    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        result = []
        for query in queries:
            result.append(self.match(query, pattern))
        return result
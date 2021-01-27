# TIME COMPLEXITY: O(n(k+m)) where n is the no. of queries, k is length of the pattern and m is the avg length of a query
# SPACE COMPLEXITY: O(1)

class Solution(object):
    def camelMatch(self, queries, pattern):
        """
        :type queries: List[str]
        :type pattern: str
        :rtype: List[bool]
        """
        result = []
        for word in queries:
            result.append(self.check_match(pattern, word))
        return result

    # Function to check if a pattern matches a query
    def check_match(self, pattern, word):
        # Initialize a pointer to 0
        p = 0
        match = False
        # For every character, check if the character matches the pattern at pointer index
        for c in word:
            if p < len(pattern) and c == pattern[p]:
                # Increment pointer for a match
                p += 1
                # If pointer reaches end of pattern, set match to True
                if p == len(pattern):
                    match = True
            # If there is an upper case character after every character of the pattern is matched, set match to False
            elif c.isupper():
                match = False
                break
        return match

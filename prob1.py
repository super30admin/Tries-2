# Time Complexity : O(N * M^2)
# Space Complexity : O(N * M^2)
# Passed on Leetcode: yes

class Solution:
    def wordSquares(self, words):
        def is_prefix(prefix, word):
            return word.startswith(prefix)
        
        def backtrack(square):
            if len(square) == len(words[0]):
                squares.append(list(square))
                return
            
            col = len(square)
            prefix = ''.join(row[col] for row in square)
            
            for word in prefix_to_words[prefix]:
                if is_prefix(''.join(word[col + 1] for col in range(col)), word):
                    square.append(word)
                    backtrack(square)
                    square.pop()
        
        if not words:
            return []
        
        squares = []
        prefix_to_words = {}
        
        # Preprocess the words and build a dictionary of prefixes to words
        for word in words:
            for i in range(len(word)):
                prefix = word[:i]
                if prefix not in prefix_to_words:
                    prefix_to_words[prefix] = []
                prefix_to_words[prefix].append(word)
        
        # Start backtracking
        for word in words:
            square = [word]
            backtrack(square)
        
        return squares
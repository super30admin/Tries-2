# Time Complexity : O(N * 26^L) approx (Backtracking with prefix pruning)
# Space Complexity : O(N * L) for prefix hashmap
# Did this code successfully run on Leetcode : No
# Any problem you faced while coding this : No

# Explanation:
# Build a prefix hashmap mapping prefix -> list of words.
# Backtrack to construct the square row by row.
# For row k, the prefix needed is built from column k of previous rows.
# Only explore words matching this prefix.

from collections import defaultdict

class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        
        prefix = defaultdict(list)
        n = len(words[0])
        
        for word in words:
            for i in range(n+1):
                prefix[word[:i]].append(word)
        
        res = []
        
        def backtrack(step, square):
            
            if step == n:
                res.append(square[:])
                return
            
            pref = ""
            for w in square:
                pref += w[step]
            
            for candidate in prefix[pref]:
                square.append(candidate)
                backtrack(step+1, square)
                square.pop()
        
        for word in words:
            backtrack(1, [word])
        
        return res
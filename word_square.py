class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        n = len(words[0])
        wordsWithPrefix = defaultdict(list)
        for w in words:
            for i in range(1, n):
                wordsWithPrefix[w[:i]].append(w)
        ans = []
        def dfs(square):
            nonlocal n
            if (i := len(square)) == n:
                ans.append(square)
                return
            for w in wordsWithPrefix[''.join(list(zip(*square))[i][:i+1])]:
                dfs(square + [w])
        for w in words:        
            dfs([w])        
        return ans
        
        #tc is O(N*(26^L))  N is the number of input words and L is the length of a word
        #sc is o(NL)

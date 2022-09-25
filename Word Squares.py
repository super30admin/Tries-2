# Time Complexity : O(N(26^L)L)
# Space Complexity : O(NL)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
class TrieNode:
    def __init__(self):
        self.children = {}
        self.index = []

    def addWord(self, word, i):
        cur = self
        for w in word:
            if w not in cur.children:
                cur.children[w] = TrieNode()
            cur = cur.children[w]
            cur.index.append(i)
    
    
class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        n = len(words[0])

        root = TrieNode()
        for i, word in enumerate(words):
            root.addWord(word, i)

        def dfs(i, path):
            if i == n:
                res.append(path.copy())
                return

            prefix = ''.join([word[i] for word in path])
            for word in getWordsWithPrefix(prefix):
                path.append(word)
                dfs(i+1, path)
                path.pop()


        def getWordsWithPrefix(prefix):
            cur = root
            for char in prefix:
                if char not in cur.children:
                    return []
                cur = cur.children[char]
            return [words[i] for i in cur.index]

        res = []
        for word in words:
            dfs(1, [word])

        return res
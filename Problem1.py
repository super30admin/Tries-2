# Problem 1
# Word Squares (https://leetcode.com/problems/word-squares/)

class Solution:
    class TrieNode:
        def __init__(self):
            self.children = [None] * 26
            self.startsWith = []

    def __init__(self):
        self.root = self.TrieNode()

    def insert(self,word):
        curr = self.root
        for c in word:
            if not curr.children[ord(c) - ord("a")]:
                curr.children[ord(c) - ord("a")] = self.TrieNode()
            curr = curr.children[ord(c) - ord("a")]
            curr.startsWith.append(word)
    
    def searchPrefix(self,prefix):
        curr = self.root
        for c in prefix:
            if not curr.children[ord(c) - ord("a")]:
                return []
            curr = curr.children[ord(c) - ord("a")]
        return curr.startsWith

    def wordSquares(self, words: List[str]) -> List[List[str]]:
        result = []
        for word in words:
            self.insert(word)
            
        def dfs(path):
            if len(path) == len(words[0]):
                result.append(list(path))
                return

            idx = len(path)
            prefix = "".join(word[idx] for word in path)
            startsWith = self.searchPrefix(prefix)

            for word in startsWith:
                path.append(word)
                dfs(path)
                path.pop()
                 
        for word in words:
            dfs([word])

        return result

    

        


        
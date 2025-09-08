from typing import List
class TrieNode:
    def __init__(self):
        self.children = [None]*26
        self.startswith = []

class Solution:
    def insert(self,word):
        curr = self.root
        for c in word:
            index = ord(c)-ord('a')
            if not curr.children[index]:
                curr.children[index] = TrieNode()
            curr = curr.children[index]
            curr.startswith.append(word)

    def searchprefix(self,prefix):
        curr = self.root
        for c in prefix:
            index = ord(c) -ord('a')
            if not curr.children[index]:
                return []
            curr = curr.children[index]
        return curr.startswith
    
    def dfs(self,words,path):
        # base
        if len(path) == len(words[0]):
            self.res.append(list(path))
            return
        # logic
        idx = len(path)
        prefix = ''.join( str_[idx] for str_ in path)
        for word in self.searchprefix(prefix):
            path.append(word)
            self.dfs(words, path)
            path.pop()

    def wordSquares(self, words: List[str]) -> List[List[str]]:
        self.res = []
        self.root = TrieNode()
        for word in words:
            self.insert(word)
        
        path = []
        for word in words:
            path.append(word)
            self.dfs(words,path)
            path.pop()
        return self.res
        
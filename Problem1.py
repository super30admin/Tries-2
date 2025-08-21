"""
Time Complexity: O(n ^ L)
Space Complexity: O(N^2 * L^2)
"""
class TrieNode:
    def __init__(self):
        self.children = [None] * 26
        self.startsWith = []

class Solution:
    def insert(self, word):
        curr = self.root
        for c in word:
            index = ord(c) - ord('a')
            if curr.children[index] is None:
                curr.children[index] = TrieNode()
            curr = curr.children[index]
            curr.startsWith.append(word)

    def search(self, prefix):
        curr = self.root
        for c in prefix:
            index = ord(c) - ord('a')
            if curr.children[index] is None:
                return []
            curr = curr.children[index]
        return curr.startsWith

    def dfs(self, words, path):
        if len(path) == len(words[0]):
            self.result.append(list(path))
            return

        idx = len(path)
        prefix = ''.join(str_[idx] for str_ in path)

        for word in self.search(prefix):
            path.append(word)
            self.dfs(words, path)
            path.pop()

    def wordSquares(self, words):
        self.result = []
        self.root = TrieNode()

        for word in words:
            self.insert(word)

        path = []
        for word in words:
            path.append(word)
            self.dfs(words, path)
            path.pop()

        return self.result
# Time Complexity : O(N^2 * M) where N is number of words and M is length of each word
# Space Complexity : O(N * M)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
#I am using Trie to store all the words. For each word, I am trying to build the word square using DFS.
#For each level of DFS, I am finding the prefix using the current path and searching in the Trie for all the words with that prefix.
#If found, I am adding that word to the path and calling DFS for the next level.
#If the length of the path is equal to the length of the words, I am adding the path to the result. 
# Backtracking is used to remove the last word added to the path after returning from the DFS call.
# This way, all possible word squares are explored.
# The Trie helps in efficiently finding words with the required prefix.
# This approach ensures that we only explore valid word squares.


from typing import List
class Trie:
    def __init__(self):
        self.children = [None] * 26
        self.isEnd = False
    def insert(self,word):
        curr = self
        for c in word:
            w = ord(c) - ord('a')
            if curr.children[w] is None:
                curr.children[w] = Trie()
            curr = curr.children[w]
        curr.isEnd = True
class Solution:
    def __init__(self):
        self.root = Trie()
    def searchDFS(self,prefix):
        node = self.root
        for c in prefix:
            w = ord(c) - ord('a')
            if node.children[w] is None:
                return []
            node = node.children[w]
        res = []
        sb = list(prefix)
        self.dfs(node,sb,res)
        return res
    def dfs(self,node,path,res):
        if node is None:
            return 
        if node.isEnd:
            res.append("".join(path))
        for i in range(26):
            if node.children[i] is not None:
                path.append(chr(i + ord('a')))
                self.dfs(node.children[i],path,res)
                path.pop()
    
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        def helper(words,path):
            if len(path) == len(words[0]):
                res.append(list(path))
                return
            curPre = []
            idx = len(path)
            for w in path:
                curPre.append(w[idx])
            s = self.searchDFS("".join(curPre))
            for i in s:
                path.append(i)
                helper(words,path)
                path.pop()
        res = []
        for w in words:
            self.root.insert(w)
        path = []
        for w in words:
            path.append(w)
            helper(words,path)
            path.pop()
        return res

        
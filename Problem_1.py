"""
Time Complexity : Not sure- please advice. Maybe O(nlogn)
Space Complexity : Trie- 0(nm)- where n is total words and m is average length
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

We first make a trie out of the first so that we can easily access later. For making a square, the transpose should be equal.
For this purpose, we perform backtracking over the words, then to select the next word, we find out the words that would be 
applicable given a prefix. After we get a list of potential words, we backtrack on them
"""


class Solution:
    class TrieNode:
        def __init__(self):
            self.children = {}
            self.startsWith = []

    def buildTrie(self, words):
        root = self.TrieNode()
        for word in words:
            curr = root
            for w in word:
                if w not in curr.children:
                    curr.children[w] = self.TrieNode()
                curr = curr.children[w]
                curr.startsWith.append(word)
        return root

    def findWordsWithPrefix(self, prefix):
        curr = self.root
        for p in prefix:
            if p not in curr.children:
                return []
            curr = curr.children[p]
        return curr.startsWith

    def backtrack(self, arr, length):
        if len(arr) == length:
            self.result.append(list(arr))
            return

        temp = []
        l = len(arr)
        for a in arr:
            temp.append(a[l])
        prefix = ''.join(temp)
        listOfWords = self.findWordsWithPrefix(prefix)
        for word in listOfWords:
            arr.append(word)
            self.backtrack(arr, length)
            arr.pop()

    def wordSquares(self, words: List[str]) -> List[List[str]]:
        if not words:
            return []
        self.root = self.buildTrie(words)
        self.result = []
        arr = []
        l = len(words[0])
        for word in words:
            arr.append(word)
            self.backtrack(arr, l)
            arr.pop()
        return self.result

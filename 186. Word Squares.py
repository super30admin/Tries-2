class TrieNode:
    def __init__(self):
        self.children = [None for i in range(26)]
        self.startsWith = list()


class Solution:
    def __init__(self):
        self.root = None

    def insert(self, word):
        curr = self.root

        for i in range(len(word)):
            char = word[i]
            if not curr.children[ord(char) - ord("a")]:
                curr.children[ord(char) - ord("a")] = TrieNode()
            curr = curr.children[ord(char) - ord("a")]
            curr.startsWith.append(word)

    def search(self, prefix):
        curr = self.root
        for i in range(len(prefix)):
            char = prefix[i]
            if not curr.children[ord(char) - ord("a")]:
                return []
            curr = curr.children[ord(char) - ord("a")]
        return curr.startsWith

    result = 0

    def word_squares(self, words: List[str]) -> List[List[str]]:
        # write your code here
        self.result = list()
        self.root = TrieNode()
        li = list()
        for word in words:
            self.insert(word)

        for word in words:
            # action
            li.append(word)
            # recurse
            self.backtrack(li, len(word))
            # backtrack
            li.pop()
        # print(self.result)
        return self.result

    def backtrack(self, li, k):
        # base
        if k == len(li):
            self.result.append(li.copy())
            return

        # logic
        i = len(li)

        sb = list()
        for s in li:
            sb.append(s[i])
        startsWith = self.search("".join(sb))

        for word in startsWith:
            # action
            li.append(word)
            # recurse
            self.backtrack(li, len(word))
            # backtrack
            li.pop()


#Trie + backtracking
# Time complexity: O(k * n) + O(n ^ n)
# Space complexity: O(k * n) Trie + O(n) -> Recurse stack space
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

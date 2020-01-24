'''
Not executed on leetcode
time - O(N * 26^L * L), N - no. of input words , L - length of single word
space - O(N * L + N * L/2)
'''
# trienode class
class TrieNode:
    def __init__(self):
        self.children = [None] * 26
        self.startsWith = []

class Solution:
    def __init__(self):
        self.root = TrieNode()
        self.retList = []

    def wordSquares(self, words):
        # edge case
        if words == None or len(words) == 0:
            return self.retList

        # calling build Trie
        self.buildTrie(words)

        # calling backtracking function
        lst = []
        length = len(words[0])

        for word in words:
            lst.append(word)
            # call backtracking for adding starting word(trial and error for start word)
            self.backtracking(length, lst)
            lst.pop()

        return self.retList

    def backtracking(self, length, lst):
        # base case
        if len(lst) >= length:
            self.retList.append(lst[:]) # add the reference of list, so that all elements in the list at that point gets added.
            return

        #recursive case
        searchIndex = len(lst)
        possibleLet = ""
        for word in lst: # eg: ['ball', 'area'] possibleLet = 'le'(takes char at position 2 from both words)
            possibleLet += word[searchIndex]

        # call trie(search trie function for all possible words starting with given prefix) and get list
        possibleWord = self.possibleWords(possibleLet)

        # try all combinations of words of same length which can form word square and add the proper one to return list.
        for word in possibleWord:
            lst.append(word)
            self.backtracking(length, lst)
            lst.pop()

    # gets all possible words with given prefix( search trie function)
    def possibleWords(self, possibleLet):
        node = self.root
        result = []

        for i in range(len(possibleLet)):
            ch = possibleLet[i]
            if node.children[ord(ch) - ord('a')] == None:
                return result

            node = node.children[ord(ch) - ord('a')]

        result += node.startsWith
        return result
    # build trie node for each new word
    def buildTrie(self, words):
        for word in words:
            node = self.root
            for i in range(len(word)):
                ch = word[i]
                if node.children[ord(ch)-ord('a')] == None:
                    node.children[ord(ch) - ord('a')] = TrieNode()

                node.children[ord(ch) - ord('a')].startsWith.append(word)
                node = node.children[ord(ch)-ord('a')]

sol = Solution()
print(sol.wordSquares(["area","lead","wall","lady","ball","arvy"]))



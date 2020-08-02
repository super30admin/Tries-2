'''
Time Complexity: O(N*26^L*L) -> L is length of trie and N is number of words
Space Complexity: O(N*L)
Did this code successfully run on Leetcode : Yes
Explanation: Create a trie of the words in the input and after creating the trie save the index of the prefixes which
match the character for example, 'area' ->'a' contians the index of the word area which is 0 so does 'r' and 'e' and 'a' again.
for every word check if its possible to form wordsquare, once we take a word check if there is any word with word with the prefix
from 0:index in the wordSquare if it is then add that word to the wordSquare and backtrack, do this until we reach the length of the
word.
'''
class TrieNode:
    def __init__(self):
        self.children = {}
        self.wordIndex = []


class Solution:
    def getWordsWithPrefix(self, root, prefix):
        cursor = root
        for ch in prefix:
            child = cursor.children.get(ch)
            if child == None:
                return []
            cursor = child

        return cursor.wordIndex

    def constructTrie(self, words, root):
        for i in range(0, len(words)):
            self.insert(words[i], root, i)

    def insert(self, word, root, index):
        cursor = root

        for ch in word:
            child = cursor.children.get(ch)
            if child == None:
                child = TrieNode()
                cursor.children[ch] = child

            cursor = child
            cursor.wordIndex.append(index)

    # words is needed as we need to check index of words in trie
    def backTrack(self, root, limit, words, result, state, index):

        # goal state
        if index == limit:
            result.append(copy.deepcopy(state))
            return

        # get prefix
        # wall
        # area
        # index = 2, prefix to le

        prefix = ''
        for word in state:
            prefix = prefix + word[index]

        for wordIndex in self.getWordsWithPrefix(root, prefix):
            word = words[wordIndex]
            state.append(word)
            self.backTrack(root, limit, words, result, state, index + 1)
            state.pop()

    def wordSquares(self, words: List[str]) -> List[List[str]]:
        if words == None or len(words) == 0:
            return []
        result = []
        root = TrieNode()
        limit = len(words[0])

        self.constructTrie(words, root)

        for word in words:
            square = [word]
            self.backTrack(root, limit, words, result, square, 1)

        return result
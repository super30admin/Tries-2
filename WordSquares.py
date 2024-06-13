'''
TC: O(n*l) - n is thr total number of nodes and l is the size of the words to be 
            iterated after searching for the prefix and bactrack at every level.
SC: O(n*h)- n is the total number of nodes with space of all 26 letters
            in dictionary that can be considered to occupy O(1) 
            where l is the max length of any given word
'''
class TrieNode:
    def __init__(self):
        self.children = {}
        self.all_words = list([])

class Trie:
    def __init__(self):
        self.head = TrieNode()

    def insertTrie(self, word):
        pointer = self.head
        for letter in word:
            if letter not in pointer.children:
                pointer.children[letter] = TrieNode()
            pointer = pointer.children[letter]
            pointer.all_words.append(word)

    def search(self, prefix):
        pointer = self.head
        for letter in prefix:
            if letter not in pointer.children:
                return []
            pointer = pointer.children[letter]
        return pointer.all_words

class Solution:
    def __init__(self):
        self.ans = []
        self.obj = Trie()

    def backtrack(self, temp):
        if len(temp) == len(temp[0]):
            self.ans.append(list(temp))
        prefix = ""
        col = len(temp)
        for row in range(0, col):
            if col < len(temp[0]):
                prefix += temp[row][col]
        possible_words = self.obj.search(prefix)
        for word in possible_words:
            temp.append(word)
            self.backtrack(temp)
            temp.pop()

    def wordSquares(self, words: List[str]) -> List[List[str]]:
        for word in words:   
            self.obj.insertTrie(word)

        temp = []
        for word in words:
            temp.append(word)
            self.backtrack(temp)
            temp.pop()

        return self.ans